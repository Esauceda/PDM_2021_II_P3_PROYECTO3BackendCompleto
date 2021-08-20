package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Empleado.IEmpleadoBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Empleado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_EMPLEADOS)
class EmpleadoRestController {
    @Autowired
    val empleadoBusiness: IEmpleadoBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Empleado>> {
        return try {
            ResponseEntity(empleadoBusiness!!.getEmpleados(), HttpStatus.OK)
        }catch(e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idEmpleado: Long):ResponseEntity<Empleado>{
        return try{
            ResponseEntity(empleadoBusiness!!.getEmpleadoById(idEmpleado), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreEmpleado:String):ResponseEntity<Empleado>{
        return try{
            ResponseEntity(empleadoBusiness!!.getEmpleadoByNombre(nombreEmpleado), HttpStatus.OK)
        }catch(e:BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addEmpleado")
    fun insert(@RequestBody empleado: Empleado):ResponseEntity<Any>{
        return try {
            empleadoBusiness!!.saveEmpleado(empleado)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_EMPLEADOS+"/"+empleado.empleadoId)
            ResponseEntity(empleado, responseHeader, HttpStatus.CREATED)
        }catch(e:BusinessException) {
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",
                    e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addEmpleados")
    fun insert(@RequestBody empleados:List<Empleado>):ResponseEntity<Any>{
        return try {
            ResponseEntity(empleadoBusiness!!.saveEmpleados(empleados), HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody empleado: Empleado):ResponseEntity<Any>{
        return try {
            empleadoBusiness!!.updateEmpleado(empleado)
            ResponseEntity(empleado, HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idEmpleado: Long):ResponseEntity<Any>{
        return try{
            empleadoBusiness!!.removeEmpleado(idEmpleado)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}