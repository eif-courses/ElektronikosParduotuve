package eif.viko.lt.elektronikosparduotuve.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import eif.viko.lt.elektronikosparduotuve.R
import kotlinx.android.synthetic.main.fragment_view_details.*


class ViewDetailsFragment : Fragment(R.layout.fragment_view_details) {

    val args: ViewDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        details_text.text = args.product?.description



    }
}