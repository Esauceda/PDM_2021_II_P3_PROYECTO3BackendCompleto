package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.OrdenDetalle.IOrdenDetalleBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.OrdenDetalle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_ORDENEDETALLE)
class OrdenDetalleController {

    @Autowired
    val ordenDetalleBusiness: IOrdenDetalleBusiness? = null

    //GETS
    @GetMapping("")
    fun list(): ResponseEntity<List<OrdenDetalle>> {
        return try {
            ResponseEntity(ordenDetalleBusiness!!.getOrdenesDetalle(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    //GET
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") ordenId:Long):ResponseEntity<OrdenDetalle>{
        return try {
            ResponseEntity(ordenDetalleBusiness!!.getOrdenDetalleById(ordenId), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    //GETORDENDETALLEBYORDENID
    @GetMapping("/ordenId/{ordenId}")
    fun listByOrdenId(@PathVariable("ordenId") ordenId: Long ): ResponseEntity<List<OrdenDetalle>>{
        return try {
            ResponseEntity(ordenDetalleBusiness!!.getOrdenDetalleByOrdenId(ordenId), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    //ADD
    @PostMapping("/addOrdenDetalle")
    fun insert(@RequestBody ordenDetalle: OrdenDetalle):ResponseEntity<Any>{
        return try {
            ordenDetalleBusiness!!.saveOrdenDetalle(ordenDetalle)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_ORDENEDETALLE+"/"+ordenDetalle.ordenDetalleId)
            ResponseEntity(ordenDetalle, responseHeader, HttpStatus.CREATED)
        }catch (e:BusinessException){
            //return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion Enviada no es Valida",
                    e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //ADDS
    @PostMapping("/addOrdenesDetalle")
    fun insert(@RequestBody ordenesDetalle:List<OrdenDetalle>):ResponseEntity<Any>{
        return try {
            ResponseEntity(ordenDetalleBusiness!!.saveOrdenesDetalle(ordenesDetalle),HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //UPDATE
    @PutMapping("")
    fun update(@RequestBody ordeDetalle:OrdenDetalle):ResponseEntity<Any>{
        return try {
            ordenDetalleBusiness!!.updateOrdenDetalle(ordeDetalle)
            ResponseEntity(ordeDetalle,HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") ordenId: Long):ResponseEntity<Any> {
        return try {
            ordenDetalleBusiness!!.removeOrdenDetalle(ordenId)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }
}