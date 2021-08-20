package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Maquinaria

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Maquinaria

interface IMaquinariaBusiness {
    fun getMaquinarias():List<Maquinaria>
    fun getMaquinariaById(maquinariaId: Long): Maquinaria
    fun saveMaquinaria(maquinaria: Maquinaria): Maquinaria
    fun saveMaquinarias(maquinarias: List<Maquinaria>):List<Maquinaria>
    fun removeMaquinaria(maquinariaId: Long)
    fun getMaquinariaByFabricaId(fabricaId: Long): Maquinaria
    fun updateMaquinaria(maquinaria: Maquinaria): Maquinaria
}