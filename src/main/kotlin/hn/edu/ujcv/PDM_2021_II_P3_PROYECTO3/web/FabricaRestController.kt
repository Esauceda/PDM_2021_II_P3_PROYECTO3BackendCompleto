package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Fabrica.IFabricaBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Fabrica
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_FABRICA)
class FabricaRestController {

    @Autowired
    val fabricaBusiness : IFabricaBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Fabrica>> {
        return try {
            ResponseEntity(fabricaBusiness!!.getFabricas(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") fabricaId: Long): ResponseEntity<Fabrica> {
        return try{
            ResponseEntity(fabricaBusiness!!.getFabricaById(fabricaId), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreFabrica:String): ResponseEntity<Fabrica> {
        return try{
            ResponseEntity(fabricaBusiness!!.getFabricaByNombre(nombreFabrica), HttpStatus.OK)
        }catch(e:BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addFabrica")
    fun insert(@RequestBody fabrica: Fabrica): ResponseEntity<Any> {
        return try {
            fabricaBusiness!!.saveFabrica(fabrica)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_FABRICA+"/"+fabrica.fabricaId)
            ResponseEntity(fabrica, responseHeader, HttpStatus.CREATED)
        }catch(e:BusinessException) {
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",
                    e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addFabricas")
    fun insert(@RequestBody fabricas:List<Fabrica>): ResponseEntity<Any> {
        return try {
            ResponseEntity(fabricaBusiness!!.saveFabricas(fabricas), HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody fabrica: Fabrica): ResponseEntity<Any> {
        return try {
            fabricaBusiness!!.updateFabrica(fabrica)
            ResponseEntity(fabrica, HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") fabricaId: Long): ResponseEntity<Any> {
        return try{
            fabricaBusiness!!.removeFabrica(fabricaId)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}