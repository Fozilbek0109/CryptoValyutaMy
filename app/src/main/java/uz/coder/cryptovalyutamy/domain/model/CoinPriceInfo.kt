package uz.coder.cryptovalyutamy.domain.model

data class CoinPriceInfo(
    val fromSymbol: String,
    val tosymbol: String? = null,
    val lastupdate: String? = null,
    val price: Double? = null,
    val imageurl: String? = null
)