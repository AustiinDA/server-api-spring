package com.iessanalberto.dam2.datos.repositorios

import com.iessanalberto.dam2.datos.modelos.Pelicula
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface RepositorioPeliculas : MongoRepository<Pelicula, String> {
    fun findPeliculaById(id: String): Pelicula

    fun findAllByOrderByTitulo(pageable: Pageable): Page<Pelicula>
    override fun deleteAll()
}