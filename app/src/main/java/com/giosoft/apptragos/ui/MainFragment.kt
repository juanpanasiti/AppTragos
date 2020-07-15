package com.giosoft.apptragos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.giosoft.apptragos.AppDatabase
import com.giosoft.apptragos.R
import com.giosoft.apptragos.data.DataSource
import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.domain.RepoImpl
import com.giosoft.apptragos.ui.viewmodel.MainViewModel
import com.giosoft.apptragos.ui.viewmodel.VMFactory
import com.giosoft.apptragos.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainAdapter.OnDrinkClickListener {

    private val viewModel by viewModels<MainViewModel>{ VMFactory(RepoImpl(DataSource(
        AppDatabase.getDatabase(requireActivity().applicationContext)
    ))) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }//onCreateView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        setUpObservers()
        btn_ir_favoritos.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favouritesFragment)
        }
    }

    private fun setUpObservers(){
        viewModel.fetchDrinkList.observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    if(!result.data.isNullOrEmpty()){
                        rv_drinks.adapter = MainAdapter(requireContext(),result.data,this)
                    } else {
                        Toast.makeText(requireContext(), "La búsqueda no generó resultados", Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(),"Ocurrió un error al traer los datos ${result.exception}",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDrinkClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        findNavController().navigate(R.id.action_mainFragment_to_detailsCocktail,bundle)
    }

    private fun setupRecyclerView(){
        rv_drinks.layoutManager = LinearLayoutManager(requireContext())
        rv_drinks.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }//setupRecyclerView()

    private fun setupSearchView(){
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.setDrink((p0!!))
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }


        })
    }
}


