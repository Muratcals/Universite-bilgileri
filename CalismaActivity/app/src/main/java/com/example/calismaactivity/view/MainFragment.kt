package com.example.calismaactivity.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calismaactivity.R
import com.example.calismaactivity.adapter.UniversityNameRecycler
import com.example.calismaactivity.model.University
import com.example.calismaactivity.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var adapter =UniversityNameRecycler(arrayListOf())
    private var yedek=ArrayList<University>()
    private var yedek2=ArrayList<University>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            val countryName =MainFragmentArgs.fromBundle(it!!).countryName
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            mainRefresh.setOnRefreshListener {
                searchEditText.text.clear()
                mainRefresh.isRefreshing=false
                viewModel.getUniversity(countryName)
            }
            searchEditText.addTextChangedListener {
                yedek.clear()
                yedek.addAll(viewModel.university.value!!)
                if (it!=null){
                    yedek2.clear()
                    var x =0
                    while(x<yedek.size){
                        val veri =yedek[x].name.toLowerCase().contains(it.toString())
                        if (veri){
                            yedek2.add(yedek[x])
                        }
                        x+=1
                    }
                    adapter.refreshAdapter(yedek2)
                }
            }
            viewModel.getUniversity(countryName)
            observerItem(view)
        }
    }

    fun observerItem(view: View){
        viewModel.university.observe(viewLifecycleOwner, Observer {
            adapter= UniversityNameRecycler(it)
            universityNameRecycler.visibility=View.VISIBLE
            universityNameRecycler.adapter=adapter
            universityNameRecycler.layoutManager=LinearLayoutManager(view.context)
        })
        viewModel.hataMesaji.observe(viewLifecycleOwner, Observer {
            if (it==true){
                mainHataMesaji.visibility=View.VISIBLE
                universityNameRecycler.visibility=View.INVISIBLE
                mainProgress.visibility=View.GONE
            }else{
                mainHataMesaji.visibility=View.GONE
            }
        })
        viewModel.progresss.observe(viewLifecycleOwner, Observer {
            if (it ==true){
                mainProgress.visibility=View.VISIBLE
                universityNameRecycler.visibility=View.GONE
                mainHataMesaji.visibility=View.GONE
            }else{
                mainProgress.visibility=View.GONE
            }
        })
        viewModel.toolbar.observe(viewLifecycleOwner, Observer {
            if (it==true){
                toolbar.visibility=View.VISIBLE
            }else{
                toolbar.visibility=View.GONE
            }
        })
    }
}