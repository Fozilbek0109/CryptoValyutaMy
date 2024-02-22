package uz.coder.cryptovalyutamy.di

import dagger.Binds
import dagger.Module
import uz.coder.cryptovalyutamy.data.database.repo_impl.CoinRepositoryImpl
import uz.coder.cryptovalyutamy.domain.repository.CoinRepository
@Module
interface DomainMudule {

    @Binds
    fun bindsCoinRepository(impl: CoinRepositoryImpl): CoinRepository

}