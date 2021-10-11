package com.aslnstbk.democompose.auth.data.firebase

import android.util.Log
import com.aslnstbk.democompose.auth.data.models.RegistrationParam
import com.aslnstbk.democompose.auth.data.models.UserDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthenticationFirebaseDataSource(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) {

    fun onLogin(
        email: String,
        password: String,
        onSuccess: (UserDTO) -> Unit,
        onFailure: () -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser

                    onSuccess(
                        UserDTO(
                            id = user?.uid,
                            displayName = user?.displayName,
                            email = user?.email,
                            phoneNumber = user?.phoneNumber,
                            photoUrl = user?.photoUrl.toString()
                        )
                    )
                } else {
                    Log.d("TAG4", "Authorization Error")
                    onFailure()
                }
            }
    }

    fun onRegister(
        param: RegistrationParam,
        onSuccess: (UserDTO) -> Unit,
        onFailure: () -> Unit
    ) {
        auth.createUserWithEmailAndPassword(param.email, param.password)
            .addOnSuccessListener {
                val user = UserDTO(
                    id = auth.currentUser?.uid,
                    displayName = auth.currentUser?.displayName,
                    email = auth.currentUser?.email,
                    phoneNumber = auth.currentUser?.phoneNumber,
                    photoUrl = auth.currentUser?.photoUrl.toString()
                )

                database.getReference(DatabaseConstants.USERS)
                    .child(user.id.orEmpty())
                    .setValue(param.apply { password = "" })
                    .addOnSuccessListener {
                        onSuccess(user)
                    }
                    .addOnFailureListener {
                        Log.d("TAG4", "Authorization Error")
                        onFailure()
                    }
            }
            .addOnFailureListener {
                Log.d("TAG4", "Authorization Error")
                onFailure()
            }
    }

    fun signOut() {
        auth.signOut()
    }
}