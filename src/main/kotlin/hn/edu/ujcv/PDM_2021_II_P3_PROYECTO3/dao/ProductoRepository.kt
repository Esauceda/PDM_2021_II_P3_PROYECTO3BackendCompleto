package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Producto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductoRepository: JpaRepository<Producto, Long> {
    fun findByNombreProducto(nombreProducto: String): Optional<Producto>
}