package com.iessanalberto.dam2.datos.servicios

import com.iessanalberto.dam2.datos.modelos.Creditos
import com.iessanalberto.dam2.datos.repositorios.RepositorioActores
import com.iessanalberto.dam2.datos.repositorios.RepositorioCreditos
import com.iessanalberto.dam2.datos.repositorios.RepositorioProduccion
import org.springframework.stereotype.Service

@Service
class ServicioCreditos(
    private val repositorioActores: RepositorioActores,
    private val repositorioProduccion: RepositorioProduccion,
    private val repositorioCreditos: RepositorioCreditos
) {
    fun guardarCreditos(creditos: Creditos): Creditos {
        // Guardar los actores en el repositorio de actores
        creditos.elenco.forEach { actor ->
            repositorioActores.save(actor)
        }

        // Guardar el equipo de producción en el repositorio de producción
        creditos.equipoProduccion.forEach { produccion ->
            repositorioProduccion.save(produccion)
        }

        // Guardar los créditos en el repositorio de créditos

        return repositorioCreditos.save(creditos)
    }
}