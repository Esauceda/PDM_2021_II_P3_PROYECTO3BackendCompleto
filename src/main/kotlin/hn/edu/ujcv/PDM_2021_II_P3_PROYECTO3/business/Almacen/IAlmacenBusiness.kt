package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Almacen

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Almacen


interface IAlmacenBusiness {
    fun getAlmacenes():List<Almacen>
    fun getAlmacenById(idAlmacen: Long): Almacen
    fun saveAlmacen(almacen: Almacen): Almacen
    fun saveAlmacenes(almacen: List<Almacen>):List<Almacen>
    fun removeAlmacen(idAlmacen: Long)
    fun getAlmacenByEncargado(encargado: String): Almacen
    fun updateAlmacen(almacen: Almacen): Almacen
}