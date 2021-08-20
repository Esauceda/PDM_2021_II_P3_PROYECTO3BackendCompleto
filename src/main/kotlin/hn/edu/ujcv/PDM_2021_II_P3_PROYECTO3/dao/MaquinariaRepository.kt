package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Maquinaria
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MaquinariaRepository: JpaRepository<Maquinaria, Long>{
    fun findByFabricaId(fabricaId:Long):Optional<Maquinaria>
}