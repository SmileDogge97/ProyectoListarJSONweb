package com.example.mijson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var lista:RecyclerView ?= null
    val personajes = ArrayList<Personaje>()
    var adaptador = AdaptadorCustom(this, personajes)
    var layoutManager:RecyclerView.LayoutManager ?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        if (ValidarR.hayRed(this)){
            //solicitudHTTP("http://10.0.0.5/JSON/clientes.json")
            solicitudHTTP("https://personajesmalcolm.000webhostapp.com/clientes.json")
        }else{
            Toast.makeText(this, "No hay red", Toast.LENGTH_LONG).show()
        }
    }

    private fun agregarPersonaje(nombre: String, edad: String, genero: String, email: String, localidad:String, telefono: String){
        personajes.add(Personaje("$nombre", "$edad", "$genero", "$email", "$localidad", "$telefono" ))
    }

    private fun solicitudHTTP(url:String){
        val cliente = OkHttpClient()
        val solicitud = okhttp3.Request.Builder().url(url).build()

        cliente.newCall(solicitud).enqueue(object: okhttp3.Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                //el error
                Log.e("El error ", "sin respuesta")
            }

            override fun onResponse(call: Call?, response: Response) {
                var result = response.body().string()
                //Log.d("Json crudo ", result)

                //con esto el código que vaya debajo, vuelvo a pasar al thread principal del aplicativo
                //y ejecute el código que defina
                this@MainActivity.runOnUiThread {
                    if (response.isSuccessful()){
                    try {
                        val gson = Gson()
                        val res = gson.fromJson(result, Contact::class.java)

                        var tamaño= res.Contactos.size
                        for (i in 0..(tamaño-1)){
                            agregarPersonaje(
                                "${res.Contactos.get(i).nombre}",
                                "${res.Contactos.get(i).edad}",
                                "${res.Contactos.get(i).genero}",
                                "${res.Contactos.get(i).email}",
                                "${res.Contactos.get(i).localidad}",
                                "${res.Contactos.get(i).telefono}"
                            )

                            /*Log.d("Personaje $i",
                                    personajes.get(i).nombre + ", "
                                            + personajes.get(i).edad + ", "
                            + personajes.get(i).genero + ", "
                            + personajes.get(i).email + ", "
                            + personajes.get(i).localidad + ", "
                                            + personajes.get(i).telefono
                            )*/

                        }
                        inflar()
                    } catch (e: Exception){

                    }
                } else{ Log.e("El error ", "No se pudo conectar") }
                }
            }
        })
    }

    private fun inflar(){
        //aquí configuro el recycler View
        lista = findViewById(R.id.lista)
        lista?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager
        adaptador = AdaptadorCustom(this, personajes)
        lista?.adapter = adaptador
        //Log.d("lista","paso la lista")
    }
}
