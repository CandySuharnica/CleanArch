package dev.rodkin.syharnicacleanarch.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rodkin.domain.useCases.GetCountAllBasketItemsFlowUseCase
import javax.inject.Inject

@HiltViewModel
class BottomBarViewModel @Inject constructor(
    private val getAllCountUseCase: GetCountAllBasketItemsFlowUseCase
):ViewModel() {
    fun getCount() = getAllCountUseCase.getCountFlow()
}