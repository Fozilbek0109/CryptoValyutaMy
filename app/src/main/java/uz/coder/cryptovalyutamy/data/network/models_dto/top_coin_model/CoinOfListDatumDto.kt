package uz.coder.cryptovalyutamy.data.network.models_dto.top_coin_model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class CoinOfListDatumDto (
    @SerializedName("Data")
    @Expose
    val data: List<DatumDto>? = null
)