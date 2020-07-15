package com.giosoft.apptragos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.giosoft.apptragos.AppDatabase
import com.giosoft.apptragos.R
import com.giosoft.apptragos.data.DataSource
import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.data.models.DrinkEntity
import com.giosoft.apptragos.domain.RepoImpl
import com.giosoft.apptragos.ui.viewmodel.MainViewModel
import com.giosoft.apptragos.ui.viewmodel.VMFactory
import com.giosoft.apptragos.vo.Resource
import kotlinx.android.synthetic.main.fragment_details_cocktail.*
import kotlinx.android.synthetic.main.fragment_favourites.*

class DrinkDetailsFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>{ VMFactory(RepoImpl(DataSource(AppDatabase.getDatabase(requireActivity().applicationContext)))) }
    private lateinit var drink: Drink
    private var favDrink: DrinkEntity? = DrinkEntity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")!!
            Log.d("DETALLES_FRAG", "$drink")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_cocktail, container, false)
    }//onCreateView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.imagen).into(img_drink)
        txt_drink_title.text = drink.nombre
        txt_has_alcohol.text = if (drink.hasAlcohol == "Non alcoholic") "Sin Alcohol" else "Con alcohol"
        txt_drink_desc.text = drink.descripcion
        setupFavDrink()

        btn_favorito.setOnClickListener {
            if (favDrink?.idDrink.isNullOrEmpty()){
                //No está en favoritos
                viewModel.saveDrink(DrinkEntity(drink.idDrink, drink.imagen, drink.nombre, drink.descripcion, drink.hasAlcohol))
                setupFavDrink()
                Toast.makeText(requireContext(), "Trago guardado en favoritos", Toast.LENGTH_SHORT).show()
            } else {
                //Está en favoritos
                viewModel.deleteDrink(favDrink!!)
                setupFavDrink()
                Toast.makeText(requireContext(), "Trago eliminado de favoritos", Toast.LENGTH_SHORT).show()
            }
        }
    }//onViewCreated()

    private fun setupFavDrink(){
        viewModel.getFavouriteDrinkById(drink.idDrink).observe(viewLifecycleOwner, Observer {resFavDrink ->
            when(resFavDrink){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    resFavDrink.data.let {
                            favDrink = it
                    }
                    if (favDrink?.idDrink.isNullOrEmpty()){
                        //No está en favoritos
                        btn_favorito.setImageResource(R.drawable.ic_unstar_24)
                    } else {
                        //Está en favoritos
                        btn_favorito.setImageResource(R.drawable.ic_star_24)
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}