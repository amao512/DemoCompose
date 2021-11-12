package com.aslnstbk.democompose.search.data.firebase

import com.aslnstbk.democompose.global.data.firebase.DatabaseConstants
import com.aslnstbk.democompose.global.presentation.utils.toUserDTO
import com.aslnstbk.democompose.profile.data.models.UserDTO
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UsersFirebaseDataSource(
    private val firebaseDatabase: FirebaseDatabase
) {

    fun getAllUsers(
        onSuccess: (List<UserDTO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        firebaseDatabase.getReference(DatabaseConstants.USERS)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    onSuccess(
                        snapshot.children.map {
                            it.toUserDTO()
                        }
                    )
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }
            })
    }
}