package uz.coder.cryptovalyutamy.domain.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.coder.cryptovalyutamy.domain.model.CoinPriceInfo

interface CoinRepository {
    fun getListCoinPriseInfo(): Flow<List<CoinPriceInfo>>
    fun getCoin(fromSymbol: String): LiveData<CoinPriceInfo>

    fun load()
}