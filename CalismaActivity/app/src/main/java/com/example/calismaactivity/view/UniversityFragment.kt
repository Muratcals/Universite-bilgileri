package com.example.calismaactivity.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import com.example.calismaactivity.R
import com.example.calismaactivity.viewModel.UniversityViewModel
import kotlinx.android.synthetic.main.fragment_university.*

class UniversityFragment : Fragment() {

    companion object {
        fun newInstance() = UniversityFragment()
    }

    private lateinit var viewModel: UniversityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_university, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UniversityViewModel::class.java)
        arguments.let {
            val universityName=UniversityFragmentArgs.fromBundle(it!!).name
            viewModel.getUniversity(universityName)
            observerItem()

        }
    }

    fun observerItem(){
        viewModel.university.observe(viewLifecycleOwner, Observer {
            universityName.text=it[0].name
            universityCountry.text=it[0].country
            val universiteUrl=it[0].webPages
            universityWebSite.text=universiteUrl[0]
            universityWebSiteUrl.setOnClickListener {
                val intent =Intent(Intent.ACTION_VIEW,universiteUrl[0].toUri())
                startActivity(intent)
            }
        })
    }

}