package uz.coder.cryptovalyutamy.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.coder.cryptovalyutamy.domain.model.CoinPriceInfo
import uz.coder.cryptovalyutamy.domain.repository.CoinRepository
import javax.inject.Inject

class GetListCoinPriseInfoUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(): Flow<List<CoinPriceInfo>> = repository.getListCoinPriseInfo()
}