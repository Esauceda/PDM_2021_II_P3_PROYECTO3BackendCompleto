package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Proveedor

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.ProveedorRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Proveedor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProveedorBusiness: IProveedorBusiness {

    @Autowired
    val proveedorRepository: ProveedorRepository?=null

    @Throws(BusinessException::class)
    override fun getProveedores(): List<Proveedor> {
        try {
            return proveedorRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getProveedorById(idProveedor: Long): Proveedor {
        val opt: Optional<Proveedor>
        try{
            opt = proveedorRepository!!.findById(idProveedor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el proveedor $idProveedor")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveProveedor(proveedor: Proveedor): Proveedor {
        try {
            if (proveedor.proveedorId < 0) {
                throw BusinessException("El Id no puede ser menor o igual a 0")
            }else if (proveedor.nombreCompania.length == 0){
                throw BusinessException("El nombre de la compania no puede estar vacio")
            }else if (proveedor.nombreContacto.length == 0){
                throw BusinessException("El nombre de el contacto no puede estar vacio")
            }else if  (proveedor.numero.toString().length < 8) {
                throw BusinessException("Las unidades en almacen no pueden ser menor a 8 digitos")
            }else if (proveedor.numero.toString().length > 8) {
                throw BusinessException("Las unidades en almacen no pueden ser mayor a 8 digitos")
            }else if (proveedor.correo.length == 0){
                throw BusinessException("El correo no puede estar vacio")
            }else if (proveedor.pais.length == 0){
                throw BusinessException("El pais no puede estar vacio")
            }else if (proveedor.direccion.length == 0){
                throw BusinessException("La direcicon no puede estar vacia")
            }
            return proveedorRepository!!.save(proveedor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveProveedores(proveedor: List<Proveedor>): List<Proveedor> {
        try {
            return proveedorRepository!!.saveAll(proveedor)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeProveedor(idProveedor: Long) {
        val opt: Optional<Proveedor>
        try{
            if (idProveedor < 0){
                throw BusinessException("El id que ingreso no es valido")
            }
            opt = proveedorRepository!!.findById(idProveedor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el proveedor $idProveedor")
        }
        else{
            try{
                proveedorRepository!!.deleteById(idProveedor)
            }catch (e:Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getProveedorByNombreCompania(nombreCompania: String): Proveedor {
        val opt: Optional<Proveedor>
        try{
            if (nombreCompania.isEmpty()) {
                throw BusinessException("El nombre de la compania no puede estar vacio")
            }
            opt = proveedorRepository!!.findByNombreCompania(nombreCompania)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el proveedor $nombreCompania")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateProveedor(proveedor: Proveedor): Proveedor {
        val opt: Optional<Proveedor>
        try{
            if (proveedor.nombreCompania.length == 0){
                throw BusinessException("El nombre de la compania no puede estar vacio")
            }else if (proveedor.nombreContacto.length == 0){
                throw BusinessException("El nombre de el contacto no puede estar vacio")
            }else if  (proveedor.numero.toString().length < 8) {
                throw BusinessException("Las unidades en almacen no pueden ser menor a 8 digitos")
            }else if (proveedor.numero.toString().length > 8) {
                throw BusinessException("Las unidades en almacen no pueden ser mayor a 8 digitos")
            }else if (proveedor.correo.length == 0){
                throw BusinessException("El correo no puede estar vacio")
            }else if (proveedor.pais.length == 0){
                throw BusinessException("El pais no puede estar vacio")
            }else if (proveedor.direccion.length == 0){
                throw BusinessException("La direcicon no puede estar vacia")
            }
            opt = proveedorRepository!!.findById(proveedor.proveedorId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el proveedor ${proveedor.proveedorId}")
        }
        else{
            try {
                return proveedorRepository!!.save(proveedor)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}