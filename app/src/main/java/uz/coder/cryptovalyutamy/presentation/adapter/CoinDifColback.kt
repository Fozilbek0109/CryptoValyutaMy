package uz.coder.cryptovalyutamy.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import uz.coder.cryptovalyutamy.domain.model.CoinPriceInfo

class CoinDifColback:DiffUtil.ItemCallback<CoinPriceInfo>() {
    override fun areItemsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem == newItem
    }
}