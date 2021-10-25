package com.aslnstbk.democompose.profile.data.firebase

import com.aslnstbk.democompose.global.data.firebase.DatabaseConstants
import com.aslnstbk.democompose.profile.data.models.UserDTO
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileFirebaseDataSource(
    private val firebaseDataSource: FirebaseDatabase
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
}