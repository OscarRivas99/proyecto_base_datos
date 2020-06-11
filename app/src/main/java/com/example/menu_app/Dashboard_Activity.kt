package com.example.menu_app

import android.content.DialogInterface
import android.content.Intent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menu_app.Classes.Movement
import com.example.menu_app.database.DBHandler
import androidx.navigation.fragment.findNavController
import com.example.menu_app.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard_.*

class DashboardActivity : AppCompatActivity() {

    lateinit var dbHandler: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard_)


        dbHandler = DBHandler(this)

        rv_dashboard.layoutManager = LinearLayoutManager(this)

        ///////////////////////////////////////////////////////////////7


        fab_dashboard.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Add Movement")
            val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
            val movementName = view.findViewById<EditText>(R.id.ev_todo)
            val movementdate = view.findViewById<EditText>(R.id.et_username)

            dialog.setView(view)

            dialog.setPositiveButton("Add") { _: DialogInterface, _: Int ->
                if (movementName.text.isNotEmpty() && movementdate.text.isNotEmpty() ) {
                    val movement = Movement()
                    movement.name = movementName.text.toString()
                    movement.date = movementdate.text.toString()

                    dbHandler.addMovement(movement)
                    refreshList()
                }
            }
            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->

            }
            dialog.show()
        }

    }

    /////////////////////////////////////////////////////////////////////////////////

    fun updateMovement(movement: Movement){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Update Movement")
        val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
        val MovementName = view.findViewById<EditText>(R.id.ev_todo)
        //val MovementUser = view.findViewById<EditText>(R.id.et_username)
        val Movementdate = view.findViewById<EditText>(R.id.et_username)

        MovementName.setText(movement.name)
        //   MovementUser.setText(movement.username)
        Movementdate.setText(movement.date)

        dialog.setView(view)
        dialog.setPositiveButton("Update") { _: DialogInterface, _: Int ->

            if (MovementName.text.isNotEmpty() && Movementdate.text.isNotEmpty() ) {
                movement.name = MovementName.text.toString()
                //   movement.username = MovementUser.text.toString()
                movement.date = Movementdate.text.toString()

                dbHandler.updateMovement(movement)

                refreshList()
            }
        }
        dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->

        }
        dialog.show()
    }



    override fun onResume() {
        refreshList()
        super.onResume()
    }

    private fun refreshList(){
        rv_dashboard.adapter = DashboardAdapter(this,dbHandler.getMovements())
    }


    class DashboardAdapter(val activity: DashboardActivity, val list: MutableList<Movement>) :
        RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(activity).inflate(R.layout.rv_child_dashboard, p0,false))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, p1: Int ) {

            holder.user.text = list[p1].date
            holder.toDoName.text = list[p1].name
            holder.toDoName.text =   "Movimiento: "+ holder.toDoName.text
            holder.user.text =   "Usuario: "+ holder.user.text




            holder.menu.setOnClickListener {
                val popup = PopupMenu(activity,holder.menu)
                popup.inflate(R.menu.dashboard_child)
                popup.setOnMenuItemClickListener {

                    when(it.itemId){
                        R.id.menu_edit->{
                            activity.updateMovement(list[p1])
                        }


                        R.id.menu_delete->{
                            val dialog = AlertDialog.Builder(activity)
                            dialog.setTitle("Are you sure")
                            dialog.setMessage("Do you want to delete this movement ?")
                            dialog.setPositiveButton("Continue") { _: DialogInterface, _: Int ->
                                activity.dbHandler.deleteMovement(list[p1].id)
                                activity.refreshList()
                            }
                            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->

                            }
                            dialog.show()
                        }

                    }

                    true
                }
                popup.show()
            }
        }

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val toDoName: TextView = v.findViewById(R.id.tv_todo_name)
            val user: TextView = v.findViewById(R.id.tv_user)
            val menu : ImageView = v.findViewById(R.id.iv_menu)
        }
    }
        /////
    }






