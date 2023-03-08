package com.example.calismaactivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.calismaactivity.R
import com.example.calismaactivity.model.University
import com.example.calismaactivity.view.MainFragment
import com.example.calismaactivity.view.MainFragmentDirections
import kotlinx.android.synthetic.main.mainfragment_view.view.*

class UniversityNameRecycler(var universityName:List<University>):RecyclerView.Adapter<UniversityNameRecycler.UniversityName>(){

    class UniversityName(view:View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityName {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.mainfragment_view,parent,false)
        return UniversityName(view)
    }

    override fun onBindViewHolder(holder: UniversityName, position: Int) {
        holder.itemView.university_name.text=universityName[position].name
        holder.itemView.setOnClickListener {
            val arrayList= (universityName[position].name)
            val action =MainFragmentDirections.actionMainFragmentToUniversityFragment(arrayList)
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return universityName.size
    }

    fun refreshAdapter(universityy:ArrayList<University>){
        universityName=universityy
        notifyDataSetChanged()
    }
}