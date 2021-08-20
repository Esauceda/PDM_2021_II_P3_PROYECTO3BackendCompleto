package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Producto

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Producto

interface IProductoBusiness {
    fun getProductos():List<Producto>
    fun getProductoById(idProducto: Long): Producto
    fun saveProducto(producto: Producto): Producto
    fun saveProductos(producto: List<Producto>):List<Producto>
    fun removeProducto(idProducto: Long)
    fun getProductoByNombre(nombreProducto: String): Producto
    fun updateProducto(producto: Producto): Producto
}