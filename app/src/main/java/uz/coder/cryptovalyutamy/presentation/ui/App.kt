package uz.coder.cryptovalyutamy.presentation.ui

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.yandex.mobile.ads.common.MobileAds
import uz.coder.cryptovalyutamy.data.service.CoinRefreshWorkFactory
import uz.coder.cryptovalyutamy.di.DaggerAppComponent
import javax.inject.Inject

class App : Application(), Configuration.Provider {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    @Inject
    lateinit var coinRefreshWorkFactory: CoinRefreshWorkFactory

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
        MobileAds.initialize(this) {
            Log.d(TAG, "onCreate: start ads")
        }

    }

    companion object {
        private const val TAG = "App"
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(coinRefreshWorkFactory).build()
    }
}