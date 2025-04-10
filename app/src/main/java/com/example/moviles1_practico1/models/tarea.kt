package com.example.practico1.models

import java.io.Serializable

enum class Estado {
    CUMPLIDO,
    NO_CUMPLIDO
}

class Tarea (
    var titulo:String,
    var texto:String,
    var estado:Estado,
    var color:Int,
):Serializable


// tengo el primer activity que es el list de tareas
// el boton de agregar tarea si me lleva al segundo activy
//el boton de eliminar si funciona
// me lleva al crear tarea, me la crea , y me la muestra
// ademas puedo tacharla y eliminarla
//me lleva al editar tarea, me la edita , y me la muestra
//me deja ponerles colores y lo pinta
//tambien me deja cambiarle el color
//en teoria ya esta


//Falta
//Arreglar bug cuando doy clic
//probarlo  mas


