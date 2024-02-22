package uz.coder.cryptovalyutamy.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import uz.coder.cryptovalyutamy.domain.model.CoinPriceInfo
import uz.coder.cryptovalyutamy.domain.use_case.GetCoinInfoUseCase
import uz.coder.cryptovalyutamy.domain.use_case.GetListCoinPriseInfoUseCase
import uz.coder.cryptovalyutamy.domain.use_case.LoadUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    val getCoinInfoUseCase: GetCoinInfoUseCase,
    loadUseCase: LoadUseCase,
    gerListCoinPriseInfoUseCase: GetListCoinPriseInfoUseCase
) : ViewModel() {

    val getCoinInfoList: Flow<List<CoinPriceInfo>> = gerListCoinPriseInfoUseCase()

    init {
        loadUseCase()
    }

    fun getCoinInfo(fsyml: String): LiveData<CoinPriceInfo> {
        return getCoinInfoUseCase(fsyml)
    }


}