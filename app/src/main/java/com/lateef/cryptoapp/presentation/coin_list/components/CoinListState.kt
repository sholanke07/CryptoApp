package com.lateef.cryptoapp.presentation.coin_list.components

import com.lateef.cryptoapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
