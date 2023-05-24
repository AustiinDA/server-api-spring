package com.iessanalberto.dam2.datos.modelos

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Genero(
    val nombre: String = ""
)