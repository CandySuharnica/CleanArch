package dev.rodkin.syharnicacleanarch.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rodkin.domain.useCases.RegisterUserUseCase
import javax.inject.Inject

@HiltViewModel
class LogInAndSignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {
    fun register(email: String, password: String) {
        registerUserUseCase.register(email, password)
    }
}