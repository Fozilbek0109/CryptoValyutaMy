package uz.coder.cryptovalyutamy.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uz.coder.cryptovalyutamy.presentation.ui.App
import uz.coder.cryptovalyutamy.presentation.ui.CoinDetailActivity
import uz.coder.cryptovalyutamy.presentation.ui.CoinInfoActivity

@ApplicationScope
@Component(modules = [DataMudule::class, ViewModelModule::class, DomainMudule::class])
interface AppComponent {

    fun inject(app: App)
    fun inject(coinInfoActivity: CoinInfoActivity)
    fun inject(coinDetailActivity: CoinDetailActivity)
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}