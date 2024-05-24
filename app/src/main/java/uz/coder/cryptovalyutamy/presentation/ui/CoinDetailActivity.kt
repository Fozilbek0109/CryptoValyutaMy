package uz.coder.cryptovalyutamy.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import uz.coder.cryptovalyutamy.R
import uz.coder.cryptovalyutamy.databinding.ActivityCoinDetailBinding
import uz.coder.cryptovalyutamy.presentation.vm.CoinViewModel
import uz.coder.cryptovalyutamy.presentation.vm.ViewModelFactory
import javax.inject.Inject

class CoinDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    private val component by lazy {
        (application as App).component
    }
    private lateinit var buttons: ArrayList<Button>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        component.inject(this)
        val coinName = intent.getStringExtra(KEY) ?: ""
        viewModel.getCoinInfo(coinName).observe(this) { coin ->
            binding.apply {
                Picasso.get().load(coin.imageurl).into(ivLogoCoin)
                tvPrice.text = String.format(getString(R.string.priceinfo, coin.price.toString()))
                tvLastUpdate.text = String.format(getString(R.string.date, coin.lastupdate))
                tvSymbols.text =
                    String.format(getString(R.string.coin_name, coin.fromSymbol, coin.tosymbol))
            }
            binding.btnCalkulate.setOnClickListener {
                calculate(coin.price, coin.fromSymbol, coin.tosymbol)
            }
        }


    }

    private var EXCHANGE_COIN = 0
    private fun calculate(price: Double?, fromSymbol: String, tosymbol: String?) {
        binding.apply {
            if (ivLogoCoin.isVisible && tvPrice.isVisible && tvSymbols.isVisible && tvLastUpdate.isVisible) {
                ivLogoCoin.visibility = View.GONE
                tvPrice.visibility = View.GONE
                tvSymbols.visibility = View.GONE
                tvLastUpdate.visibility = View.GONE
                lyKeyboard.visibility = View.VISIBLE
                txtSetCoin.text = tosymbol
                txtGetCoin.text = fromSymbol
                val toString = txtSetPrise.text.toString()
                if (toString.isNotEmpty()) {
                    if (EXCHANGE_COIN == 0) {
                        txtSetCoin.text = tosymbol
                        txtGetCoin.text = fromSymbol
                        getTextButtonAndCalculatShowTv(price, "")
                    } else {
                        txtSetCoin.text = fromSymbol
                        txtGetCoin.text = tosymbol
                        getTextButtonAndCalculatShowTv(price, "")
                    }
                }
                btnExchangeCoin.setOnClickListener {
                    if (EXCHANGE_COIN == 0) {
                        EXCHANGE_COIN = 1
                        txtGetCoin.text = tosymbol
                        txtSetCoin.text = fromSymbol
                        getTextButtonAndCalculatShowTv(price, "")

                    } else if (EXCHANGE_COIN == 1) {
                        EXCHANGE_COIN = 0
                        txtSetCoin.text = tosymbol
                        txtGetCoin.text = fromSymbol
                        getTextButtonAndCalculatShowTv(price, "")
                    } else {
                        EXCHANGE_COIN = 0
                        txtSetCoin.text = tosymbol
                        txtGetCoin.text = fromSymbol
                        getTextButtonAndCalculatShowTv(price, "")
                    }
                }

                btn1.setOnClickListener {
                    getTextButtonAndCalculatShowTv(price, btn1.text.toString())
                }
                btn2.setOnClickListener {
                    getTextButtonAndCalculatShowTv(price, btn2.text.toString())
                }
                btn3.setOnClickListener {
                    getTextButtonAndCalculatShowTv(price, btn3.text.toString())
                }
                btn4.setOnClickListener {
                    getTextButtonAndCalculatShowTv(price, btn4.text.toString())
                }
                btn5.setOnClickListener {
                    getTextButtonAndCalculatShowTv(price, btn5.text.toString())
                }
                btn6.setOnClickListener {
                    getTextButtonAndCalculatShowTv(price, btn6.text.toString())
                }
                btn7.setOnClickListener {
                    getTextButtonAndCalculatShowTv(price, btn7.text.toString())
                }
                btn8.setOnClickListener {
                    getTextButtonAndCalculatShowTv(price, btn8.text.toString())
                }
                btn9.setOnClickListener {
                    getTextButtonAndCalculatShowTv(price, btn9.text.toString())
                }
                btn0.setOnClickListener {
                    getTextButtonAndCalculatShowTv(price, btn0.text.toString())
                }
                btnDot.setOnClickListener {
                    if (!txtSetPrise.text.toString()
                            .contains(btnDot.text.toString())
                    ) getTextButtonAndCalculatShowTv(price, btnDot.text.toString())
                }
                btnClear.setOnClickListener {
                    getTextButtonAndCalculatShowTv(price, "-1")
                }
                btnClear.setOnLongClickListener {
                    getTextButtonAndCalculatShowTv(price, "-2")
                    true
                }
            } else {
                ivLogoCoin.visibility = View.VISIBLE
                tvPrice.visibility = View.VISIBLE
                tvSymbols.visibility = View.VISIBLE
                tvLastUpdate.visibility = View.VISIBLE
                lyKeyboard.visibility = View.GONE
            }
        }
    }

    private fun getTextButtonAndCalculatShowTv(price: Double?, number: String) {
        binding.apply {
            if (EXCHANGE_COIN == 0) {
                usdToCoin(price, number)
            } else if (EXCHANGE_COIN == 1) {
                coinToUsd(price, number)
            }
        }
    }

    private fun usdToCoin(price: Double?, number: String) {
        binding.apply {

            val toString = txtSetPrise.text.toString()
            if (number != "-1" && number != "-2") {
                if (number == "0" && toString.isEmpty()) {
                    txtSetPrise.text = "0."
                    txtGetPrise.text = "0.0"
                } else {
                    if (toString.isNotEmpty()) {
                        val get = txtSetPrise.text.toString()
                        val sum = "$get$number"
                        txtSetPrise.text = sum
                        if (price != null && price > 0.0) {
                            val d = sum.toDouble() / price
                            txtGetPrise.text = d.toString()
                        }
                    } else {
                        if (number == ".") {
                            txtSetPrise.text = "0$number"
                        } else {
                            txtSetPrise.text = number
                            if (number.isNotEmpty() && number != "0" && number.toDouble() > 0 && price != null && price > 0.0) {
                                val d = (number.toDouble() / price).toString()
                                txtGetPrise.text = d
                            }
                        }
                    }
                }
            } else if (number == "-2") {
                val s = txtSetPrise.text.toString()
                if (s.isNotEmpty()) {
                    txtSetPrise.text = ""
                    txtGetPrise.text = ""
                }
            } else {
                val s = txtSetPrise.text.toString()
                if (s.isNotEmpty()) {
                    val substring = s.substring(0, s.length - 1)
                    txtSetPrise.text = substring
                    if (substring != "0" && substring.isNotEmpty() && price != null && price > 0.0) {
                        val d = substring.toDouble() / price
                        txtGetPrise.text = d.toString()
                    } else if (substring == "0") {
                        txtGetPrise.text = "0"
                    } else if (substring.isEmpty()) {
                        txtGetPrise.text = ""
                    }
                }
            }
        }
    }

    private fun coinToUsd(price: Double?, number: String) {
        binding.apply {
            val toString = txtSetPrise.text.toString()
            if (number != "-1" && number != "-2") {
                if (number == "0" && toString.isEmpty()) {
                    txtSetPrise.text = "0."
                    txtGetPrise.text = "0.0"
                } else {
                    if (toString.isNotEmpty()) {
                        val get = txtSetPrise.text.toString()
                        val sum = "$get$number"
                        txtSetPrise.text = sum
                        if (price != null && price > 0.0) {
                            val d = sum.toDouble() * price
                            txtGetPrise.text = d.toString()
                        }
                    } else {
                        if (number == ".") {
                            txtSetPrise.text = "0$number"
                        } else {
                            txtSetPrise.text = number
                            if (number.isNotEmpty() && number != "0" && number.toDouble() > 0 && price != null && price > 0.0) {
                                val d = (number.toDouble() * price).toString()
                                txtGetPrise.text = d
                            }
                        }
                    }
                }
            } else if (number == "-2") {
                val s = txtSetPrise.text.toString()
                if (s.isNotEmpty()) {
                    txtSetPrise.text = ""
                    txtGetPrise.text = ""
                }
            } else {
                val s = txtSetPrise.text.toString()
                if (s.isNotEmpty()) {
                    val substring = s.substring(0, s.length - 1)
                    txtSetPrise.text = substring
                    if (substring != "0" && substring.isNotEmpty() && price != null && price > 0.0) {
                        val d = substring.toDouble() * price
                        txtGetPrise.text = d.toString()
                    } else if (substring == "0") {
                        txtGetPrise.text = "0"
                    } else if (substring.isEmpty()) {
                        txtGetPrise.text = ""
                    }
                }
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

    override fun onBackPressed() {
        binding.apply {
            if (ivLogoCoin.isVisible && tvPrice.isVisible && tvSymbols.isVisible && tvLastUpdate.isVisible) {
                super.onBackPressed()
            } else {
                ivLogoCoin.visibility = View.VISIBLE
                tvPrice.visibility = View.VISIBLE
                tvSymbols.visibility = View.VISIBLE
                tvLastUpdate.visibility = View.VISIBLE
                lyKeyboard.visibility = View.GONE
            }
        }
    }


}