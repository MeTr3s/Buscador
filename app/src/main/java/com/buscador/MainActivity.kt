package com.buscador

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val clave = "VALOR"
    private val nombreKey = "NOMBRE"
    private val correoKey = "CORREO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nombre = findViewById<EditText>(R.id.txtNombre)
        val correo = findViewById<EditText>(R.id.txtCorreo)
        val guardar = findViewById<Button>(R.id.btnSave)
        val buscar = findViewById<Button>(R.id.btnBuscar)

        val preferencias = getSharedPreferences(clave, Context.MODE_PRIVATE)
        val editor = preferencias.edit()

        buscar.setOnClickListener {
            val nombreGuardado = preferencias.getString(nombreKey, null)
            val correoGuardado = preferencias.getString(correoKey, null)

            if (nombreGuardado != null && correoGuardado != null) {
                val mensaje = "Se ha encontrado exitosamente a $nombreGuardado y $correoGuardado"
                Alerta(mensaje)
            } else {
                val mensajeError = "Este usuario no se ha encontrado. Por favor, inténtelo nuevamente o ingrese un usuario existente."
                Alerta(mensajeError)
            }
        }

        guardar.setOnClickListener {
            val nombreText = nombre.text.toString()
            val correoText = correo.text.toString()

            editor.putString(nombreKey, nombreText)
            editor.putString(correoKey, correoText)
            editor.apply()

            nombre.setText("")
            correo.setText("")

            Toast.makeText(this, "Se ha guardado exitosamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun Alerta(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Preferencias")
        builder.setMessage(mensaje)
        builder.create().show()
    }
}
