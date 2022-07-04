package com.lateef.cryptoapp.domain.repository

import com.lateef.cryptoapp.data.remote.dto.CoinDetailDto
import com.lateef.cryptoapp.data.remote.dto.CoinDto

interface CoinRepository {
   suspend fun getCoins(): List<CoinDto>

   suspend fun getCoinById(coinId: String): CoinDetailDto

}