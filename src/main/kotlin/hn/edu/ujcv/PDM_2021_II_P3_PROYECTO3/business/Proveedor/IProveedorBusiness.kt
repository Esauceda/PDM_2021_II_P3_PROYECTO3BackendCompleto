package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Proveedor

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Proveedor


interface IProveedorBusiness {
    fun getProveedores():List<Proveedor>
    fun getProveedorById(idProveedor: Long): Proveedor
    fun saveProveedor(proveedor: Proveedor): Proveedor
    fun saveProveedores(proveedor: List<Proveedor>):List<Proveedor>
    fun removeProveedor(idProveedor: Long)
    fun getProveedorByNombreCompania(nombreCompania: String): Proveedor
    fun updateProveedor(proveedor: Proveedor): Proveedor
}