package uz.coder.cryptovalyutamy.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import uz.coder.cryptovalyutamy.R
import uz.coder.cryptovalyutamy.databinding.ItemCoinBinding
import uz.coder.cryptovalyutamy.domain.model.CoinPriceInfo

class CoinsInfoAdapter(val context: Context, private val clicItem: (CoinPriceInfo) -> Unit) :
    ListAdapter<CoinPriceInfo, CoinsInfoAdapter.VH>(CoinDifColback()) {
    inner class VH(val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        ItemCoinBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            txtFsymTsym.text = String.format(
                context.getString(R.string.BTC_USD),
                getItem(position).fromSymbol,
                getItem(position).tosymbol
            )
            txtDateUpdate.text = getItem(position).lastupdate
            txtPrice.text =
                String.format(context.getString(R.string.price, getItem(position).price.toString()))
            Picasso.get().load(getItem(position).imageurl).into(imgCoin)
            holder.itemView.setOnClickListener {
                clicItem.invoke(getItem(position))
            }
            Glide.with(context).load(R.drawable.diagram).into(gif)
            txtPrice.isSelected = true
        }
    }

}