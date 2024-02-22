package uz.coder.cryptovalyutamy.data.network.conection

import retrofit2.http.GET
import retrofit2.http.Query
import uz.coder.cryptovalyutamy.data.network.models_dto.top_coin_model.CoinOfListDatumDto
import uz.coder.cryptovalyutamy.data.network.models_dto.top_coin_model.CoinPriceInfoRowDataDto

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(API_KEY)apiKey:String="",
        @Query(LIMIT)limit:Int = 50,
        @Query(TSYM)tsym:String = CURRENCY,
        ): CoinOfListDatumDto

    @GET("pricemultifull")
    suspend fun getFullInformation(
        @Query(API_KEY)apiKey:String = "",
        @Query(FSYMS)fsym:String,
        @Query(TOSYM)tsyms:String = CURRENCY,
    ): CoinPriceInfoRowDataDto


    companion object{
        private const val API_KEY = "api_key"
        private const val LIMIT = "limit"
        private const val TSYM = "tsym"
        private const val TOSYM = "tsyms"
        private const val FSYM = "fsym"
        private const val FSYMS = "fsyms"
        private const val CURRENCY = "USD"


    }
}