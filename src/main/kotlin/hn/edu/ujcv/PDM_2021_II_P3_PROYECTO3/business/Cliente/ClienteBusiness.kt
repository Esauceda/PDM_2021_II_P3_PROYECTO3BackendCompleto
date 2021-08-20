package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Cliente

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.ClienteRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClienteBusiness: IClienteBusiness {

    @Autowired
    val clienteRepository: ClienteRepository?=null

    @Throws(BusinessException::class)
    override fun getClientes(): List<Cliente> {
        try {
            return clienteRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getClienteById(idCliente: Long): Cliente {
        val opt: Optional<Cliente>
        try{
            opt = clienteRepository!!.findById(idCliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el cliente $idCliente")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCliente(cliente: Cliente): Cliente {
        try {
            if (cliente.clienteId < 0) {
                throw BusinessException("El id que ingreso no es valido")
            }else if (cliente.nombreCompania.length == 0){
                throw BusinessException("El nombre no puede estar vacio")
            }else if (cliente.nombre.length == 0){
                throw BusinessException("El nombre no puede estar vacio")
            }else if (cliente.telefono.toString().length < 8) {
                throw BusinessException("El telefono no pueden ser menor a 8 digitos")
            }else if (cliente.telefono.toString().length > 8) {
                throw BusinessException("El telefono no puede ser mayor a 8 digitos")
            }else if (cliente.correo.length == 0){
                throw BusinessException("El correo no puede estar vacio")
            }else if (cliente.pais.length == 0){
                throw BusinessException("El pais no puede estar vacio")
            }else if (cliente.direccion.length == 0){
                throw BusinessException("La direccion no puede estar vacio")
            }else if (cliente.categoria.length == 0) {
                throw BusinessException("La categoria no puede estar vacio")
            }
            return clienteRepository!!.save(cliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveClientes(cliente: List<Cliente>): List<Cliente> {
        try {
            return clienteRepository!!.saveAll(cliente)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCliente(idCliente: Long) {
        val opt: Optional<Cliente>
        try{
            if (idCliente < 0){
                throw BusinessException("El id que ingreso no es valido")
            }
            opt = clienteRepository!!.findById(idCliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el cliente $idCliente")
        }
        else{
            try{
                clienteRepository!!.deleteById(idCliente)
            }catch (e:Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getClienteByNombre(nombreCliente: String): Cliente {
        val opt: Optional<Cliente>
        try{
            if (nombreCliente.isEmpty()){
                throw BusinessException("El cliente no puede estar vacio")
            }
            opt = clienteRepository!!.findByNombre(nombreCliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el cliente $nombreCliente")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateCliente(cliente: Cliente): Cliente {
        val opt: Optional<Cliente>
        try{
            if (cliente.nombreCompania.length == 0){
                throw BusinessException("El nombre no puede estar vacio")
            }else if (cliente.nombre.length == 0){
                throw BusinessException("El nombre no puede estar vacio")
            }else if (cliente.telefono.toString().length < 8) {
                throw BusinessException("El telefono no pueden ser menor a 8 digitos")
            }else if (cliente.telefono.toString().length > 8) {
                throw BusinessException("El telefono no puede ser mayor a 8 digitos")
            }else if (cliente.correo.length == 0){
                throw BusinessException("El correo no puede estar vacio")
            }else if (cliente.pais.length == 0){
                throw BusinessException("El pais no puede estar vacio")
            }else if (cliente.direccion.length == 0){
                throw BusinessException("La direccion no puede estar vacio")
            }else if (cliente.categoria.length == 0) {
                throw BusinessException("La categoria no puede estar vacio")
            }
            opt = clienteRepository!!.findById(cliente.clienteId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el cliente ${cliente.clienteId}")
        }
        else{
            try {
                return clienteRepository!!.save(cliente)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}