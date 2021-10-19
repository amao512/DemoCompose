package com.aslnstbk.democompose.auth.data.firebase

import android.util.Log
import com.aslnstbk.democompose.auth.data.models.RegistrationParam
import com.aslnstbk.democompose.profile.data.models.UserDTO
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
                            id = user?.uid.orEmpty(),
                            name = user?.displayName.orEmpty(),
                            surname = user?.displayName.orEmpty(),
                            email = user?.email.orEmpty(),
                            phoneNumber = user?.phoneNumber.orEmpty(),
                            photoUrl = user?.photoUrl.toString(),
                            isDoctor = false
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
                    id = auth.currentUser?.uid.orEmpty(),
                    name = param.name,
                    surname = param.surname,
                    email = auth.currentUser?.email.orEmpty(),
                    phoneNumber = auth.currentUser?.phoneNumber.orEmpty(),
                    photoUrl = auth.currentUser?.photoUrl.toString(),
                    isDoctor = param.isDoctor
                )

                database.getReference(DatabaseConstants.USERS)
                    .child(user.id)
                    .setValue(user)
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