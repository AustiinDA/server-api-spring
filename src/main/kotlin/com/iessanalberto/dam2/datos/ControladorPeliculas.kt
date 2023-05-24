package com.iessanalberto.dam2.datos

import com.iessanalberto.dam2.datos.dtos.RespuestaPaginada
import com.iessanalberto.dam2.datos.modelos.Creditos
import com.iessanalberto.dam2.datos.modelos.Pelicula
import com.iessanalberto.dam2.datos.peticiones.PeticionPelicula
import com.iessanalberto.dam2.datos.repositorios.RepositorioActores
import com.iessanalberto.dam2.datos.repositorios.RepositorioPeliculas
import com.iessanalberto.dam2.datos.repositorios.RepositorioProduccion
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
/**se utiliza para gestionar las peticiones web*/
@RequestMapping("/peliculas")
class ControladorPeliculas(
    private val repositorioPeliculas: RepositorioPeliculas,
    private val repositorioActores: RepositorioActores,
    private val repositorioProduccion: RepositorioProduccion
) {

    @GetMapping("/descubrir")
    fun devolverPeliculas(
        @RequestParam("page", defaultValue = "0") page: Int,
        @RequestParam("size", defaultValue = "20") size: Int
    ): RespuestaPaginada {

        val paginaAjustada = if (page > 0) page - 1 else 0 // Ajusta la página a 0 si es menor o igual a 0
        val pageable = PageRequest.of(paginaAjustada, size, Sort.by("titulo"))
        val pageResult = repositorioPeliculas.findAllByOrderByTitulo(pageable)

        return RespuestaPaginada(
            pageResult.number + 1, // Ajusta el número de página devuelto agregando 1
            pageResult.content,
            pageResult.totalPages,
            pageResult.totalElements
        )
    }

    @GetMapping("/{id}")
    fun devolverUnaPelicula(@PathVariable("id") id: String): ResponseEntity<Pelicula> {
        val pelicula = repositorioPeliculas.findPeliculaById(id)
        return ResponseEntity.ok(pelicula)
    }

    @PostMapping("/crearPelicula")
    fun crearPelicula(@RequestBody peticion: Pelicula): ResponseEntity<Pelicula> {
        val pelicula = repositorioPeliculas.save(peticion)
        return ResponseEntity(pelicula, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun actualizarPelicula(
        @RequestBody peticion: PeticionPelicula,
        @PathVariable("id") id: String
    ): ResponseEntity<Pelicula> {
        val pelicula = repositorioPeliculas.findPeliculaById(id)

        // Verifica los campos de la peticion y actualiza solo los que no sean nulos

        pelicula.titulo = peticion.titulo
        pelicula.poster_url = peticion.poster_url
        pelicula.fondo_url = peticion.fondo_url
        pelicula.resumen = peticion.resumen
        pelicula.generos = peticion.generos
        pelicula.duracion = peticion.duracion
        pelicula.fecha_lanzamiento = peticion.fecha_lanzamiento


        //Guarda la pelicula actualizada
        val peliculaActualizada = repositorioPeliculas.save(pelicula)

        return ResponseEntity.ok(peliculaActualizada)
    }
    @DeleteMapping("/{id}")
    fun borrarPelicula(@PathVariable("id") id: String): ResponseEntity<String> {
        repositorioPeliculas.deleteById(id)
        return ResponseEntity.ok("Película borrada exitosamente")
        //return ResponseEntity.noContent().build()
    }

//    @GetMapping("/{idPelicula}/creditos")
//    fun obtenerCreditosPelicula(@PathVariable idPelicula: String): Creditos {
//        // Lógica para obtener los créditos de la película con el ID proporcionado
//        val elenco = servicioCreditos.obtenerElenco(idPelicula)
//        val equipoProduccion = servicioCreditos.obtenerEquipoProduccion(idPelicula)
//
//        return Creditos(idPelicula, elenco, equipoProduccion)
//    }
}