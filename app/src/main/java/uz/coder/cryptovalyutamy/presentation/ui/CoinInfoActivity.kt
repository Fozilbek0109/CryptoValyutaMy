package uz.coder.cryptovalyutamy.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import kotlinx.coroutines.launch
import uz.coder.cryptovalyutamy.databinding.ActivityCoinInfoBinding
import uz.coder.cryptovalyutamy.domain.model.CoinPriceInfo
import uz.coder.cryptovalyutamy.presentation.adapter.CoinsInfoAdapter
import uz.coder.cryptovalyutamy.presentation.vm.CoinViewModel
import uz.coder.cryptovalyutamy.presentation.vm.ViewModelFactory
import java.util.Locale
import javax.inject.Inject

class CoinInfoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinInfoBinding.inflate(layoutInflater)
    }
    private lateinit var adapterCoinInfo: CoinsInfoAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var listCoinPriceInfo: ArrayList<CoinPriceInfo>

    private val component by lazy {
        (application as App).component
    }

    private val coinViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        listCoinPriceInfo = ArrayList()
        component.inject(this)
        adapterCoinInfo = CoinsInfoAdapter(this@CoinInfoActivity) { model ->
            val newIntent =
                CoinDetailActivity.newIntent(this@CoinInfoActivity, model.fromSymbol)
            startActivity(newIntent)
        }
        binding.rec.itemAnimator = null
        binding.rec.adapter = adapterCoinInfo


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                coinViewModel.getCoinInfoList.collect { listCoin ->
                        listCoinPriceInfo.clear()
                        listCoinPriceInfo.addAll(listCoin)
                        adapterCoinInfo.submitList(listCoin)
                }
            }
        }
        setupSearchView()
        binding.banner.setAdUnitId("R-M-6207023-4")
        binding.banner.setAdSize(BannerAdSize.stickySize(this, Int.MAX_VALUE))
        val adRequest = AdRequest.Builder().build()
        binding.banner.loadAd(adRequest)
        binding.banner.setBannerAdEventListener(object : BannerAdEventListener {
            override fun onAdLoaded() {

            }

            override fun onAdFailedToLoad(p0: AdRequestError) {

            }

            override fun onAdClicked() {

            }

            override fun onLeftApplication() {

            }

            override fun onReturnedToApplication() {

            }

            override fun onImpression(p0: ImpressionData?) {

            }
        })
    }

    private fun filter(newText: String) {
        for (coinPriceInfo in listCoinPriceInfo) {
            if (coinPriceInfo.fromSymbol.lowercase(Locale.getDefault()).contains(
                    newText.lowercase(
                        Locale.getDefault()
                    )
                )
            ) {
                binding.rec.scrollToPosition(listCoinPriceInfo.indexOf(coinPriceInfo))
            }
        }
    }

    private fun setupSearchView() {
        binding.searView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filter(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText ?: "")
                return true
            }
        })
    }
    companion object {
        private const val TAG = "CoinInfoActivity"
        fun newIntent(context: Context):Intent = Intent(context,CoinInfoActivity::class.java)
    }
}