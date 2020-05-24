package com.example.menu_app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.menu_app.R
import com.example.menu_app.databinding.FragmentHomeBinding
import com.example.menu_app.databinding.FragmentMetodosBinding


class HomeFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)
    val button = binding.categoriesButton
        button.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_categoria)
        }




        return binding.root
    }
}
