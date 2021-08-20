package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web


import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.OrdenEncabezado.IOrdenEncabezadoBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.OrdenEncabezado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(Constants.URL_BASE_ORDENENCABEZADO)

class OrdenEcabezadoRestController {
    @Autowired
    val ordenEncabezadoBusiness : IOrdenEncabezadoBusiness? = null

    @GetMapping("")
    fun list():ResponseEntity<List<OrdenEncabezado>>{

        return try {
            ResponseEntity(ordenEncabezadoBusiness!!.getOrdendesEncabezado(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") ordenEncabezadoId:Long):ResponseEntity<OrdenEncabezado>{
        return try {
            ResponseEntity(ordenEncabezadoBusiness!!.getOrdenEncabezadoById(ordenEncabezadoId), HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/status/{status}")
    fun laodByStatus(@PathVariable("status") estadoOrdenEncabezado:String):ResponseEntity<OrdenEncabezado>{
        return try {
            ResponseEntity(ordenEncabezadoBusiness!!.getOrdenEncabezadoByEstado(estadoOrdenEncabezado), HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addOrdenEncabezado")
    fun insert(@RequestBody ordenEncabezado: OrdenEncabezado): ResponseEntity<Any>{
        return try {
            ordenEncabezadoBusiness!!.saveOrdenEncabezado(ordenEncabezado)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_ORDENENCABEZADO+"/"+ordenEncabezado.ordenId)
            ResponseEntity(ordenEncabezado, responseHeader, HttpStatus.CREATED)
        }catch (e:BusinessException){
           // ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
            "Informacion no valida",
            e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addOrdenesEncabezado")
    fun insert(@RequestBody ordenesEncabezado:List<OrdenEncabezado>):ResponseEntity<Any>{
        return try {
            ResponseEntity(ordenEncabezadoBusiness!!.saveOrdenesEncabezado(ordenesEncabezado), HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody ordenEncabezado: OrdenEncabezado):ResponseEntity<Any>{
        return try {
            ordenEncabezadoBusiness!!.updateOrdenEncabezado(ordenEncabezado)
            ResponseEntity(ordenEncabezado, HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable ("id")ordenEncabezadoId: Long):ResponseEntity<Any>{
        return try {
            ordenEncabezadoBusiness!!.removeOrdenEncabezado(ordenEncabezadoId)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}//-----