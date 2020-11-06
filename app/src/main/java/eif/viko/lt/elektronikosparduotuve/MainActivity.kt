package eif.viko.lt.elektronikosparduotuve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.core.view.MenuItemCompat.getActionView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import eif.viko.lt.elektronikosparduotuve.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var cartCounter: TextView
    private lateinit var productViewModel: ProductViewModel

    private lateinit var listener: NavController.OnDestinationChangedListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        drawerLayout = findViewById(R.id.drawer_layout)
        nav_view.setupWithNavController(navController)

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
        return navController.navigateUp(appBarConfiguration) || return super.onSupportNavigateUp()
    }

// NOTIFICATION BADGE EXAMPLE: https://bitbucket.org/shahimtiyaj/notification-badge-count-actionbar/src/master/app/src/main/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val m: MenuItem? = menu?.findItem(R.id.myButton);
        cartCounter = m?.actionView!!.findViewById(R.id.notification_badge)

        productViewModel.getShoppingCart().observe(this, Observer {
            cartCounter.text = "${it.size}"
        })

        getActionView(m).setOnClickListener {
            navController.navigate(R.id.shoppingCartFragment)
        }


        return super.onCreateOptionsMenu(menu)
    }


}