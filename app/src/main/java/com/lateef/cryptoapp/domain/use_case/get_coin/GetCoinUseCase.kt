package com.lateef.cryptoapp.domain.use_case.get_coin


import com.lateef.cryptoapp.common.Resource
import com.lateef.cryptoapp.data.remote.dto.toCoinDetail
import com.lateef.cryptoapp.domain.model.CoinDetail
import com.lateef.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
)  {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try{
            //to show our progress bar
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        }catch(e: HttpException){
            //if we get reponse code that didnot start from 2 error code not successful code
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An expected error occured"))
        }catch(e: IOException){
            // if no internet connection
            emit(Resource.Error<CoinDetail>("Couldn't reach server, check your internet connection"))
        }
    }
}