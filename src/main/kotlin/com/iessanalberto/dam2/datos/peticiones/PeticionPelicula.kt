package com.iessanalberto.dam2.datos.peticiones

import com.iessanalberto.dam2.datos.modelos.Genero
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

class PeticionPelicula(



    var titulo: String,
    var poster_url: String,
    var fondo_url: String,
    var resumen: String,
    val generos: List<Genero> = listOf(),
    val fecha_lanzamiento: String,
    val duracion: Int

)