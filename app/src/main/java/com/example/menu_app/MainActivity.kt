package com.example.menu_app

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menu_app.Classes.Movement
import com.example.menu_app.database.DBHandler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_home.*

import kotlinx.android.synthetic.main.fragment_metodos.*
import kotlinx.android.synthetic.main.rv_child_dashboard.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var dbHandler: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
       setSupportActionBar(toolbar)



    val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        ////////////////////////////////////////////////////////////////////

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


        /////////////////////////////////////////////////////////////////////

        //borre drawerlayout
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_categoria, R.id.nav_metodos, R.id.nav_avisos),drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    ///////////////////////////////////////////////// Dashboard movements methods


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


    class DashboardAdapter(val activity: MainActivity, val list: MutableList<Movement>) :
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
}


    ////////////////////////////////////////////////////










