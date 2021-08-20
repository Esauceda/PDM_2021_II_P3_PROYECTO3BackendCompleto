package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.OrdenEncabezado

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.OrdenEncabezado

interface IOrdenEncabezadoBusiness {
    fun getOrdendesEncabezado():List<OrdenEncabezado>
    fun getOrdenEncabezadoById(ordenEncabezadoId: Long): OrdenEncabezado
    fun saveOrdenEncabezado(ordenEncabezado: OrdenEncabezado): OrdenEncabezado
    fun saveOrdenesEncabezado(ordenesEncabezado: List<OrdenEncabezado>):List<OrdenEncabezado>
    fun removeOrdenEncabezado(ordenEncabezadoId: Long)
    fun getOrdenEncabezadoByEstado(estadoOrdenEncabezado: String): OrdenEncabezado
    fun updateOrdenEncabezado(ordenEncabezado: OrdenEncabezado): OrdenEncabezado
}