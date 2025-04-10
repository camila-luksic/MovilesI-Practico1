package com.example.moviles1_practico1.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviles1_practico1.databinding.ActivityTareaListaBinding
import com.example.moviles1_practico1.ui.Repositories.TareaRepository
import com.example.moviles1_practico1.ui.adapters.TareaAdapter
import com.example.moviles1_practico1.ui.adapters.TareaClickListener
import com.example.practico1.models.Tarea

class TareaListaActivity : AppCompatActivity(),TareaClickListener {
    private lateinit var binding: ActivityTareaListaBinding
    private lateinit var adapter: TareaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTareaListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TareaAdapter(
            TareaRepository.getTareas(),
            this
            //{ tarea, posicion ->

               // val intent = Intent(this, TareaDetalleActivity::class.java)
                //intent.putExtra("tarea", tarea)
                //intent.putExtra("posicion", posicion)
                //startActivity(intent)
           // },
            //{ posicion ->

              //  eliminarTarea(posicion)
            //}
        )

        binding.recyclerTareas.layoutManager = LinearLayoutManager(this)
        binding.recyclerTareas.adapter = adapter

        binding.btnAgregar.setOnClickListener {
            val intent = Intent(this, TareaDetalleActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onTareaClick(tarea: Tarea, posicion: Int) {
        val intent = TareaDetalleActivity.newIntent(this, tarea, posicion)
        startActivity(intent)
       // val intent = Intent(this, TareaDetalleActivity::class.java)
        //intent.putExtra("tarea", tarea)
        //intent.putExtra("posicion", posicion)
        //startActivity(intent)
    }

    override fun onEliminarTareaClick(posicion: Int) {
        eliminarTarea(posicion)
    }
    private fun eliminarTarea(posicion: Int) {
        if (posicion != -1) {
            TareaRepository.eliminarTareaPorPosicion(posicion)
            adapter.notifyItemRemoved(posicion)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.actualizarLista(TareaRepository.getTareas())
    }
}
