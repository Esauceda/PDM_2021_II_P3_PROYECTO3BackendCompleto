package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Empleado

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.EmpleadoRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Empleado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service

class EmpleadoBusiness: IEmpleadoBusiness {

    @Autowired
    val empleadoRepository: EmpleadoRepository?=null

    @Throws(BusinessException::class)
    override fun getEmpleados(): List<Empleado> {
        try {
            return empleadoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getEmpleadoById(idEmpleado: Long): Empleado {
        val opt: Optional<Empleado>
        try{
            opt = empleadoRepository!!.findById(idEmpleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el empleado $idEmpleado")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveEmpleado(empleado: Empleado): Empleado {
        try {
            var sdf = SimpleDateFormat("yyyy-MM-dd")
            var fecha:Date = sdf.parse(empleado.fechaContratacion)
            var fecha2:Date = sdf.parse(empleado.fechaNacimiento)
            if (fecha!!.before(fecha2)) {
                throw BusinessException("La fecha de contratacion no puede ser menor a la fecha de nacimiento")
            }else if (fecha2!!.after(fecha)) {
                throw BusinessException("La fecha de nacimeinto no puede ser mayor a la fecha de contratacion")
            }
            if (empleado.empleadoId < 0){
                throw BusinessException("El id que ingreso no es valido")
            }else if (empleado.nombre.length == 0){
                throw BusinessException("El nombre no puede estar vacio")
            }else if (empleado.direccion.length == 0){
                throw BusinessException("La direccion no puede estar vacia")
            }else if (empleado.telefono.toString().length < 8) {
                throw BusinessException("El telefono no pueden ser menor a 8 digitos")
            }else if (empleado.telefono.toString().length > 8) {
                throw BusinessException("El telefono no puede ser mayor a 8 digitos")
            }else if (empleado.salario < 0.0){
                throw BusinessException("El salario no puede ser menor a 0.0")
            }else if (empleado.salario.toString().length == 0){
                throw BusinessException("El salario no puede estar vacio")
            }else if (empleado.puesto.length == 0){
                throw BusinessException("El puesto no puede estar vacio")
            }else if (empleado.fechaContratacion.length == 0){
                throw BusinessException("La fecha de contratacion no puede estar vacio")
            }else if (empleado.fechaNacimiento.length == 0){
                throw BusinessException("La fecha de nacimiento no puede estar vacio")
            }else if (empleado.contrasena.length == 0){
                throw BusinessException("La contrasena no puede estar vacia")
            }
            return empleadoRepository!!.save(empleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveEmpleados(empleado: List<Empleado>): List<Empleado> {
        try {
            return empleadoRepository!!.saveAll(empleado)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeEmpleado(idEmpleado: Long) {
        val opt: Optional<Empleado>
        try{
            if (idEmpleado < 0){
                throw BusinessException("El id que ingreso no es valido")
            }
            opt = empleadoRepository!!.findById(idEmpleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el empleado $idEmpleado")
        }
        else{
            try{
                empleadoRepository!!.deleteById(idEmpleado)
            }catch (e:Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getEmpleadoByNombre(nombreEmpleado: String): Empleado {
        val opt: Optional<Empleado>
        try{
            if (nombreEmpleado.isEmpty()){
                throw BusinessException("El nombre del empleado no puede estar vacio")
            }
            opt = empleadoRepository!!.findByNombre(nombreEmpleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el empleado $nombreEmpleado")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateEmpleado(empleado: Empleado): Empleado {
        val opt: Optional<Empleado>
        var sdf = SimpleDateFormat("yyyy-MM-dd")
        try {
            var fecha:Date = sdf.parse(empleado.fechaContratacion)
            var fecha2:Date = sdf.parse(empleado.fechaNacimiento)
            if (fecha!!.compareTo(fecha2)<0) {
                throw BusinessException("La fecha de contratacion no puede ser menor a la fecha de nacimiento")
            }else if (fecha2!!.compareTo(fecha)>0) {
                throw BusinessException("La fecha de nacimeinto no puede ser mayor a la fecha de contratacion")
            }
        } catch (e:Exception) {
            throw BusinessException("Error en el parseo de fecha")
        }
        try{
            if (empleado.nombre.length == 0){
                throw BusinessException("El nombre no puede estar vacio")
            }else if (empleado.direccion.length == 0){
                throw BusinessException("La direccion no puede estar vacia")
            }else if (empleado.telefono.toString().length < 8) {
                throw BusinessException("El telefono no pueden ser menor a 8 digitos")
            }else if (empleado.telefono.toString().length > 8) {
                throw BusinessException("El telefono no puede ser mayor a 8 digitos")
            }else if (empleado.salario < 0.0){
                throw BusinessException("El salario no puede ser menor a 0.0")
            }else if (empleado.salario.toString().length == 0){
                throw BusinessException("El salario no puede estar vacio")
            }else if (empleado.puesto.length == 0){
                throw BusinessException("El puesto no puede estar vacio")
            }else if (empleado.contrasena.length == 0){
                throw BusinessException("La contrasena no puede estar vacia")
            }else if (empleado.fechaContratacion.length == 0){
                throw BusinessException("La fecha de contratacion no puede estar vacio")
            }else if (empleado.fechaNacimiento.length == 0) {
                throw BusinessException("La fecha de nacimiento no puede estar vacio")
            }
            opt = empleadoRepository!!.findById(empleado.empleadoId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el empleado ${empleado.empleadoId}")
        }
        else{
            try {
                return empleadoRepository!!.save(empleado)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }


}
