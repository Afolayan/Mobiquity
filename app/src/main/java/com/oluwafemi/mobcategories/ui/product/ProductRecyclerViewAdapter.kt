package com.oluwafemi.mobcategories.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oluwafemi.mobcategories.R
import com.oluwafemi.mobcategories.data.remote.model.Product
import com.oluwafemi.mobcategories.ui.product.enums.CategoryType
import com.oluwafemi.mobcategories.ui.product.model.ProductTypeAndData
import com.oluwafemi.mobcategories.util.ImageLoader
import com.oluwafemi.mobcategories.util.StringUtil.joinTwoStrings
import javax.inject.Inject

class ProductRecyclerViewAdapter @Inject constructor (private val imageLoader: ImageLoader) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADER_ITEM_VIEW = 0
    private val PRODUCT_ITEM_VIEW = 1
    private val items = mutableListOf<ProductTypeAndData>()
    var onItemClick: ((Product) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {

        return when (items[position].type) {
            CategoryType.PRODUCT -> PRODUCT_ITEM_VIEW
            CategoryType.CATEGORY -> HEADER_ITEM_VIEW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == HEADER_ITEM_VIEW) {
            val itemView = layoutInflater.inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(itemView)
        } else {
            val itemView = layoutInflater.inflate(R.layout.list_item_product, parent, false)
            ProductViewHolder(itemView)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (item.type == CategoryType.CATEGORY) {
            val headerHolder = holder as HeaderViewHolder
            item.categoryName?.let {
                headerHolder.bind(it)
            }
        } else {
            val productHolder = holder as ProductViewHolder
            item.product?.let {
                productHolder.bind(it)
            }
        }
    }

    fun updateList(data: MutableList<ProductTypeAndData>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImageView: ImageView = itemView.findViewById(R.id.iv_product_image)
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_item_name)
        private val priceTextView: TextView = itemView.findViewById(R.id.tv_item_price)

        init {
            itemView.setOnClickListener {
                items[adapterPosition].product?.let {
                    onItemClick?.invoke(it)
                }
            }
        }

        fun bind(product: Product) {
            imageLoader.loadImage(product.url, productImageView)
            nameTextView.text = product.name
            priceTextView.text = joinTwoStrings(product.salePrice.amount, product.salePrice.currency)
        }

    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerTextView: TextView = itemView.findViewById(R.id.tv_header_title)

        fun bind(title: String) {
            headerTextView.text = title
        }
    }
}