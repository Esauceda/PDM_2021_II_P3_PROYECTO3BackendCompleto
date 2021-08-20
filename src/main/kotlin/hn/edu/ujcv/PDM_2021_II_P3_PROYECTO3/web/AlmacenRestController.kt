package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Almacen.IAlmacenBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Almacen
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(Constants.URL_BASE_ALMACEN)
class AlmacenRestController {

    @Autowired
    val almacenBusiness: IAlmacenBusiness? = null

        @GetMapping("")
    fun list(): ResponseEntity<List<Almacen>> {
        return try {
            ResponseEntity(almacenBusiness!!.getAlmacenes(), HttpStatus.OK)
        }catch(e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idAlmacen: Long): ResponseEntity<Almacen> {
        return try{
            ResponseEntity(almacenBusiness!!.getAlmacenById(idAlmacen), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/encargado/{encargado}")
    fun loadByEncargado(@PathVariable("encargado") encargado:String): ResponseEntity<Almacen> {
        return try{
            ResponseEntity(almacenBusiness!!.getAlmacenByEncargado(encargado), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addAlmacen")
    fun insert(@RequestBody almacen: Almacen): ResponseEntity<Any> {
        return try {
            almacenBusiness!!.saveAlmacen(almacen)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_ALMACEN+"/"+almacen.almacenId)
            ResponseEntity(almacen, responseHeader, HttpStatus.CREATED)
        }catch(e: BusinessException) {
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",
                    e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addAlmacenes")
    fun insert(@RequestBody almacenes:List<Almacen>): ResponseEntity<Any> {
        return try {
            ResponseEntity(almacenBusiness!!.saveAlmacenes(almacenes), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody almacen: Almacen): ResponseEntity<Any> {
        return try {
            almacenBusiness!!.updateAlmacen(almacen)
            ResponseEntity(almacen, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idAlmacen: Long): ResponseEntity<Any> {
        return try{
            almacenBusiness!!.removeAlmacen(idAlmacen)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}