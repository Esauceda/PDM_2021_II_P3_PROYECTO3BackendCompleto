package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Delivery.IDeliveryBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Delivery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_DELIVERYS)
class DeliveryRestController {

    @Autowired
    val deliveryBusiness: IDeliveryBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Delivery>> {
        return try {
            ResponseEntity(deliveryBusiness!!.getDeliverys(), HttpStatus.OK)
        }catch(e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idDelivery: Long): ResponseEntity<Delivery> {
        return try{
            ResponseEntity(deliveryBusiness!!.getDeliveryById(idDelivery), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/nombreCompania/{nombreCompania}")
    fun loadByNombre(@PathVariable("nombreCompania") nombreDelivery:String): ResponseEntity<Delivery> {
        return try{
            ResponseEntity(deliveryBusiness!!.getDeliveryByNombre(nombreDelivery), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addDelivery")
    fun insert(@RequestBody delivery: Delivery): ResponseEntity<Any> {
        return try {
            deliveryBusiness!!.saveDelivery(delivery)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_DELIVERYS+"/"+delivery.deliveryId)
            ResponseEntity(delivery, responseHeader, HttpStatus.CREATED)
        }catch(e: BusinessException) {
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",
                    e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addDeliverys")
    fun insert(@RequestBody deliverys:List<Delivery>): ResponseEntity<Any> {
        return try {
            ResponseEntity(deliveryBusiness!!.saveDeliverys(deliverys), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody delivery: Delivery): ResponseEntity<Any> {
        return try {
            deliveryBusiness!!.updateDelivery(delivery)
            ResponseEntity(delivery, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idDelivery: Long): ResponseEntity<Any> {
        return try{
            deliveryBusiness!!.removeDelivery(idDelivery)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}