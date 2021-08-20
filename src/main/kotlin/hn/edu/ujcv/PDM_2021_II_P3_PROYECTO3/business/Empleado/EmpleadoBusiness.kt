package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Empleado

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.EmpleadoRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Empleado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
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
            }else if (empleado.fechaNacimiento.length == 0){
                throw BusinessException("La fecha de nacimiento no puede estar vacio")
            }else if (empleado.fechaContratacion.length == 0){
                throw BusinessException("La fecha de contratacion no puede estar vacia")
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
            }else if (empleado.fechaNacimiento.length == 0){
                throw BusinessException("La fecha de nacimiento no puede estar vacio")
            }else if (empleado.fechaContratacion.length == 0){
                throw BusinessException("La fecha de contratacion no puede estar vacia")
            }else if (empleado.contrasena.length == 0){
                throw BusinessException("La contrasena no puede estar vacia")
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
