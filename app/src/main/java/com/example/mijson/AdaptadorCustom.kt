package com.example.mijson

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCustom(var contexto:Context, items:ArrayList<Personaje>): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>(){

    var items: ArrayList<Personaje> ?= null

    init {
        this.items = items
    }

    //crea el view holder y mete el archivo xml a la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.templatepersonajes, parent, false)
        val viewHolder = ViewHolder(vista)
        //Log.d("onCreateViewHolder ","pasó")
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdaptadorCustom.ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.nombre?.text = "Nombre: " +item?.nombre
        holder.edad?.text = "Edad: "+ item?.edad
        holder.genero?.text = "Genero: "+ item?.genero
        holder.email?.text = "Email: "+ item?.email
        holder.localidad?.text = "Localidad: "+ item?.localidad
        holder.telefono?.text = "Teléfono: "+ item?.telefono

        when(item?.nombre){
            "Craig" -> holder.foto?.setImageResource(R.drawable.craig)
            "Malcom" -> holder.foto?.setImageResource(R.drawable.malcom)
            "Francis" -> holder.foto?.setImageResource(R.drawable.francis)
            "Reese" -> holder.foto?.setImageResource(R.drawable.reese)
            "Dewey" -> holder.foto?.setImageResource(R.drawable.dewey)
            "Jamie" -> holder.foto?.setImageResource(R.drawable.jamie)
            "Stevie" -> holder.foto?.setImageResource(R.drawable.stevie)
            "Louis" -> holder.foto?.setImageResource(R.drawable.louis)
            "Hal" -> holder.foto?.setImageResource(R.drawable.hal)
            "Ida" -> holder.foto?.setImageResource(R.drawable.ida)
            else -> holder.foto?.setImageResource(R.drawable.khe)
        }
        //Log.d("onBindViewHolder ","pasó")
    }

    override fun getItemCount(): Int {
        return items?.count()!!
        //Log.d("getItemCount","pasó")
    }

    class ViewHolder(vista:View): RecyclerView.ViewHolder(vista){
        var vista = vista
        var foto:ImageView ?= null
        var nombre:TextView ?= null
        var edad:TextView ?= null
        var genero:TextView ?= null
        var email:TextView ?= null
        var localidad:TextView ?= null
        var telefono:TextView ?= null

        init {
            foto = vista.findViewById(R.id.Foto)
            nombre = vista.findViewById(R.id.ETNombre)
            edad = vista.findViewById(R.id.ETEdad)
            genero = vista.findViewById(R.id.ETGenero)
            email = vista.findViewById(R.id.ETEmail)
            localidad = vista.findViewById(R.id.ETLocalidad)
            telefono = vista.findViewById(R.id.ETTelefono)
        }
    }
}