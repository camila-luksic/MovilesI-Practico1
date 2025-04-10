package com.example.moviles1_practico1.ui.Repositories

import com.example.practico1.models.Estado
import com.example.practico1.models.Tarea

object TareaRepository {

    private val tareas = arrayListOf(
        Tarea("Estudiar", "Repasar Kotlin", Estado.NO_CUMPLIDO, 0xFFE57373.toInt()),
        Tarea("Ir al gimnasio", "Cardio + fuerza", Estado.CUMPLIDO, 0xFF81C784.toInt())
    )

    fun getTareas(): ArrayList<Tarea> {
        return tareas
    }

    fun guardarTarea(tarea: Tarea, posicion: Int? = null) {

        if (posicion != null && posicion >= 0 && posicion < tareas.size) {
            tareas[posicion] = tarea
        } else {

            tareas.add(tarea)
        }
    }

    fun eliminarTareaPorPosicion(posicion: Int) {
        if (posicion >= 0 && posicion < tareas.size) {
            tareas.removeAt(posicion)
        }
    }
}
