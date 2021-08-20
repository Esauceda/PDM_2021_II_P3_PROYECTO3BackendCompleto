package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.OrdenEncabezado

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.OrdenEncabezadoRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.OrdenEncabezado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class OrdenEncabezadoBusiness: IOrdenEncabezadoBusiness {


    @Autowired
    val ordenEncabezadoRepository: OrdenEncabezadoRepository? = null

    //GETS
    @Throws(BusinessException::class)
    override fun getOrdendesEncabezado(): List<OrdenEncabezado> {
        try {
            return ordenEncabezadoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    //GET
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getOrdenEncabezadoById(ordenEncabezadoId: Long): OrdenEncabezado {
        val opt:Optional<OrdenEncabezado>

        try{
            opt = ordenEncabezadoRepository!!.findById(ordenEncabezadoId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if (!opt.isPresent){
            throw NotFoundException("No se encontró el Orden Encabezado $ordenEncabezadoId")
        }

        return opt.get()
    }
    //SAVE
    @Throws(BusinessException::class)
    override fun saveOrdenEncabezado(ordenEncabezado: OrdenEncabezado): OrdenEncabezado {
        try {

            if (ordenEncabezado.ordenId < 0)
                throw BusinessException("ID de orden no válido")
            if (ordenEncabezado.empleadoId <= 0)
                throw BusinessException("ID de empleado menor o igual a 0 no válido")
            if (ordenEncabezado.empleadoId.toString().length == 0)
                throw BusinessException("ID de empleado no puede estar vacio")
            if (ordenEncabezado.clienteId <= 0)
                throw BusinessException("ID de cliente menor o igual a 0 no válido")
            if (ordenEncabezado.clienteId.toString().length == 0)
                throw BusinessException("ID de cliente no puede estar vacio")
            if (ordenEncabezado.fechaOrden.length == 0)
                throw BusinessException("Ingrese una fecha válida")
            if (ordenEncabezado.fechaEnvio.length == 0)
                throw BusinessException("Ingrese una fecha de envio válida")
            if (ordenEncabezado.direccionEnvio.length == 0)
                throw BusinessException("Ingrese un direccion válida")
            if (ordenEncabezado.estado.length == 0)
                throw BusinessException("Ingrese una estado de orden válida")
            if (ordenEncabezado.total <= 0)
                throw BusinessException("Valor total menor o igual a 0 no válido")
            if (ordenEncabezado.total.toString().length == 0)
                throw BusinessException("Valor total no puede estar vacio")
            return ordenEncabezadoRepository!!.save(ordenEncabezado)

        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    //SAVES
    @Throws(BusinessException::class)
    override fun saveOrdenesEncabezado(ordenesEncabezado: List<OrdenEncabezado>): List<OrdenEncabezado> {
        try {
            return ordenEncabezadoRepository!!.saveAll(ordenesEncabezado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    //REMOVE
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeOrdenEncabezado(ordenEncabezadoId: Long) {
        val opt:Optional<OrdenEncabezado>

        try{
            if (ordenEncabezadoId <= 0)
                throw BusinessException("El Id de orden encabezado no puede ser menor o igual a 0")

            opt = ordenEncabezadoRepository!!.findById(ordenEncabezadoId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if (!opt.isPresent){
            throw NotFoundException("No se encontró el Orden Encabezado $ordenEncabezadoId")
        }else{
            try {
                ordenEncabezadoRepository!!.deleteById(ordenEncabezadoId)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }

    }
    //FINDBYESTADO
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getOrdenEncabezadoByEstado(estadoOrdenEncabezado: String): OrdenEncabezado {
        val opt:Optional<OrdenEncabezado>

        try{
            if (estadoOrdenEncabezado.isEmpty())
                throw BusinessException("El estado de la orden de orden no puede estar vacio")

            opt = ordenEncabezadoRepository!!.findByEstado(estadoOrdenEncabezado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if (!opt.isPresent){
            throw NotFoundException("No se encontró Orden Encabezado con estado $estadoOrdenEncabezado")
        }

        return opt.get()
    }
    //UPDATE
    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateOrdenEncabezado(ordenEncabezado: OrdenEncabezado): OrdenEncabezado {
        val opt:Optional<OrdenEncabezado>

        try{
            if (ordenEncabezado.empleadoId <= 0)
                throw BusinessException("ID de empleado menor o igual a 0 no válido")
            if (ordenEncabezado.empleadoId.toString().length == 0)
                throw BusinessException("ID de empleado no puede estar vacio")
            if (ordenEncabezado.clienteId <= 0)
                throw BusinessException("ID de cliente menor o igual a 0 no válido")
            if (ordenEncabezado.clienteId.toString().length == 0)
                throw BusinessException("ID de cliente no puede estar vacio")
            if (ordenEncabezado.fechaOrden.length == 0)
                throw BusinessException("Ingrese una fecha válida")
            if (ordenEncabezado.fechaEnvio.length == 0)
                throw BusinessException("Ingrese una fecha de envio válida")
            if (ordenEncabezado.direccionEnvio.length == 0)
                throw BusinessException("Ingrese un direccion válida")
            if (ordenEncabezado.estado.length == 0)
                throw BusinessException("Ingrese una estado de orden válida")
            if (ordenEncabezado.total <= 0)
                throw BusinessException("Valor total menor o igual a 0 no válido")
            if (ordenEncabezado.total.toString().length == 0)
                throw BusinessException("Valor total no puede estar vacio")

            opt = ordenEncabezadoRepository!!.findById(ordenEncabezado.ordenId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

        if (!opt.isPresent){
            throw NotFoundException("No se encontró el Orden Encabezado ${ordenEncabezado.ordenId}")
        }else{
            try {
                return ordenEncabezadoRepository!!.save(ordenEncabezado)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }

        return opt.get()

    }

}//-----