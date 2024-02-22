package uz.coder.cryptovalyutamy.data.mapper

import com.google.gson.Gson
import uz.coder.cryptovalyutamy.data.database.models_db.CoinPriceInfoDb

import uz.coder.cryptovalyutamy.data.network.models_dto.top_coin_model.CoinOfListDatumDto
import uz.coder.cryptovalyutamy.data.network.models_dto.top_coin_model.CoinPriceInfoDto
import uz.coder.cryptovalyutamy.data.network.models_dto.top_coin_model.CoinPriceInfoRowDataDto
import uz.coder.cryptovalyutamy.domain.model.CoinPriceInfo
import javax.inject.Inject

class CoinMapper @Inject constructor(){

    /**
     * CoinPriceInfoDB:   bu metod data basedagi modelni damin modeliga o'tqazadi
     */
    fun mapCoinPIDbToCoinPI(coinPriceInfoDb: CoinPriceInfoDb): CoinPriceInfo =
        CoinPriceInfo(
            fromSymbol = coinPriceInfoDb.fromSymbol,
            tosymbol = coinPriceInfoDb.tosymbol,
            lastupdate = coinPriceInfoDb.lastupdate,
            price = coinPriceInfoDb.price,
            imageurl = coinPriceInfoDb.imageurl
        )

    /**
     *  CoinPriceInfoDB:    database dagi model listni damain model listga o'tqazadi
     */
    fun mapListCionPIDbToListCoinPI(listdb: List<CoinPriceInfoDb>): List<CoinPriceInfo> {
        val list = ArrayList<CoinPriceInfo>()
        listdb.forEach { list.add(mapCoinPIDbToCoinPI(it)) }
        return list
    }

    fun mapCoinPIDtoToCoinPIDb(coinPriceInfoDto: CoinPriceInfoDto): CoinPriceInfoDb =
        CoinPriceInfoDb(
            fromSymbol = coinPriceInfoDto.fromSymbol,
            tosymbol = coinPriceInfoDto.tosymbol,
            lastupdate = coinPriceInfoDto.getTimeconverted(),
            price = coinPriceInfoDto.price,
            imageurl = coinPriceInfoDto.getImageLink()
        )

    fun mapListCoinPIDtoToCoinPIdb(list: List<CoinPriceInfoDto>): List<CoinPriceInfoDb> {
        val listDb = ArrayList<CoinPriceInfoDb>()
        for (coinPriceInfoDto in list) {
            listDb.add(mapCoinPIDtoToCoinPIDb(coinPriceInfoDto))
        }
        return listDb
    }

    fun maptoContainerToListCoinInfo(jsonContainerDto: CoinPriceInfoRowDataDto): List<CoinPriceInfoDto> {
        val result = ArrayList<CoinPriceInfoDto>()
        val jsonObject = jsonContainerDto.coinPriceInfoJsonObject ?: return result
        val keySet = jsonObject.keySet() //BTC , SOL
        for (key in keySet) {//BTC ....SOL
            val currentJSon = jsonObject.getAsJsonObject(key) //BTC{}
            val keySetCurrent = currentJSon.keySet() //BTC{price,time, from}
            for (currentKey in keySetCurrent) {
                val priceInfo = Gson().fromJson(
                    currentJSon.getAsJsonObject(currentKey),
                    CoinPriceInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapListDbModelEntity(namesListDto: CoinOfListDatumDto): String {
        return namesListDto.data?.map { it.coinInfo?.name }?.joinToString(",") ?: ""
    }


}