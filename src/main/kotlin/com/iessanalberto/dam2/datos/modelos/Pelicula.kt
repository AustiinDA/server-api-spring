package com.iessanalberto.dam2.datos.modelos

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Pelicula(
    val fondoUrl: String? = "",
    val generos: List<Genero> = listOf(),
    @Id
    var id: ObjectId = ObjectId.get(),
    val resumen: String = "",
    val posterUrl: String? = "",
    val fechaLanzamiento: String = "",
    val duracion: Int = 0,
    val titulo: String = "",
)