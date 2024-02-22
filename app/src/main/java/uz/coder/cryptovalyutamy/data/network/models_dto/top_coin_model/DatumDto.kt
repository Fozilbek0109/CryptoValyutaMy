package uz.coder.cryptovalyutamy.data.network.models_dto.top_coin_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import uz.coder.cryptovalyutamy.data.network.models_dto.top_coin_model.CoinNameDto

data class DatumDto (
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinNameDto? = null
)