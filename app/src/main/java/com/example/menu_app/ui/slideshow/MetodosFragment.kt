package com.example.menu_app.ui.slideshow

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.menu_app.R
import com.example.menu_app.databinding.FragmentMetodosBinding
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.fragment_metodos.*
import java.util.*

class MetodosFragment : Fragment() {



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        setup_title()
        val binding = DataBindingUtil.inflate<FragmentMetodosBinding>(inflater, R.layout.fragment_metodos, container, false)
        val sendMount = binding.sendMountButton
        val et_mount = binding.etMount
        val date_button = binding.showDatepickerButton
        val et_date = binding.labelDate

        sendMount.setOnClickListener {
            Toast.makeText(context, "Monto: ${et_mount.text.toString()}", Toast.LENGTH_SHORT).show()
        }


        date_button.setOnClickListener {
            ShowDatePicker()
        }



        return binding.root

    }
    private fun setup_title(){
        (activity as AppCompatActivity)
            .supportActionBar?.title = "Movement Detail"
    }

    private fun ShowDatePicker(){

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datepicker = DatePickerDialog( view!!.context, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            Toast.makeText(context, "Day:" + dayOfMonth + "\nMonth: " + (month+1) + "\nYear: " + year, Toast.LENGTH_SHORT).show()
        }, year , month , day)

         datepicker.show()
    }


}
