package com.iessanalberto.dam2.datos.modelos

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id


data class Actor(
    @Id
    val id: String = ObjectId.get().toString(),
    val genero: Int,
    val nombre: String,
    val foto_url: String?
)