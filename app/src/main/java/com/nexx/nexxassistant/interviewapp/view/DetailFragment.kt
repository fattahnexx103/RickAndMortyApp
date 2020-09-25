package com.nexx.nexxassistant.interviewapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nexx.nexxassistant.interviewapp.R
import com.nexx.nexxassistant.interviewapp.model.CharacterResults
import com.nexx.nexxassistant.interviewapp.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    var character: CharacterResults? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //if bundle is not null, get the object from ListFragment
        arguments?.let {
            character = DetailFragmentArgs.fromBundle(it).character
        }

        //Set values in the view
        detail_item_name.text = character?.name
        detail_item_location_name.text = character?.location?.locationName

    }

}
