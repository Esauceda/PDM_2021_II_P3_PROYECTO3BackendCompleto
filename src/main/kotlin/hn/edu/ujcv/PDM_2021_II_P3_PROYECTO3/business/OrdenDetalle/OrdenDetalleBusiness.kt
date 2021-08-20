package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.OrdenDetalle

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.OrdenDetalle.IOrdenDetalleBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.OrdenDetalleRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.OrdenDetalle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class OrdenDetalleBusiness: IOrdenDetalleBusiness {

    @Autowired
    val ordenDetalleRepository: OrdenDetalleRepository? = null

    //GETS
    @Throws(BusinessException::class)
    override fun getOrdenesDetalle(): List<OrdenDetalle> {
        try {
            return ordenDetalleRepository!!.findAll()
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    //GET
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getOrdenDetalleById(ordenDetalleId: Long): OrdenDetalle {
        val opt: Optional<OrdenDetalle>
        try {
            opt = ordenDetalleRepository!!.findById(ordenDetalleId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el orden detalle $ordenDetalleId")
        }
        return opt.get()
    }

    //SAVE
    @Throws(BusinessException::class)
    override fun saveOrdenDetalle(ordenDetalle: OrdenDetalle): OrdenDetalle {
        try {
            if (ordenDetalle.ordenId < 0)
                throw BusinessException("Ingrese id de orden detalle valido")
            if (ordenDetalle.almacenId < 0)
                throw BusinessException("Ingrese id de almacen valido")
            if (ordenDetalle.almacenId.toString().length == 0)
                throw BusinessException("El Almacen Id no puede estar vacio")
            if (ordenDetalle.productoId < 0)
                throw BusinessException("Ingrese id de producto valido")
            if (ordenDetalle.productoId.toString().length == 0)
                throw BusinessException("El Producto Id no puede estar vacio")
            if (ordenDetalle.cantidad.toString().length == 0)
                throw BusinessException("La cantidad no puede estar vacio")
            if (ordenDetalle.cantidad <= 0)
                throw BusinessException("La cantidad no puede ser menor o igual a 0")
            if (ordenDetalle.precio < 0)
                throw BusinessException("El precio no puede ser menor o igual a 0 ")
            if (ordenDetalle.precio.toString().length == 0)
                throw BusinessException("El precio no puede estar vacio")
            return ordenDetalleRepository!!.save(ordenDetalle)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    //SAVES
    @Throws(BusinessException::class)
    override fun saveOrdenesDetalle(ordenesDetalle: List<OrdenDetalle>): List<OrdenDetalle> {
        try {
            return ordenDetalleRepository!!.saveAll(ordenesDetalle)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    //REMOVE
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeOrdenDetalle(ordenDetalleId: Long) {
        val opt:Optional<OrdenDetalle>
        try {
            if (ordenDetalleId <= 0)
                throw BusinessException("El Id de orden detalle no puede ser menor o igual a 0")

            opt = ordenDetalleRepository!!.findById(ordenDetalleId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el orden detalle $ordenDetalleId")
        }
        else{
            try {
                ordenDetalleRepository!!.deleteById(ordenDetalleId)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    //GETBYPRODUCTOID
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getOrdenDetalleByProductoId(productoIdOrden: Long): OrdenDetalle {
        val opt:Optional<OrdenDetalle>
        try {
            opt = ordenDetalleRepository!!.findByProductoId(productoIdOrden)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el orden detalle con productoId $productoIdOrden")
        }
        return opt.get()
    }

    //UPDATE
    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateOrdenDetalle(ordenDetalle: OrdenDetalle): OrdenDetalle {
        val opt:Optional<OrdenDetalle>
        try {
            if (ordenDetalle.almacenId < 0)
                throw BusinessException("Ingrese id de almacen valido")
            if (ordenDetalle.almacenId.toString().length == 0)
                throw BusinessException("El Almacen Id no puede estar vacio")
            if (ordenDetalle.productoId < 0)
                throw BusinessException("Ingrese id de producto valido")
            if (ordenDetalle.productoId.toString().length == 0)
                throw BusinessException("El Producto Id no puede estar vacio")
            if (ordenDetalle.cantidad.toString().length == 0)
                throw BusinessException("La cantidad no puede estar vacio")
            if (ordenDetalle.cantidad <= 0)
                throw BusinessException("La cantidad no puede ser menor o igual a 0")
            if (ordenDetalle.precio < 0)
                throw BusinessException("El precio no puede ser menor o igual a 0 ")
            if (ordenDetalle.precio.toString().length == 0)
                throw BusinessException("El precio no puede estar vacio")

            opt = ordenDetalleRepository!!.findById(ordenDetalle.ordenId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el orden detalle ${ordenDetalle.ordenId}")
        }
        else{
            try {
                return ordenDetalleRepository!!.save(ordenDetalle)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
}