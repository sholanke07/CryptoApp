package com.lateef.cryptoapp.presentation.coin_list.components


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.*
import com.lateef.cryptoapp.common.Resource
import com.lateef.cryptoapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel


@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {

     private val _state = mutableStateOf(CoinListState())
     val state: State<CoinListState> = _state

    init {
        getCoins()
    }
      private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?:
                    "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}








