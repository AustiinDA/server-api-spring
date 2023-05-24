package com.iessanalberto.dam2.datos.repositorios

import com.iessanalberto.dam2.datos.modelos.Actor
import com.iessanalberto.dam2.datos.modelos.Produccion
import org.springframework.data.mongodb.repository.MongoRepository

interface RepositorioActores: MongoRepository<Actor, String>{
    fun obtenerElencoPorIdPelicula(idPelicula: String): List<Actor>

}