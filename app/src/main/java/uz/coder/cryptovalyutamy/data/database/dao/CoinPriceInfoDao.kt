package uz.coder.cryptovalyutamy.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.coder.cryptovalyutamy.data.database.models_db.CoinPriceInfoDb

@Dao
interface CoinPriceInfoDao {
    @Query("SELECT * FROM full_price_list ORDER by lastupdate DESC")
    fun getPriceList():Flow<List<CoinPriceInfoDb>>

    @Query("Select  * from full_price_list where fromSymbol = :fsym Limit 1")
    fun getPriceCoinInfoAbout(fsym:String):LiveData<CoinPriceInfoDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserPriceList(priceList:List<CoinPriceInfoDb>)

}