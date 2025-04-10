package com.example.moviles1_practico1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviles1_practico1.databinding.ItemTareaBinding
import com.example.moviles1_practico1.ui.Repositories.TareaRepository
import com.example.practico1.models.Estado
import com.example.practico1.models.Tarea

class TareaAdapter(
    private var listaTareas: List<Tarea>,
    private val tareaClickListener: TareaClickListener
) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    inner class TareaViewHolder(val binding: ItemTareaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val binding = ItemTareaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TareaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = listaTareas[position]
        holder.binding.tvTitulo.text = tarea.titulo
        holder.binding.tvTexto.text=tarea.texto
        holder.binding.root.setBackgroundColor(tarea.color)

        holder.binding.checkboxEstado.isChecked = tarea.estado == Estado.CUMPLIDO

        holder.binding.checkboxEstado.setOnCheckedChangeListener { _, isChecked ->

            tarea.estado = if (isChecked) Estado.CUMPLIDO else Estado.NO_CUMPLIDO

            TareaRepository.guardarTarea(tarea, position)
        }
        holder.binding.btnEliminarTarea.setOnClickListener {
            tareaClickListener.onEliminarTareaClick(position)

        }
        holder.binding.root.setOnClickListener {
            tareaClickListener.onTareaClick(tarea, position)

        }
    }

    override fun getItemCount(): Int {
        return listaTareas.size
    }

    fun actualizarLista(nuevasTareas: List<Tarea>) {
        listaTareas = nuevasTareas
        notifyDataSetChanged()
    }
}
interface TareaClickListener {
    fun onTareaClick(tarea: Tarea, posicion: Int)
    fun onEliminarTareaClick(posicion: Int)
}
