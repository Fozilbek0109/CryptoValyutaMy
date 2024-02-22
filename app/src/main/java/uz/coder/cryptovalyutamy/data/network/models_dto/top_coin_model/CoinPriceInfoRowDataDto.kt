package uz.coder.cryptovalyutamy.data.network.models_dto.top_coin_model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceInfoRowDataDto (
    @SerializedName("RAW")
    @Expose
    val coinPriceInfoJsonObject:JsonObject? = null
)