package uz.coder.cryptovalyutamy.data.database.models_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "full_price_list")
data class CoinPriceInfoDb (
    @PrimaryKey
    val fromSymbol: String,
    val tosymbol: String? = null,
    val lastupdate: String? = null,
    val price: Double? = null,
    val imageurl: String? = null
){
}