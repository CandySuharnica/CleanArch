package dev.rodkin.domain.useCases.useCasesImpl

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dev.rodkin.domain.useCases.RegisterUserUseCase
import javax.inject.Inject

class RegisterUserUseCaseImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : RegisterUserUseCase {

    override fun register(email: String, password: String): Task<AuthResult> =
        firebaseAuth.createUserWithEmailAndPassword(email,password)
}