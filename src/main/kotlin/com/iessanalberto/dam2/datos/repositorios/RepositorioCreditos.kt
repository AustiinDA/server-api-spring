package com.iessanalberto.dam2.datos.repositorios

import com.iessanalberto.dam2.datos.modelos.Creditos
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*

@Repository
interface RepositorioCreditos : MongoRepository<Creditos, String> {
    fun getCreditosByIdPelicula(idPelicula: String): Creditos

}