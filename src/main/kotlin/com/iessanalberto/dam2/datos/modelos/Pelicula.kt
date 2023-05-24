package com.iessanalberto.dam2.datos.modelos

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Pelicula(
    var fondo_url: String? = "",
    var generos: List<Genero> = listOf(),
    @Id
    val id: String = ObjectId.get().toString(),
    var resumen: String = "",
    var poster_url: String? = "",
    var fecha_lanzamiento: String = "",
    var duracion: Int = 0,
    var titulo: String = ""
)