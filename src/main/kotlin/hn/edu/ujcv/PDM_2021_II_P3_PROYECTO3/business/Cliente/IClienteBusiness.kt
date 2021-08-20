package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Cliente

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Cliente


interface IClienteBusiness {
    fun getClientes():List<Cliente>
    fun getClienteById(idCliente: Long): Cliente
    fun saveCliente(cliente: Cliente): Cliente
    fun saveClientes(cliente: List<Cliente>):List<Cliente>
    fun removeCliente(idCliente: Long)
    fun getClienteByNombre(nombreCliente: String): Cliente
    fun updateCliente(cliente: Cliente): Cliente
}