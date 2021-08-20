package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Factura

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.FacturaRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Factura
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class FacturaBusiness: IFacturaBusiness {

    @Autowired
    val facturaRepository: FacturaRepository? = null

    //GETS
    @Throws(BusinessException::class)
    override fun getFacturas(): List<Factura> {
        try {
            return facturaRepository!!.findAll()
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    //GET
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaById(facturaId: Long): Factura {
        val opt: Optional<Factura>
        try {
            opt = facturaRepository!!.findById(facturaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $facturaId")
        }
        return opt.get()
    }

    //SAVE
    @Throws(BusinessException::class)
    override fun saveFactura(factura: Factura): Factura {
        try {
            if (factura.facturaId < 0)
                throw BusinessException("Id de factura no valido")
            if (factura.ordenId <= 0)
                throw BusinessException("Id de orden no valido")
            if (factura.ordenId.toString().length == 0)
                throw BusinessException("Id de orden no puede estar vacio")
            if (factura.fechaFactura.length == 0)
                throw BusinessException("Ingrese una fecha valida")
            if (factura.total <= 0)
                throw BusinessException("El total no puede ser menor o igual a 0")
            if (factura.total.toString().length == 0)
                throw BusinessException("El total no puede estar vacio")
            return facturaRepository!!.save(factura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    //SAVES
    @Throws(BusinessException::class)
    override fun saveFacturas(facturas: List<Factura>): List<Factura> {
        try {
            return facturaRepository!!.saveAll(facturas)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    //REMOVE
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeFactura(facturaId: Long) {
        val opt:Optional<Factura>
        try {
            if (facturaId < 0)
                throw BusinessException("El Id de factura no puede ser menor o igual a 0")

            opt = facturaRepository!!.findById(facturaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la factura $facturaId")
        }
        else{
            try {
                facturaRepository!!.deleteById(facturaId)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    //GETBYORDEID
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaByOrdenId(ordenId: Long): Factura {
        val opt:Optional<Factura>
        try {
            opt = facturaRepository!!.findByOrdenId(ordenId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la la factura con odenId $ordenId")
        }
        return opt.get()
    }

    //UPDATE
    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateFactura(factura: Factura): Factura {
        val opt:Optional<Factura>
        try {
            if (factura.ordenId <= 0)
                throw BusinessException("Id de orden no valido")
            if (factura.ordenId.toString().length == 0)
                throw BusinessException("Id de orden no puede estar vacio")
            if (factura.fechaFactura.length == 0)
                throw BusinessException("Ingrese una fecha valida")
            if (factura.total <= 0)
                throw BusinessException("El total no puede ser menor o igual a 0")
            if (factura.total.toString().length == 0)
                throw BusinessException("El total no puede estar vacio")
            opt = facturaRepository!!.findById(factura.facturaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la factura ${factura.facturaId}")
        }
        else{
            try {
                return facturaRepository!!.save(factura)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }


}