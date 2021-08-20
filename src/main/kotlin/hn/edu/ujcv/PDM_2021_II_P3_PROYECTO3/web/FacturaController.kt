package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Factura.IFacturaBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Factura
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_FACTURA)
class FacturaController{

    @Autowired
    val facturaBusiness: IFacturaBusiness? = null

    //GETS
    @GetMapping("")
    fun list(): ResponseEntity<List<Factura>> {
        return try {
            ResponseEntity(facturaBusiness!!.getFacturas(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    //GET
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") facturaId:Long):ResponseEntity<Factura>{
        return try {
            ResponseEntity(facturaBusiness!!.getFacturaById(facturaId), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    //GETBYORDENID
    @GetMapping("/ordenId/{ordenId}")
    fun loadByOrdenId(@PathVariable("ordenId") ordenId:Long):ResponseEntity<Factura>{
        return try {
            ResponseEntity(facturaBusiness!!.getFacturaByOrdenId(ordenId), HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    //ADD
    @PostMapping("/addFactura")
    fun insert(@RequestBody factura: Factura):ResponseEntity<Any>{
        return try {
            facturaBusiness!!.saveFactura(factura)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_FACTURA+"/"+factura.facturaId)
            ResponseEntity(factura, responseHeader, HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion Enviada no es Valida",
                    e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    //ADDS
    @PostMapping("/addFacturas")
    fun insert(@RequestBody facturas:List<Factura>):ResponseEntity<Any>{
        return try {
            ResponseEntity(facturaBusiness!!.saveFacturas(facturas),HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    //UPDATE
    @PutMapping("")
    fun update(@RequestBody factura:Factura):ResponseEntity<Any>{
        return try {
            facturaBusiness!!.updateFactura(factura)
            ResponseEntity(factura,HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    //DELETE
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") facturaId: Long):ResponseEntity<Any> {
        return try {
            facturaBusiness!!.removeFactura(facturaId)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }

}