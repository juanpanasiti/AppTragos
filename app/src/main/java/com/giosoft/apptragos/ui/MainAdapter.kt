package com.giosoft.apptragos.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giosoft.apptragos.R
import com.giosoft.apptragos.base.BaseViewHolder
import com.giosoft.apptragos.data.models.Drink
import kotlinx.android.synthetic.main.drinks_row.view.*

class MainAdapter(private val context: Context, private val drinkList:List<Drink>, private val itemClickListener:OnDrinkClickListener): RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnDrinkClickListener{
        fun onDrinkClick(drink:Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.drinks_row,parent,false))
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(drinkList[position],position)
        }
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<Drink>(itemView){
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.imagen).centerCrop().into(itemView.img_drink)
            itemView.txt_titulo.text = item.nombre
            itemView.txt_descripcion.text = item.descripcion
            itemView.setOnClickListener { itemClickListener.onDrinkClick(item) }
        }

    }
}