package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Empleado
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmpleadoRepository: JpaRepository<Empleado, Long> {
    fun findByNombre(nombreEmpleado:String): Optional<Empleado>
}