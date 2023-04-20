package com.iessanalberto.dam2.datos

import com.iessanalberto.dam2.datos.modelos.Pelicula
import com.iessanalberto.dam2.datos.peticiones.PeticionPelicula
import org.apache.coyote.Response
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/peliculas")
class ControladorPeliculas(
    private val repositorioPeliculas: RepositorioPeliculas
) {
    @GetMapping
    fun devolverPeliculas(): ResponseEntity<List<Pelicula>> {
        val peliculas = repositorioPeliculas.findAll()
        return ResponseEntity.ok(peliculas)
    }

    @GetMapping("/{id}")
    fun devolverUnaPelicula(@PathVariable("id") id: String): ResponseEntity<Pelicula> {
        val pelicula = repositorioPeliculas.encontrarPeliPorId(ObjectId(id))
        return ResponseEntity.ok(pelicula)
    }

    @PostMapping
    fun crearPelicula(@RequestBody peticion: PeticionPelicula): ResponseEntity<Pelicula> {
        val pelicula = repositorioPeliculas.save(
            Pelicula(
                titulo = peticion.titulo
            )
        )
        return ResponseEntity(pelicula, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun actualizarPelicula(
        @RequestBody peticion: PeticionPelicula,
        @PathVariable("id") id: String
    ): ResponseEntity<Pelicula> {
        val pelicula = repositorioPeliculas.encontrarPeliPorId(ObjectId(id))
        val actualizarPelicula = repositorioPeliculas.save(Pelicula(
            id = pelicula.id,
            //TODO completar
        ))
        return ResponseEntity.ok(actualizarPelicula)
    }

    //TODO Completar
    /**Funcion de borrado DELETE MAPPING */


}