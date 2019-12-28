package com.weatherapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.weatherapp.R
import com.weatherapp.databinding.HomeFragmentBinding
import com.weatherapp.util.EventHandler
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment:Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var dataBinding: HomeFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)

        homeViewModel = ViewModelProviders.of(this,viewModelFactory).
            get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.home_fragment,container,false)

        dataBinding = HomeFragmentBinding.bind(root).apply {
            homeVM = homeViewModel
        }

        dataBinding.lifecycleOwner = viewLifecycleOwner

        setHasOptionsMenu(true)

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupErrorHandler()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)
        setupSearchAction(menu)
    }

    private fun setupSearchAction(menu: Menu) {
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setIconifiedByDefault(true)

        /*
            setup close button listener
         */
        searchView.findViewById<ImageView>(R.id.search_close_btn).setOnClickListener {
            searchView.onActionViewCollapsed()
        }

        /*
            setup text change listener
         */
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()

                searchView.onActionViewCollapsed()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                homeViewModel.search(newText!!)

                return true
            }
        })
    }

    private fun setupErrorHandler() {
        homeViewModel.error.observe(viewLifecycleOwner, EventHandler {
            var error = it.toString()

            if(it is Int){
                //get string from resource if type is Int
                error = getString(it)
            }

            Snackbar.make(dataBinding.root,error, Snackbar.LENGTH_SHORT).show()
        })
    }

}