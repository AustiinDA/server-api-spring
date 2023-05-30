package com.iessanalberto.dam2.datos

import com.iessanalberto.dam2.datos.modelos.Actor
import com.iessanalberto.dam2.datos.repositorios.RepositorioActores
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/actores")
class ControladorActores(
    private val repositorioActores: RepositorioActores
) {
    @GetMapping("/{id}")
    fun devolverUnActor(@PathVariable("id") id: String): ResponseEntity<Actor>{
        val actor = repositorioActores.findActorById(id)
        return ResponseEntity.ok(actor)
    }
}