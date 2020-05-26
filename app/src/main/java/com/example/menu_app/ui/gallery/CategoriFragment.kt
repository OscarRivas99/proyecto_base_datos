package com.example.menu_app.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.menu_app.R
import com.example.menu_app.databinding.FragmentCategoriaBinding
import com.example.menu_app.databinding.FragmentHomeBinding

class CategoriFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val binding = DataBindingUtil.inflate<FragmentCategoriaBinding>(inflater, R.layout.fragment_categoria, container, false)
        return binding.root
    }
}
