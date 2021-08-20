package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.CompraDetalle

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.CompraDetalle

interface ICompraDetalleBusiness {
    fun getComprasDetalle():List<CompraDetalle>
    fun getCompraDetalleById(idCompraDetalle: Long):CompraDetalle
    fun saveCompraDetalle(compraDetalle: CompraDetalle): CompraDetalle
    fun saveComprasDetalle(comprasDetalle:List<CompraDetalle>): List<CompraDetalle>
    fun removeCompraDetalle(idCompraDetalle: Long)
    fun updateCompraDetalle(compraDetalle: CompraDetalle): CompraDetalle
}