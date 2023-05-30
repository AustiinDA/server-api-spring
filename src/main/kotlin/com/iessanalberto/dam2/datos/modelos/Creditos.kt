package com.iessanalberto.dam2.datos.modelos

import org.springframework.data.annotation.Id

data class Creditos(
    @Id
    var idPelicula: String,
    val elenco: List<Actor>,
    val equipoProduccion: List<Produccion>
)

