package com.iessanalberto.dam2.datos

import com.iessanalberto.dam2.datos.dtos.RespuestaPaginadaPeliculas
import com.iessanalberto.dam2.datos.modelos.Creditos
import com.iessanalberto.dam2.datos.modelos.Pelicula
import com.iessanalberto.dam2.datos.peticiones.PeticionPelicula
import com.iessanalberto.dam2.datos.repositorios.RepositorioCreditos
import com.iessanalberto.dam2.datos.repositorios.RepositorioPeliculas
import com.iessanalberto.dam2.datos.servicios.ServicioCreditos
import org.springframework.beans.factory.annotation.Autowired
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
    private val repositorioCreditos: RepositorioCreditos,
    @Autowired private val servicioCreditos: ServicioCreditos
) {

    @GetMapping("/descubrir")
    fun devolverPeliculas(
        @RequestParam("pagina", defaultValue = "0") pagina: Int,
        @RequestParam("paginaSize", defaultValue = "5") paginaSize: Int,
        @RequestParam("titulo", required = false) titulo: String?
    ): RespuestaPaginadaPeliculas {

        if (titulo != null) {
            return buscarPeliculasPorTitulo(titulo, pagina, paginaSize)
        }

        val paginaAjustada = if (pagina > 0) pagina - 1 else 0 // Ajusta la página a 0 si es menor o igual a 0
        val pageable = PageRequest.of(paginaAjustada, paginaSize, Sort.by("titulo"))
        val resultadoPaginado = repositorioPeliculas.findAllByOrderByTitulo(pageable)

        return RespuestaPaginadaPeliculas(
            resultadoPaginado.number + 1, // Ajusta el número de página devuelto agregando 1
            resultadoPaginado.content,
            resultadoPaginado.totalPages,
            resultadoPaginado.totalElements
        )
    }
    //Método de búsqueda de las peliculas
    private fun buscarPeliculasPorTitulo(titulo: String, pagina: Int, paginaSize: Int): RespuestaPaginadaPeliculas {
        val paginaAjustada = if (pagina > 0) pagina - 1 else 0 // Ajusta la página a 0 si es menor o igual a 0
        val pageable = PageRequest.of(paginaAjustada, paginaSize, Sort.by("titulo"))
        val pageResult = repositorioPeliculas.findByTituloContainingIgnoreCaseOrderByTitulo(titulo, pageable)

        return RespuestaPaginadaPeliculas(
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
    }

    @PostMapping("/{idPelicula}/creditos")
    fun crearCreditosPelicula(
        @PathVariable idPelicula: String,
        @RequestBody peticion: Creditos
    ): ResponseEntity<Creditos> {
        // Asociar el ID de la película a los créditos
        peticion.idPelicula = idPelicula
        // Guardar los créditos en el repositorio de créditos
        servicioCreditos.guardarCreditos(peticion)
        // Devolver la respuesta exitosa
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{idPelicula}/creditos")
    fun obtenerCreditosPelicula(@PathVariable idPelicula: String): ResponseEntity<Creditos> {
        // Lógica para obtener los créditos de la película con el ID proporcionado
        val creditos = repositorioCreditos.getCreditosByIdPelicula(idPelicula)
        return ResponseEntity.ok(creditos)
    }
}