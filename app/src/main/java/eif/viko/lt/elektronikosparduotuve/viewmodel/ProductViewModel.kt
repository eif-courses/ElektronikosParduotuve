package eif.viko.lt.elektronikosparduotuve.viewmodel

import androidx.lifecycle.ViewModel
import eif.viko.lt.elektronikosparduotuve.model.Product
import eif.viko.lt.elektronikosparduotuve.repository.ProductRepository

class ProductViewModel : ViewModel(){
    private val repository = ProductRepository

    fun getProducts() = repository.getProducts()

    fun getShoppingCart() = repository.getShopingCart()

    fun add(product: Product) = repository.add(product)

}