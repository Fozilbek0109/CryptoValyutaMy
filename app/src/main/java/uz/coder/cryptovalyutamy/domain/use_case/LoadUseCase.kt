package uz.coder.cryptovalyutamy.domain.use_case

import uz.coder.cryptovalyutamy.domain.repository.CoinRepository
import javax.inject.Inject

class LoadUseCase @Inject constructor (private val repository: CoinRepository) {
     operator fun invoke()=repository.load()
}