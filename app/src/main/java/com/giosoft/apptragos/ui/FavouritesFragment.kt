package com.giosoft.apptragos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlinx.android.synthetic.main.fragment_favourites.*


class FavouritesFragment : Fragment(), MainAdapter.OnDrinkClickListener {

    private val viewModel by viewModels<MainViewModel>{ VMFactory(
        RepoImpl(
            DataSource(
                AppDatabase.getDatabase(requireActivity().applicationContext)
            )
        )
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

    }//onViewCreated()

    private fun setupObservers(){
        viewModel.getFavouritesDrinks().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val list = result.data.map {
                        Drink(it.idDrink, it.imagen, it.nombre, it.descripcion, it.hasAlcohol)
                    }
                    rv_favourites.adapter = MainAdapter(requireContext(), list, this)
                }
                is Resource.Failure -> {}
            }
        })
    }
    private fun setupRecyclerView(){
        rv_favourites.layoutManager = LinearLayoutManager(requireContext())
        rv_favourites.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    override fun onDrinkClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)
        findNavController().navigate(R.id.action_favouritesFragment_to_detailsCocktail,bundle)
    }
}