package eif.viko.lt.elektronikosparduotuve.fragment

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import eif.viko.lt.elektronikosparduotuve.R
import eif.viko.lt.elektronikosparduotuve.adapter.Models3DListAdapter
import eif.viko.lt.elektronikosparduotuve.adapter.ProductListAdapter
import eif.viko.lt.elektronikosparduotuve.databinding.FragmentHomeBinding
import eif.viko.lt.elektronikosparduotuve.databinding.FragmentShoppingCartBinding
import eif.viko.lt.elektronikosparduotuve.model.Item
import eif.viko.lt.elektronikosparduotuve.model.Product
import eif.viko.lt.elektronikosparduotuve.viewmodel.Models3DViewModel
import eif.viko.lt.elektronikosparduotuve.viewmodel.ProductViewModel


class HomeFragment : Fragment(R.layout.fragment_home), ProductListAdapter.Interaction, Models3DListAdapter.Interaction, MediaPlayer.OnPreparedListener {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var models3DViewModel: Models3DViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }







    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        models3DViewModel = ViewModelProvider(this).get(Models3DViewModel::class.java)




        val models3DListAdapter: Models3DListAdapter  by lazy { Models3DListAdapter(this) }

        models3DViewModel.getModels().observe(viewLifecycleOwner, Observer {
            models3DListAdapter.swapData(it)
        })


//        val productAdapter: ProductListAdapter  by lazy { ProductListAdapter(this) }
//
//        productViewModel.getProducts().observe(viewLifecycleOwner, Observer {
//            productAdapter.swapData(it)
//        })

        binding.productRecycleview.apply {
            layoutManager = GridLayoutManager(this@HomeFragment.context, 1)
            adapter = models3DListAdapter
        }



    }

    override fun addToCart(product: Product) {
        Toast.makeText(context, "Sekmingai prideta i krepseli", Toast.LENGTH_LONG).show()

        productViewModel.add(product)

//
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
    override fun addToCart(item: Item) {
        productViewModel.add(Product(title = item.title, imageURL = item.poster))
    }
    override fun item_clicked(item: Item) {
        //Toast.makeText(context, "$item", Toast.LENGTH_LONG).show()

    }

    override fun preview3D(item: Item) {
        val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
        val intentUri: Uri = Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
            .appendQueryParameter(
                "file",
                item.model
            )
            .build()

        sceneViewerIntent.data = intentUri
        sceneViewerIntent.setPackage("com.google.ar.core")
        startActivity(sceneViewerIntent)
    }

    override fun playSound(item: Item) {
        val url = item.audio                ///"http://........" // your URL here
        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}