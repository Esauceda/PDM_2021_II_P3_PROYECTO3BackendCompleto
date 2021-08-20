package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.CompraEncabezado

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.CompraEncabezado

interface ICompraEncabezadoBusiness {
    fun getComprasEncabezado():List<CompraEncabezado>
    fun getCompraEncabezadoById(idCompraEncabezado: Long): CompraEncabezado
    fun saveCompraEncabezado(compraEncabezado: CompraEncabezado): CompraEncabezado
    fun saveComprasEncabezado(comprasEncabezado: List<CompraEncabezado>): List<CompraEncabezado>
    fun removeCompraEncabezado(idCompraEncabezado: Long)
    fun updateCompraEncabezado(compraEncabezado: CompraEncabezado): CompraEncabezado
}