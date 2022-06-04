package com.example.teqelmasr.displaySellerProducts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teqelmasr.databinding.FragmentDisplaySellerProductsBinding
import com.example.teqelmasr.displaySellerProducts.viewModel.MyProductsViewModel
import com.example.teqelmasr.displaySellerProducts.viewModel.MyProductsViewModelFactory
import com.example.teqelmasr.model.Product
import com.example.teqelmasr.model.ProductItem
import com.example.teqelmasr.model.Repository
import com.example.teqelmasr.network.Client

class DisplaySellerProductsFragment : Fragment(), OnBtnListener {

    private val binding by lazy { FragmentDisplaySellerProductsBinding.inflate(layoutInflater) }
    private val factory by lazy { MyProductsViewModelFactory(Repository.getInstance(Client.getInstance(),requireContext())) }
    //private val viewModel by lazy { ViewModelProvider(requireActivity(), factory)[MyProductsViewModel::class.java] }
    private lateinit var adapter: MyProductsAdapter
    private lateinit var viewModel: MyProductsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity(), factory)[MyProductsViewModel::class.java]
        adapter = MyProductsAdapter(requireContext(),this)
        setUpRecyclerView()

        observeMyProducts()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        observeMyProducts()
    }
    private fun observeMyProducts() {

        viewModel.myProducts.observe(viewLifecycleOwner){
            fillData(it)
        }
    }

    private fun fillData(productItem: ProductItem) = binding.apply {
        (myProductsRecycler.adapter as MyProductsAdapter).setData(productItem.products!!)
        shimmer.stopShimmer()
        shimmer.visibility = View.GONE
    }

    private fun setUpRecyclerView() = binding.apply {
        myProductsRecycler.layoutManager = LinearLayoutManager(requireContext())
        myProductsRecycler.adapter = adapter
    }

    override fun onDeleteClick(product: Product) {
        viewModel.deleteProduct(product)
    }

    override fun onDetailsClick(product: Product) {
        val action: NavDirections = DisplaySellerProductsFragmentDirections.actionDisplaySellerProductsFragmentToDetailsSellerProductFragment(product)
        binding.root.findNavController().navigate(action)
    }


}