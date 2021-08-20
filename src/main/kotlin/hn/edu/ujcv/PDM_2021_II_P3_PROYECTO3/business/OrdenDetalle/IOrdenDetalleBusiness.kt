package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.OrdenDetalle

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.OrdenDetalle

interface IOrdenDetalleBusiness {
    fun getOrdenesDetalle():List<OrdenDetalle>
    fun getOrdenDetalleById(ordenDetalleId: Long): OrdenDetalle
    fun saveOrdenDetalle(ordenDetalle: OrdenDetalle): OrdenDetalle
    fun saveOrdenesDetalle(ordenesDetalle: List<OrdenDetalle>):List<OrdenDetalle>
    fun removeOrdenDetalle(ordenDetalleId: Long)
    fun getOrdenDetalleByProductoId(productoIdOrden: Long): OrdenDetalle
    fun updateOrdenDetalle(ordenDetalle: OrdenDetalle): OrdenDetalle
}