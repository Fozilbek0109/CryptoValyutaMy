package uz.coder.cryptovalyutamy.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.coder.cryptovalyutamy.presentation.vm.CoinViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindsCoinViewModel(coinViewModel: CoinViewModel):ViewModel
}