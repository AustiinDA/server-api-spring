package com.iessanalberto.dam2.datos.repositorios

import com.iessanalberto.dam2.datos.modelos.Produccion
import org.springframework.data.mongodb.repository.MongoRepository

interface RepositorioProduccion: MongoRepository<Produccion, String>