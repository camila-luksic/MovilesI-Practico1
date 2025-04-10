package com.example.moviles1_practico1.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.moviles1_practico1.databinding.ActivityTareaDetalleBinding
import com.example.moviles1_practico1.ui.Repositories.TareaRepository
import com.example.practico1.models.Estado
import com.example.practico1.models.Tarea

class TareaDetalleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTareaDetalleBinding
    private var tarea: Tarea? = null
    private var posicion: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTareaDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        tarea = intent.getSerializableExtra("tarea") as? Tarea
        posicion = intent.getIntExtra("posicion", -1)

        val estados = Estado.values().map { it.name }
        binding.spinnerEstado.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estados)


        tarea?.let {
            binding.etTitulo.setText(it.titulo)
            binding.etTexto.setText(it.texto)
            binding.spinnerEstado.setSelection(it.estado.ordinal)
            binding.viewColor.setBackgroundColor(it.color)
        }
        binding.viewColor.setOnClickListener {
            mostrarSelectorDeColor()
        }

        binding.btnGuardar.setOnClickListener {

            val titulo = binding.etTitulo.text.toString()
            val texto = binding.etTexto.text.toString()

            if (titulo.isBlank() || texto.isBlank()) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val estadoSeleccionado = Estado.valueOf(binding.spinnerEstado.selectedItem.toString())
            val colorSeleccionado = (binding.viewColor.background as ColorDrawable).color

            val nuevaTarea = Tarea(
                titulo,
                texto,
                estadoSeleccionado,
                colorSeleccionado
            )

            if (posicion != -1) {
                TareaRepository.guardarTarea(nuevaTarea, posicion)
            } else {

                TareaRepository.guardarTarea(nuevaTarea)
            }

            finish()
        }
    }
    private fun mostrarSelectorDeColor() {

        val colores = arrayOf("Rojo", "Lima", "Azul", "Amarillo", "Naranja", "Morado","Cyan","Gris","Rosa","Verde")
        val coloresHex = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.rgb(255, 165, 0),Color.MAGENTA,Color.CYAN,Color.GRAY,Color.rgb(255, 153, 204),Color.rgb(0, 102, 0))


        val builder = AlertDialog.Builder(this)
        builder.setTitle("Seleccionar color")
            .setItems(colores) { _, which ->
                binding.viewColor.setBackgroundColor(coloresHex[which])
                Toast.makeText(this, "Color seleccionado: ${colores[which]}", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
    companion object {
        fun newIntent(context: Context, tarea: Tarea, posicion: Int): Intent {
            val intent = Intent(context, TareaDetalleActivity::class.java)
            intent.putExtra("tarea", tarea)
            intent.putExtra("posicion", posicion)
            return intent
        }
    }
}

