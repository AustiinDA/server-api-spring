package com.iessanalberto.dam2

import com.iessanalberto.dam2.datos.repositorios.RepositorioPeliculas
import org.bson.types.ObjectId
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class TestControladorPeliculas @Autowired constructor(
    private val repositorioPeliculas: RepositorioPeliculas,
    private val plantillaRest:  TestRestTemplate
){
    private val valorDefaultIdPelicula = ObjectId.get()

    @LocalServerPort
    protected var puerto: Int = 0

    @BeforeEach
    fun setUp() {
        repositorioPeliculas.deleteAll()
    }

    private fun obtenerUrlRaiz(): String? = "http://localhost:$puerto/peliculas"

   // private fun guardarUnaPelicula() = repositorioPeliculas.save(Pelicula(valorDefaultIdPelicula))


}