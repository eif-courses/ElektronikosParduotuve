package eif.viko.lt.elektronikosparduotuve.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eif.viko.lt.elektronikosparduotuve.api.Models3DRetrofit
import eif.viko.lt.elektronikosparduotuve.model.Item
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Models3DViewModel : ViewModel(){

    val listOfModels3DViewModel: MutableLiveData<List<Item>> = MutableLiveData()

    fun getModels(): LiveData<List<Item>>{
        Models3DRetrofit.getInstance.getModels().enqueue(object : Callback,
            retrofit2.Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if(response.isSuccessful){
                    listOfModels3DViewModel.postValue(response.body())
                }else{
                    listOfModels3DViewModel.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                listOfModels3DViewModel.postValue(null)
            }

        })
        return listOfModels3DViewModel
    }

}