package com.iessanalberto.dam2.datos

import com.iessanalberto.dam2.datos.modelos.Pelicula
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface RepositorioPeliculas : MongoRepository<Pelicula, String> {
    fun encontrarPeliPorId(id: ObjectId): Pelicula
    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}