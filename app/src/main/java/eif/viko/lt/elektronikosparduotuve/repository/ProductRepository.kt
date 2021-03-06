package eif.viko.lt.elektronikosparduotuve.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eif.viko.lt.elektronikosparduotuve.model.Product

object ProductRepository {
    private val db = mutableListOf<Product>()
    private val cart = mutableListOf<Product>()
    var updatedList = MutableLiveData<List<Product>>()
    var shopingCart = MutableLiveData<List<Product>>()



    fun getProducts(): LiveData<List<Product>> {
        db.clear()
        load()
        updatedList.value = db
        return updatedList
    }
    fun add(product: Product) {
        cart.add(product)
        shopingCart.value = cart
    }
    fun remove(product: Product){
        cart.remove(product)
        shopingCart.value = cart
    }



    fun getShopingCart(): LiveData<List<Product>>{
        return shopingCart
    }



    // Webserviso ar kitu vietu duomenys
    fun load() {
        db.add(Product(1, 20.54, "1.5 TDI", "Pirmas", "https://loremflickr.com/220/240"))
        db.add(Product(2, 23.54, "1 TDI", "Antras", "https://loremflickr.com/320/240"))
        db.add(Product(3, 34.54, "1.5 TDI", "Trecias", "https://loremflickr.com/180/240"))
        db.add(Product(4, 55.54, "1.5 TDI", "Ketvirtas", "https://loremflickr.com/420/240"))
    }
}