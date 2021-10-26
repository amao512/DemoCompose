package com.aslnstbk.democompose.profile.data.firebase

import com.aslnstbk.democompose.global.data.Constants.EMPTY
import com.aslnstbk.democompose.global.data.firebase.DatabaseConstants
import com.aslnstbk.democompose.profile.data.models.UserDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileFirebaseDataSource(
    private val firebaseDataSource: FirebaseDatabase,
    private val firebaseUser: FirebaseUser,
    private val firebaseAuth: FirebaseAuth
) {

    fun getUserByUid(
        uid: String,
        onSuccess: (UserDTO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDataSource.getReference(DatabaseConstants.USERS)
            .child(uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.getValue(UserDTO::class.java)?.let {
                        onSuccess(it)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }
            })
    }

    fun addUser(
        userId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        getUserByUid(
            uid = firebaseUser.uid,
            onSuccess = { user ->
                firebaseDataSource.getReference(DatabaseConstants.USERS)
                    .child(if (user.isDoctor) DatabaseConstants.USER_PATIENTS else DatabaseConstants.USER_DOCTORS)
                    .setValue(userId)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onFailure(it.localizedMessage ?: EMPTY)
                    }
            },
            onFailure = {}
        )
    }

    fun signOut(
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseAuth.signOut().apply {
            onSuccess()
        }
    }
}