package com.iessanalberto.dam2.datos.repositorios

import com.iessanalberto.dam2.datos.modelos.Actor
import org.springframework.data.mongodb.repository.MongoRepository

interface RepositorioActores: MongoRepository<Actor, String>{

    fun findActorById(id: String): Actor
}