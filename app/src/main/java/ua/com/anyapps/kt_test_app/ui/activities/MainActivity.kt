package ua.com.anyapps.kt_test_app.ui.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ua.com.anyapps.kt_test_app.R
import ua.com.anyapps.kt_test_app.databinding.ActivityMainBinding
import ua.com.anyapps.kt_test_app.di.App
import ua.com.anyapps.kt_test_app.network.RestApi
import ua.com.anyapps.kt_test_app.ui.fragments.DetailBookFragment
import ua.com.anyapps.kt_test_app.ui.fragments.ErrorFragment
import ua.com.anyapps.kt_test_app.ui.fragments.SearchResultFragment
import ua.com.anyapps.kt_test_app.ui.fragments.WelcomeFragment
import ua.com.anyapps.kt_test_app.utils.NavigationPages
import ua.com.anyapps.kt_test_app.viewmodel.SharedMainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var restApi: RestApi

    init {
        App.applicationComponent.inject(this)
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedMainViewModel: SharedMainViewModel
    //private val sharedVM: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        initObservers()
        initListeners()
    }

    private fun setupViewModel() {
        sharedMainViewModel = ViewModelProviders.of(this).get(SharedMainViewModel::class.java)
    }

    fun initListeners() {
        binding.btnSearch.setOnClickListener(View.OnClickListener {
            sharedMainViewModel.btnSearchClick(binding.etSearch.text.toString())
            //binding.lottieLoader.playAnimation()
            binding.btnSearch.isEnabled = false
        })
    }

    fun initObservers(){
        /*mainViewModel.docs.observe(this, Observer {
            binding.lottieLoader.cancelAnimation()
            binding.btnSearch.isEnabled = true
            Log.d("debapp", "Total books: ${it.size}")
            Log.d("debapp", "GetDocs: ${mainViewModel.getBooks()}")
            if(it.size > 0){
                searchResultFragment = SearchResultFragment.newInstance(it)

                getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFrameLayout, searchResultFragment, SEARCH_RESULT_FRAGMENT)
                    .addToBackStack(SEARCH_RESULT_FRAGMENT)
                    .commit()
            }else{
                Toast.makeText(this, getResources().getString(R.string.no_result_found), Toast.LENGTH_LONG).show()
            }
        })*/
        /*sharedMainViewModel.getBooks().observe(this) {
            Log.d("debapp", "Tot:: ${it.numFound}")
            *//*getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFrameLayout, searchResultFragment, SEARCH_RESULT_FRAGMENT)
                .addToBackStack(SEARCH_RESULT_FRAGMENT)
                .commit()*//*
        }*/

        sharedMainViewModel.getLoading().observe(this){
            when(it){
                true-> {
                    binding.lottieLoader.visibility = View.VISIBLE
                    binding.lottieLoader.playAnimation()
                    binding.btnSearch.isEnabled = false
                }
                false-> {
                    binding.lottieLoader.visibility = View.GONE
                    binding.lottieLoader.cancelAnimation()
                    binding.btnSearch.isEnabled = true
                }
            }
        }

        sharedMainViewModel.getError().observe(this){
            when(it){
                true-> {
                    Log.d("debapp", "Error !!!!!!!!!!!!!!!")
                }
            }
        }

        sharedMainViewModel.getNavigationPage().observe(this, navigationObserver)
        /*mainViewModel.error.observe(this, Observer {
            binding.lottieLoader.cancelAnimation()
            binding.btnSearch.isEnabled = true
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })*/
    }

    private val navigationObserver = Observer<NavigationPages> {
        when(it){
            NavigationPages.WELCOME->{
                Log.d("debapp", "WELCOME")
                loadFragment(WelcomeFragment())
            }
            NavigationPages.SEARCH_RESULT->{
                Log.d("debapp", "SEARCH_RESULT")
                loadFragment(SearchResultFragment())
            }
            NavigationPages.DETAIL_BOOK->{
                Log.d("debapp", "DETAIL_BOOK")
                loadFragment(DetailBookFragment())
            }
            NavigationPages.ERROR->{
                Log.d("debapp", "ERROR")
                loadFragment(ErrorFragment())
            }
            else->{
                Log.d("debapp", "Another")
            }
        }
    }

    fun loadFragment(navPageFragment: Fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFrameLayout, navPageFragment, navPageFragment.javaClass.simpleName)
                .addToBackStack(null)
                .commit()
    }
}