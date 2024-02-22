package uz.coder.cryptovalyutamy.data.service

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import uz.coder.cryptovalyutamy.data.database.dao.CoinPriceInfoDao
import uz.coder.cryptovalyutamy.data.mapper.CoinMapper
import uz.coder.cryptovalyutamy.data.network.conection.ApiService


class CoinRefreshWorkManager(
    context: Context,
    workerParameters: WorkerParameters,
    val dao: CoinPriceInfoDao,
    val apiService: ApiService,
    val mapper: CoinMapper
):CoroutineWorker(context,workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo()
                val fSyms = mapper.mapListDbModelEntity(topCoins)
                Log.d("TAG", "onCreate: $fSyms")
                val jsonContainer = apiService.getFullInformation(fsym = fSyms)
                val coinInfoDtoList = mapper.maptoContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapCoinPIDtoToCoinPIDb(it) }
                Log.d("TAG", "doWork: $dbModelList ")
                dao.inserPriceList(dbModelList)
            } catch (e: Exception) {
                Log.d("TAG", "load: $e")
            }
            delay(1000)
        }
    }

    companion object {
        const val NAME = "MyRefreshWorker"
        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<CoinRefreshWorkManager>().build()
        }
    }
}