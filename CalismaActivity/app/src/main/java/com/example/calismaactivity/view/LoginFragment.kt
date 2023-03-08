package com.example.calismaactivity.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.calismaactivity.R
import com.example.calismaactivity.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        uniAra.setOnClickListener {
            if (countryName.text.isEmpty()){
                Toast.makeText(view.context,"Lütfen bir ülke ad giriniz..",Toast.LENGTH_SHORT).show()
            }else{
                val action =LoginFragmentDirections.actionLoginFragmentToMainFragment(countryName.text.toString())
                Navigation.findNavController(view).navigate(action)
                countryName.text.clear()
            }
        }
    }
}