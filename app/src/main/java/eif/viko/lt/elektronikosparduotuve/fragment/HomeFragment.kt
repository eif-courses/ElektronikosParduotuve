package eif.viko.lt.elektronikosparduotuve.fragment

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import eif.viko.lt.elektronikosparduotuve.R
import eif.viko.lt.elektronikosparduotuve.adapter.ProductListAdapter
import eif.viko.lt.elektronikosparduotuve.model.Product
import eif.viko.lt.elektronikosparduotuve.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home), ProductListAdapter.Interaction {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var topMenu: Menu
    private lateinit var counter: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)



        val productAdapter: ProductListAdapter  by lazy { ProductListAdapter(this) }

        productViewModel.getProducts().observe(viewLifecycleOwner, Observer {
            productAdapter.swapData(it)
        })

        product_recycleview.apply {
            layoutManager = GridLayoutManager(this@HomeFragment.context, 1)
            adapter = productAdapter
        }



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun addToCart(product: Product) {
        Toast.makeText(context, "Sekmingai prideta i krepseli", Toast.LENGTH_LONG).show()

        productViewModel.add(product)

//        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
//
//
//
//
//
//        val m: MenuItem? = topMenu?.findItem(R.id.myButton);
//
//
//        var i = 0
//        counter = m?.actionView!!.findViewById(R.id.notification_badge)
//
//        counter.text = i++.toString()



    }

    override fun viewDetails(product: Product) {
        val action = HomeFragmentDirections.actionHomeFragmentToViewDetailsFragment(product)
        findNavController().navigate(action)
    }

    override fun clickOnItem(product: Product) {
        Toast.makeText(context, "$product", Toast.LENGTH_LONG).show()
    }
}