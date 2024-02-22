package uz.coder.cryptovalyutamy.data.database.repo_impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import uz.coder.cryptovalyutamy.data.database.dao.CoinPriceInfoDao
import uz.coder.cryptovalyutamy.data.mapper.CoinMapper
import uz.coder.cryptovalyutamy.data.service.CoinRefreshWorkManager
import uz.coder.cryptovalyutamy.domain.model.CoinPriceInfo
import uz.coder.cryptovalyutamy.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    val context: Context,
    val dao: CoinPriceInfoDao,
    val mapper: CoinMapper
) : CoinRepository {


    override fun getListCoinPriseInfo(): Flow<List<CoinPriceInfo>> =
        flow {
            dao.getPriceList().catch {
                Log.d(TAG, "getListCoinPriseInfo: ${it.message}")
            }.filter {
                it.isNotEmpty()
            }.filter {
                it.all { it != null }
            }.collect {
                emit(mapper.mapListCionPIDbToListCoinPI(it))
            }
        }

    companion object {
        private const val TAG = "CoinRepositoryImpl"
    }

    override fun load() {
        val worker = WorkManager.getInstance(context)
        worker.enqueueUniqueWork(
            CoinRefreshWorkManager.NAME,
            ExistingWorkPolicy.REPLACE,
            CoinRefreshWorkManager.makeRequest()
        )

    }

    override fun getCoin(fromSymbol: String): LiveData<CoinPriceInfo> {
        return dao.getPriceCoinInfoAbout(fromSymbol).map {
            mapper.mapCoinPIDbToCoinPI(it)
        }
    }


}
