package eif.viko.lt.elektronikosparduotuve.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import eif.viko.lt.elektronikosparduotuve.R
import eif.viko.lt.elektronikosparduotuve.databinding.FragmentViewDetailsBinding


class ViewDetailsFragment : Fragment(R.layout.fragment_view_details) {

    val args: ViewDetailsFragmentArgs by navArgs()
    private var _binding: FragmentViewDetailsBinding? = null
    private val binding get() = _binding!!
    //private val item = args.itemObject

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailsTitle.text = args.itemObject.title
        binding.detailsDescription.text = args.itemObject.description
        binding.detailsTitle.text = args.itemObject.title
        Picasso.get().load(args.itemObject.poster).into(binding.detailsImage)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}