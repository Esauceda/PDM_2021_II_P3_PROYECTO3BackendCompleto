package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Almacen

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.AlmacenRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Almacen
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AlmacenBusiness: IAlmacenBusiness {

    @Autowired
    val almacenRepository: AlmacenRepository?=null

    @Throws(BusinessException::class)
    override fun getAlmacenes(): List<Almacen> {
        try {
            return almacenRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getAlmacenById(idAlmacen: Long): Almacen {
        val opt: Optional<Almacen>
        try{
            opt = almacenRepository!!.findById(idAlmacen)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el almacen $idAlmacen")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveAlmacen(almacen: Almacen): Almacen {
        try {
            if (almacen.almacenId < 0) {
                throw BusinessException("El id que ingreso no es valido")
            }else if (almacen.telefono < 8) {
                throw BusinessException("El telefono no pueden ser menor a 8 digitos")
            }else if (almacen.telefono.toString().length > 8) {
                throw BusinessException("Las unidades en almacen no pueden ser mayor a 8 digitos")
            }else if (almacen.direccion.length == 0){
                throw BusinessException("La direccion no puede estar vacia")
            }else if (almacen.encargado.length == 0){
                throw BusinessException("El encargado no puede estar vacio")
            }
            return almacenRepository!!.save(almacen)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveAlmacenes(almacen: List<Almacen>): List<Almacen> {
        try {
            return almacenRepository!!.saveAll(almacen)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeAlmacen(idAlmacen: Long) {
        val opt: Optional<Almacen>
        try{
            if (idAlmacen < 0){
                throw BusinessException("El id que ingreso no es valido")
            }
            opt = almacenRepository!!.findById(idAlmacen)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el almacen $idAlmacen")
        }
        else{
            try{
                almacenRepository!!.deleteById(idAlmacen)
            }catch (e:Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getAlmacenByEncargado(encargado: String): Almacen {
        val opt: Optional<Almacen>
        try{
            if (encargado.isEmpty()){
                throw BusinessException("El nombre que ingreso no es valido")
            }
            opt = almacenRepository!!.findByEncargado(encargado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el encargado $encargado")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateAlmacen(almacen: Almacen): Almacen {
        val opt: Optional<Almacen>
        try{
        if (almacen.telefono < 8) {
            throw BusinessException("El telefono no pueden ser menor a 8 digitos")
        }else if (almacen.telefono.toString().length > 8) {
            throw BusinessException("Las unidades en almacen no pueden ser mayor a 8 digitos")
        }else if (almacen.direccion.length == 0){
            throw BusinessException("La direccion no puede estar vacia")
        }else if (almacen.encargado.length == 0){
            throw BusinessException("El encargado no puede estar vacio")
            }
            opt = almacenRepository!!.findById(almacen.almacenId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el almacen ${almacen.almacenId}")
        }
        else{
            try {
                return almacenRepository!!.save(almacen)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}