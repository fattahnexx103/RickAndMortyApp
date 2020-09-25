package com.nexx.nexxassistant.interviewapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.nexx.nexxassistant.interviewapp.R
import com.nexx.nexxassistant.interviewapp.model.CharacterResults
import com.nexx.nexxassistant.interviewapp.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerViewAdapter(var characters: ArrayList<CharacterResults>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    //Update the list by adding the new dataset to the existing list.
    fun updateAdapter(newCharacterList: List<CharacterResults>){
        characters.addAll(newCharacterList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
         LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
    )

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters.get(position))

        //To navigate into detail fragment we are passing in the item as Parcelable
        holder.itemView.item_ll.setOnClickListener {
            val action = ListFragmentDirections.actionListFragment2ToDetailFragment(characters.get(position))
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

         private val imageView = view.imageView
         private val characterName = view.item_name
         private val characterStatus = view.item_status
         private val characterSpecies = view.item_species

         //this bind method is called in the onBindViewHolder
         fun bind(character: CharacterResults){

             characterName.text = character.name
             characterSpecies.text = character.species
             characterStatus.text = character.status
             imageView.loadImage(character.imageUrl)
         }

    }


}