package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Proveedor
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProveedorRepository: JpaRepository<Proveedor, Long> {
    fun findByNombreCompania(nombreCompania: String): Optional<Proveedor>
}