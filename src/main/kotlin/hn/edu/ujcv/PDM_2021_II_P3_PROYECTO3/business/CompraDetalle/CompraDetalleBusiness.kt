package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.CompraDetalle


import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.CompraDetalleRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.CompraDetalle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class CompraDetalleBusiness: ICompraDetalleBusiness {

    @Autowired
    val compraDetalleRepository: CompraDetalleRepository?=null

    @Throws(BusinessException::class)
    override fun getComprasDetalle(): List<CompraDetalle> {
        try {
            return compraDetalleRepository!!.findAll();
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getCompraDetalleById(compradetalleId: Long): CompraDetalle {
        val opt: Optional<CompraDetalle>
        try{
            opt = compraDetalleRepository!!.findById(compradetalleId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la compra detalle $compradetalleId")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCompraDetalle(compraDetalle: CompraDetalle): CompraDetalle {
        try {
            if (compraDetalle.compraDetalleId<0) {
                throw BusinessException("El id de la compra detalle no puede estar vacio")
            }
            if (compraDetalle.producto.length == 0) {
                throw BusinessException("El producto de la compra detalle no puede estar vacio")
            }
            if (compraDetalle.cantidad.toString().length == 0) {
                throw BusinessException("La cantidad de la compra detalle no puede estar vacio")
            }
            if (compraDetalle.cantidad.toString().length == 0) {
                throw BusinessException("La cantidad de la compra detalle no puede estar vacio")
            }
            if (compraDetalle.precio <= 0.0) {
                throw BusinessException("El precio de la compra detalle no puede estar vacio")
            }
            if (compraDetalle.precio.toString().length == 0) {
                throw BusinessException("El precio de la compra detalle no puede estar vacio")
            }
            return compraDetalleRepository!!.save(compraDetalle)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveComprasDetalle(comprasDetalle: List<CompraDetalle>): List<CompraDetalle> {
        try {
            return compraDetalleRepository!!.saveAll(comprasDetalle)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCompraDetalle(compradetalleId: Long) {
        val opt: Optional<CompraDetalle>
        try{
            opt = compraDetalleRepository!!.findById(compradetalleId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la compra detalle $compradetalleId")
        }
        else{
            try{
                compraDetalleRepository!!.deleteById(compradetalleId)
            }catch (e:Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class)
    override fun getComprasDetalleByCompraId(compraId: Long): List<CompraDetalle> {
        try {
            return compraDetalleRepository!!.findByCompraId(compraId);
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateCompraDetalle(compraDetalle: CompraDetalle): CompraDetalle {
        val opt: Optional<CompraDetalle>
        try{
            if (compraDetalle.producto.length == 0) {
                throw BusinessException("El producto de la compra detalle no puede estar vacio")
            }
            if (compraDetalle.cantidad.toString().length == 0) {
                throw BusinessException("La cantidad de la compra detalle no puede estar vacio")
            }
            if (compraDetalle.cantidad.toString().length == 0) {
                throw BusinessException("La cantidad de la compra detalle no puede estar vacio")
            }
            if (compraDetalle.precio <= 0.0) {
                throw BusinessException("El precio de la compra detalle no puede estar vacio")
            }
            if (compraDetalle.precio.toString().length == 0) {
                throw BusinessException("El precio de la compra detalle no puede estar vacio")
            }
            opt = compraDetalleRepository!!.findById(compraDetalle.compraDetalleId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la compra detalle ${compraDetalle.compraDetalleId}")
        }
        else{
            try {
                return compraDetalleRepository!!.save(compraDetalle)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}