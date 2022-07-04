package com.lateef.cryptoapp.domain.use_case.get_coins


import com.lateef.cryptoapp.common.Resource
import com.lateef.cryptoapp.data.remote.dto.toCoin
import com.lateef.cryptoapp.domain.model.Coin
import com.lateef.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try{
            //to show our progress bar
             emit(Resource.Loading<List<Coin>>())
             val coins = repository.getCoins().map { it.toCoin() }
             emit(Resource.Success<List<Coin>>(coins))
        }catch(e: HttpException){
        //if we get reponse code that didn't start from 2 error code not successful code
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An expected error occurred"))
        }catch(e: IOException){
          // if no internet connection
            emit(Resource.Error<List<Coin>>("Couldn't reach server, check your internet connection"))
        }
    }


}