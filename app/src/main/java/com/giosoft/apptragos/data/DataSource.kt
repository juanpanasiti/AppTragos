package com.giosoft.apptragos.data

import com.giosoft.apptragos.data.models.Drink
import com.giosoft.apptragos.vo.Resource

class DataSource {
    val generateDrinkList = Resource.Success(listOf(
        Drink("https://cdn5.recetasdeescandalo.com/wp-content/uploads/2018/09/Coctel-margarita-como-prepararlo.-Receta-e-ingredientes.jpg","Margarita", "Con azucar, vodka y nueces"),
        Drink("https://franchoxbar.files.wordpress.com/2018/07/img_20180630_181858_826.jpg","Fernet", "Fernet con coca y 2 hielos"),
        Drink("https://k33.kn3.net/taringa/D/7/F/A/4/7/cocopumita/588.jpg","Toro", "Toro con Pritty"),
        Drink("https://i.ytimg.com/vi/V-37t0GmqYE/maxresdefault.jpg","Gancia", "Gancia con Sprite")
    ))

}