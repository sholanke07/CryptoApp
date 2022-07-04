package com.lateef.cryptoapp.data.repository

import com.lateef.cryptoapp.data.remote.CoinPaprikaApi
import com.lateef.cryptoapp.data.remote.dto.CoinDetailDto
import com.lateef.cryptoapp.data.remote.dto.CoinDto
import com.lateef.cryptoapp.domain.repository.CoinRepository
import javax.inject.Inject


class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
    ): CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}