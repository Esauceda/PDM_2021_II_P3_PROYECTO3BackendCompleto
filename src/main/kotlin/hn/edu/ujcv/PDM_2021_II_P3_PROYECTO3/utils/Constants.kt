package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3

class Constants {
    companion object{
        private const val URL_API_BASE      = "/api"
        private const val URL_API_VERSION   = "/v1"
        private const val URL_BASE          = URL_API_BASE + URL_API_VERSION
        const val URL_BASE_EMPLEADOS        = "$URL_BASE/empleado"
        const val URL_BASE_ALMACEN          = "$URL_BASE/almacen"
        const val URL_BASE_CLIENTES         = "$URL_BASE/cliente"
        const val URL_BASE_DELIVERYS        = "$URL_BASE/delivery"
        const val URL_BASE_PRODUCTOS        = "$URL_BASE/producto"
        const val URL_BASE_PROVEEDORES      = "$URL_BASE/proveedor"
        const val URL_BASE_COMPRADETALLE    = "$URL_BASE/compraDetalle"
        const val URL_BASE_COMPRAENCABEZADO = "$URL_BASE/compraEncabezado"
        const val URL_BASE_FABRICA          = "$URL_BASE/fabrica"
        const val URL_BASE_MATERIAPRIMA     = "$URL_BASE/materiaPrima"
        const val URL_BASE_ORDENENCABEZADO  = URL_BASE+"/OrdenEncabezado"
        const val URL_BASE_ORDENEDETALLE    = URL_BASE+"/OrdenDetalle"
        const val URL_BASE_FACTURA          = URL_BASE+"/Factura"
        const val URL_BASE_MAQUINARIA       = URL_BASE+"/Maquinaria"
    }
}