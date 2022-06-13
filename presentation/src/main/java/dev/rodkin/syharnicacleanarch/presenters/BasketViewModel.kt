package dev.rodkin.syharnicacleanarch.presenters

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rodkin.domain.useCases.BasketUseCase
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val basketUseCase: BasketUseCase
) : ViewModel() {


}