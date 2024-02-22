package uz.coder.cryptovalyutamy.di

import android.content.Context
import dagger.Module
import dagger.Provides
import uz.coder.cryptovalyutamy.data.database.dao.CoinPriceInfoDao
import uz.coder.cryptovalyutamy.data.database.db.AppDatabase
import uz.coder.cryptovalyutamy.data.network.conection.ApiClient
import uz.coder.cryptovalyutamy.data.network.conection.ApiService

@Module
class DataMudule {


    @Provides
    @ApplicationScope
    fun providesAppDataBase(context: Context): CoinPriceInfoDao {
        return AppDatabase.getInstens(context).coinPriceInfoDao()
    }

    @Provides
    @ApplicationScope
   fun providesApiService(): ApiService {
       return ApiClient.getRetrofit().create(ApiService::class.java)
   }

}