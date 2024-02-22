package uz.coder.cryptovalyutamy.presentation.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import uz.coder.cryptovalyutamy.R
import uz.coder.cryptovalyutamy.databinding.ActivityCoinDetailBinding
import uz.coder.cryptovalyutamy.presentation.vm.CoinViewModel
import uz.coder.cryptovalyutamy.presentation.vm.ViewModelFactory
import javax.inject.Inject

class CoinDetailActivity : AppCompatActivity() {

    private val  binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[CoinViewModel::class.java]
    }

    private val component by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        val coinName = intent.getStringExtra(KEY)?:""


        viewModel.getCoinInfo(coinName).observe(this){
            binding.apply {
                Picasso.get().load(it.imageurl).into(ivLogoCoin)
                tvPrice.text = String.format(getString(R.string.priceinfo,it.price.toString()))
                tvLastUpdate.text = String.format(getString(R.string.date,it.lastupdate))
                tvSymbols.text = String.format(getString(R.string.coin_name,it.fromSymbol,it.tosymbol))
            }

        }





    }
    companion object {
         const val KEY = "key"
        fun newIntent(context: Context, str: String): Intent =
            Intent(context, CoinDetailActivity::class.java).apply {
                putExtra(KEY, str)
            }

    }
}