package eif.viko.lt.elektronikosparduotuve.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import eif.viko.lt.elektronikosparduotuve.R
import eif.viko.lt.elektronikosparduotuve.adapter.CartListAdapter
import eif.viko.lt.elektronikosparduotuve.adapter.ProductListAdapter
import eif.viko.lt.elektronikosparduotuve.databinding.FragmentShoppingCartBinding
import eif.viko.lt.elektronikosparduotuve.model.Product
import eif.viko.lt.elektronikosparduotuve.viewmodel.ProductViewModel

class ShoppingCartFragment : Fragment(R.layout.fragment_shopping_cart), CartListAdapter.Interaction {


    private lateinit var productViewModel: ProductViewModel

    private var _binding: FragmentShoppingCartBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        val cartListAdapter: CartListAdapter by lazy { CartListAdapter(this) }

        productViewModel.getShoppingCart().observe(viewLifecycleOwner, Observer {
            cartListAdapter.swapData(it)
        })

        binding.shoppingCartRecycleview.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = cartListAdapter
        }

    }

    override fun clickButton(product: Product) {

    }

    override fun removeButtonClicked(product: Product) {
       productViewModel.remove(product)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}