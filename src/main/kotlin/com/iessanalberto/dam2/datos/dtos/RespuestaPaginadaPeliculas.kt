package com.iessanalberto.dam2.datos.dtos

import com.iessanalberto.dam2.datos.modelos.Pelicula

class RespuestaPaginadaPeliculas(
    val numero_pagina: Int,
    val resultados: List<Pelicula>,
    val paginas_totales: Int,
    val resultados_totales: Long
    )