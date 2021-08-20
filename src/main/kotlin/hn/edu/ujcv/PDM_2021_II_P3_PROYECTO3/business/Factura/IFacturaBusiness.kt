package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Factura

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Factura

interface IFacturaBusiness {
    fun getFacturas():List<Factura>
    fun getFacturaById(facturaId: Long): Factura
    fun saveFactura(factura: Factura): Factura
    fun saveFacturas(facturas: List<Factura>):List<Factura>
    fun removeFactura(facturaId: Long)
    fun getFacturaByOrdenId(ordenId: Long): Factura
    fun updateFactura(factura: Factura): Factura
}