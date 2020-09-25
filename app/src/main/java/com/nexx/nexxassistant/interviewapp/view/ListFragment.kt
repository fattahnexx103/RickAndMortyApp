package com.nexx.nexxassistant.interviewapp.view


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.nexx.nexxassistant.interviewapp.R
import com.nexx.nexxassistant.interviewapp.viewmodel.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    lateinit var viewModel: CharacterViewModel
    private val listAdapter = RecyclerViewAdapter(arrayListOf())

    private var isMarked = false //this is to not call the viewmodel when user navigates from detail screen back to list screen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI() //setUp the UI

        //If first time this is called, set the view model
        if(!isMarked){
            setUpViewModel()
            viewModel.fetchCharacters()
            isMarked = true //change is marked to true

        }

        list_recyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                //This gets triggered for pagination purposes. When end of list happens, this is called.
                if(!recyclerView.canScrollVertically(1) && newState ==RecyclerView.SCROLL_STATE_IDLE){
                    // Toast.makeText(context,"End of list",Toast.LENGTH_SHORT).show()
                    viewModel.fetchCharacters() //fetch the next set of characters
                }
            }
        })

    }

    //Sets up the properties of the recyclerview
    private fun setUpUI(){


            list_recyclerview.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = listAdapter
            }
    }

    //sets up the viewmodel and calls the observe method.
    private fun setUpViewModel(){


        viewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        observeViewModel()

    }

    //Observe the viewmodel.
    private fun observeViewModel(){

        viewModel.charactersLD.observe(this, Observer { characters ->
            characters?.let {
                listAdapter.updateAdapter(it.results) //update the recyclerview with the results.

            }
        })


    }



}
