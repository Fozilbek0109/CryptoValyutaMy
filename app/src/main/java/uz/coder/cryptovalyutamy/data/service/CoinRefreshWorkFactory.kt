package uz.coder.cryptovalyutamy.data.service

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import uz.coder.cryptovalyutamy.data.database.dao.CoinPriceInfoDao
import uz.coder.cryptovalyutamy.data.mapper.CoinMapper
import uz.coder.cryptovalyutamy.data.network.conection.ApiService
import javax.inject.Inject

class CoinRefreshWorkFactory @Inject constructor(
    val dao: CoinPriceInfoDao,
    val apiService: ApiService,
    val mapper: CoinMapper
):WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return CoinRefreshWorkManager(appContext,workerParameters,dao,apiService,mapper)
    }
}