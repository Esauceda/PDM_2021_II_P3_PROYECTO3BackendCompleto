package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.MateriaPrima

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.MateriaPrima

interface IMateriaPrimaBusiness {
    fun getMateriaPrimas():List<MateriaPrima>
    fun getMateriaById(idMateria: Long): MateriaPrima
    fun saveMateria(materia: MateriaPrima): MateriaPrima
    fun saveMateriaPrimas(materias: List<MateriaPrima>): List<MateriaPrima>
    fun removeMateria(idMateria: Long)
    fun getMateriaByNombre(nombreMateria: String): MateriaPrima
    fun updateMateria(materia: MateriaPrima): MateriaPrima
}