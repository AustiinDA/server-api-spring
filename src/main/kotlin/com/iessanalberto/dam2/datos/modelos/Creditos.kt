package com.iessanalberto.dam2.datos.modelos

data class Creditos(
    val idPelicula: String,
    val elenco: List<Actor>,
    val equipoProduccion: List<Produccion>
)


