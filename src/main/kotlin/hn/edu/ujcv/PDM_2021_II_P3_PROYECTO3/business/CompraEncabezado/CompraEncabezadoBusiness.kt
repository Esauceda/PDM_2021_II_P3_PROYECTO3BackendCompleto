package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.CompraEncabezado

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.CompraEncabezadoRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.CompraEncabezado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class CompraEncabezadoBusiness: ICompraEncabezadoBusiness {

    @Autowired
    val compraEncabezadoRepository: CompraEncabezadoRepository?=null

    @Throws(BusinessException::class)
    override fun getComprasEncabezado(): List<CompraEncabezado> {
        try {
            return compraEncabezadoRepository!!.findAll();
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getCompraEncabezadoById(idCompraEncabezado: Long): CompraEncabezado {
        val opt: Optional<CompraEncabezado>
        try{
            opt = compraEncabezadoRepository!!.findById(idCompraEncabezado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la compra encabezado $idCompraEncabezado")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCompraEncabezado(compraEncabezado: CompraEncabezado): CompraEncabezado {
        try {
            var sdf = SimpleDateFormat("yyyy-MM-dd")
            var fecha:Date = sdf.parse(compraEncabezado.fechaCompra)
            var fecha2:Date = sdf.parse(compraEncabezado.fechaRecepcion)
            if (fecha2!!.before(fecha)) {
                throw BusinessException("La fecha de recepcion no puede ser menor a la fecha de compra")
            }else if (fecha!!.after(fecha2)) {
                throw BusinessException("La fecha de compra no puede ser mayor a la fecha de recepcion")
            }
            if (compraEncabezado.compraId<0){
                throw BusinessException("El id de la compra encabezado no puede estar vacio")
            }
            if (compraEncabezado.proveedorId<=0){
                throw BusinessException("El id del proveedor no puede estar vacio")
            }
            if (compraEncabezado.proveedorId.toString().length == 0){
                throw BusinessException("El id del proveedor no puede estar vacio")
            }
            if (compraEncabezado.empleadoId<=0){
                throw BusinessException("El id del empleado no puede estar vacio")
            }
            if (compraEncabezado.empleadoId.toString().length == 0){
                throw BusinessException("El id del empleado no puede estar vacio")
            }
            if (compraEncabezado.fechaCompra!!.compareTo(compraEncabezado.fechaRecepcion)>0) {
                throw BusinessException("La fecha de compra no puede ser mayor a la fecha de recepcion")
            }
            if (compraEncabezado.fechaCompra.toString().length == 0) {
                throw BusinessException("La fecha de compra no puede estar vacia")
            }
            if (compraEncabezado.fechaRecepcion!!.compareTo(compraEncabezado.fechaCompra)<0) {
                throw BusinessException("La fecha de recepcion no puede ser menor a la fecha de compra")
            }
            if (compraEncabezado.fechaRecepcion.toString().length == 0) {
                throw BusinessException("La fecha de recepcion no puede estar vacia")
            }
            if (compraEncabezado.total<=0){
                throw BusinessException("El total de la compra encabezado no puede estar vacio")
            }
            if (compraEncabezado.total.toString().length == 0){
                throw BusinessException("El total de la compra encabezado no puede estar vacio")
            }
            if (compraEncabezado.estado.length == 0){
                throw BusinessException("El estado de la compra encabezado no puede estar vacio")
            }
            return compraEncabezadoRepository!!.save(compraEncabezado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveComprasEncabezado(comprasEncabezado: List<CompraEncabezado>): List<CompraEncabezado> {
        try {
            return compraEncabezadoRepository!!.saveAll(comprasEncabezado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeCompraEncabezado(compraencabezadoId: Long) {
        val opt: Optional<CompraEncabezado>
        try{
            opt = compraEncabezadoRepository!!.findById(compraencabezadoId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la compra encabezado $compraencabezadoId")
        }
        else{
            try{
                compraEncabezadoRepository!!.deleteById(compraencabezadoId)
            }catch (e:Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateCompraEncabezado(compraEncabezado: CompraEncabezado): CompraEncabezado{
        val opt: Optional<CompraEncabezado>
        try{
            var sdf = SimpleDateFormat("yyyy-MM-dd")
            var fecha:Date = sdf.parse(compraEncabezado.fechaCompra)
            var fecha2:Date = sdf.parse(compraEncabezado.fechaRecepcion)
            if (fecha!!.before(fecha2)) {
                throw BusinessException("La fecha de recepcion no puede ser menor a la fecha de compra")
            }else if (fecha2!!.after(fecha)) {
                throw BusinessException("La fecha de compra no puede ser mayor a la fecha de recepcion")
            }
            if (compraEncabezado.proveedorId<=0){
                throw BusinessException("El id del proveedor no puede estar vacio")
            }
            if (compraEncabezado.proveedorId.toString().length == 0){
                throw BusinessException("El id del proveedor no puede estar vacio")
            }
            if (compraEncabezado.empleadoId<=0){
                throw BusinessException("El id del empleado no puede estar vacio")
            }
            if (compraEncabezado.empleadoId.toString().length == 0){
                throw BusinessException("El id del empleado no puede estar vacio")
            }
            if (compraEncabezado.fechaCompra.toString().length == 0) {
                throw BusinessException("La fecha de compra no puede estar vacia")
            }
            if (compraEncabezado.fechaRecepcion.toString().length == 0) {
                throw BusinessException("La fecha de recepcion no puede estar vacia")
            }
            if (compraEncabezado.total<=0){
                throw BusinessException("El total de la compra encabezado no puede estar vacio")
            }
            if (compraEncabezado.total.toString().length == 0){
                throw BusinessException("El total de la compra encabezado no puede estar vacio")
            }
            if (compraEncabezado.estado.length == 0){
                throw BusinessException("El estado de la compra encabezado no puede estar vacio")
            }
            opt = compraEncabezadoRepository!!.findById(compraEncabezado.compraId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la copmra encabezado ${compraEncabezado.compraId}")
        }
        else{
            try {
                return compraEncabezadoRepository!!.save(compraEncabezado)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}