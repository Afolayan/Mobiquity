package com.oluwafemi.mobcategories.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.oluwafemi.mobcategories.R
import com.oluwafemi.mobcategories.data.remote.DataState
import com.oluwafemi.mobcategories.ui.product.model.ProductTypeAndData
import com.oluwafemi.mobcategories.ui.product.vm.ProductVM
import com.oluwafemi.mobcategories.ui.shared.PagesFragment
import com.oluwafemi.mobcategories.util.Constants
import com.oluwafemi.mobcategories.util.ImageLoader
import kotlinx.android.synthetic.main.product_list.*
import kotlinx.android.synthetic.main.progress_layout.*
import javax.inject.Inject

class ProductListFragment: PagesFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var imageLoader: ImageLoader
    @Inject lateinit var adapter: ProductRecyclerViewAdapter
    private lateinit var viewModel: ProductVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[ProductVM::class.java]
        viewModel.retrieveData()
        observeViewModelValue()
        setupRecyclerView()
    }

    private fun observeViewModelValue() {
        viewModel.dataState.observe(this, Observer { state ->
            when (state) {
                is DataState.Loading -> showLoading()
                is DataState.Success -> displayList(state.categories)
                is DataState.Error -> displayErrorMessage(state.message)
            }
        })
    }

    private fun setupRecyclerView() {
        recycler_view.adapter = adapter

        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    0 -> 2
                    else -> 1
                }
            }

        }
        recycler_view.layoutManager = layoutManager
        goToDetailView()

    }

    private fun goToDetailView() {
        adapter.onItemClick = {product ->
            Bundle().apply {
                putParcelable(Constants.DATA_KEY, product)
                navigateWithArgsTo(destId = R.id.action_productListFragment_to_detailFragment, args = this)
            }

        }
    }

    private fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun displayList(productList: MutableList<ProductTypeAndData>) {
        progress_bar.visibility = View.GONE
        adapter.updateList(productList)
    }

    private fun displayErrorMessage(errorMessage: String) {
        progress_bar.visibility = View.GONE
        Snackbar.make(container, errorMessage, Snackbar.LENGTH_LONG).apply {
            setAction(getString(R.string.retry)) {
                viewModel.retrieveData()
            }
            show()
        }
    }
}