package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.OrdenDetalle

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.OrdenDetalle
import java.util.*

interface IOrdenDetalleBusiness {
    fun getOrdenesDetalle():List<OrdenDetalle>
    fun getOrdenDetalleById(ordenDetalleId: Long): OrdenDetalle
    fun saveOrdenDetalle(ordenDetalle: OrdenDetalle): OrdenDetalle
    fun saveOrdenesDetalle(ordenesDetalle: List<OrdenDetalle>):List<OrdenDetalle>
    fun removeOrdenDetalle(ordenDetalleId: Long)
    fun getOrdenDetalleByOrdenId(ordenId: Long): List<OrdenDetalle>
    fun updateOrdenDetalle(ordenDetalle: OrdenDetalle): OrdenDetalle
}