package eif.viko.lt.elektronikosparduotuve

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import eif.viko.lt.elektronikosparduotuve.databinding.ActivityMainBinding
import eif.viko.lt.elektronikosparduotuve.viewmodel.ProductViewModel
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var performClick = true

    private lateinit var productViewModel: ProductViewModel

    private lateinit var listener: NavController.OnDestinationChangedListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        drawerLayout = findViewById(R.id.drawer_layout)
        binding.navView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)



        listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->

            if(destination.id == R.id.homeFragment){

            }else if(destination.id == R.id.shoppingCartFragment){

            }

        }

    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.addOnDestinationChangedListener(listener)
    }


    override fun onSupportNavigateUp(): Boolean {
        performClick = true
        Toast.makeText(this, "BACK", Toast.LENGTH_SHORT).show()
        return navController.navigateUp(appBarConfiguration) || return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.options_menu, menu)
        val menuItem: MenuItem? = menu?.findItem(R.id.myButton)
        val click = menuItem?.actionView



        click?.setOnClickListener {
            if(performClick){
               navController.navigate(R.id.shoppingCartFragment)
                performClick = false
            }
        }


        //notification_badge
        val itemsInCart = menuItem?.actionView?.findViewById<TextView>(R.id.notification_badge)

        productViewModel.getShoppingCart().observe(this, Observer {
            itemsInCart?.text = "${it.size}"
        })
        return super.onCreateOptionsMenu(menu)
    }






}