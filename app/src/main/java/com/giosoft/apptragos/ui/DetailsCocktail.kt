package com.giosoft.apptragos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.giosoft.apptragos.R
import com.giosoft.apptragos.data.models.Drink
import kotlinx.android.synthetic.main.fragment_details_cocktail.*

class DetailsCocktail : Fragment() {

    private lateinit var drink: Drink

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
    }//onViewCreated()

}