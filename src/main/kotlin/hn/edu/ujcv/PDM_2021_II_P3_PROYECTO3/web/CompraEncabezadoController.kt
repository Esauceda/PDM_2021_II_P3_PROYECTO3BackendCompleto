package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.CompraEncabezado.ICompraEncabezadoBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.CompraEncabezado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_COMPRAENCABEZADO)
class CompraEncabezadoController {
    @Autowired
    val compraEncabezadoBusiness: ICompraEncabezadoBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<CompraEncabezado>> {
        return try {
            ResponseEntity(compraEncabezadoBusiness!!.getComprasEncabezado(), HttpStatus.OK)
        }catch(e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") compraencabezadoId: Long): ResponseEntity<CompraEncabezado> {
        return try{
            ResponseEntity(compraEncabezadoBusiness!!.getCompraEncabezadoById(compraencabezadoId), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addcompraEncabezado")
    fun insert(@RequestBody compraEncabezado: CompraEncabezado): ResponseEntity<Any> {
        return try {
            compraEncabezadoBusiness!!.saveCompraEncabezado(compraEncabezado)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_COMPRAENCABEZADO+"/"+compraEncabezado.compraId)
            ResponseEntity(compraEncabezado, responseHeader, HttpStatus.CREATED)
        }catch(e:BusinessException) {
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",
                    e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addcomprasEncabezado")
    fun insert(@RequestBody comprasEnca:List<CompraEncabezado>): ResponseEntity<Any> {
        return try {
            ResponseEntity(compraEncabezadoBusiness!!.saveComprasEncabezado(comprasEnca), HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody compraEncabezado:CompraEncabezado): ResponseEntity<Any> {
        return try {
            compraEncabezadoBusiness!!.updateCompraEncabezado(compraEncabezado)
            ResponseEntity(compraEncabezado, HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") compraencabezadoId: Long): ResponseEntity<Any> {
        return try{
            compraEncabezadoBusiness!!.removeCompraEncabezado(compraencabezadoId)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}