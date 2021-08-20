package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.CompraDetalle.ICompraDetalleBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.CompraDetalle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

class CompraDetalleRestController {
    @RestController
    @RequestMapping(Constants.URL_BASE_COMPRADETALLE)
    class CompraDetalleController {
        @Autowired
        val compraDetBusiness: ICompraDetalleBusiness? = null

        @GetMapping("")
        fun list(): ResponseEntity<List<CompraDetalle>> {
            return try {
                ResponseEntity(compraDetBusiness!!.getComprasDetalle(), HttpStatus.OK)
            } catch (e: Exception) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @GetMapping("/id/{id}")
        fun loadById(@PathVariable("id") idCompraDet: Long): ResponseEntity<CompraDetalle> {
            return try {
                ResponseEntity(compraDetBusiness!!.getCompraDetalleById(idCompraDet), HttpStatus.OK)
            } catch (e: BusinessException) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }

        @PostMapping("/addcompraDetalle")
        fun insert(@RequestBody compraDetalle: CompraDetalle): ResponseEntity<Any> {
            return try {
                compraDetBusiness!!.saveCompraDetalle(compraDetalle)
                val responseHeader = HttpHeaders()
                responseHeader.set("location", Constants.URL_BASE_COMPRADETALLE + "/" + compraDetalle.compraId)
                ResponseEntity(compraDetalle, responseHeader, HttpStatus.CREATED)
            } catch (e: BusinessException) {
                val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Informacion enviada no es valida",
                        e.message.toString())
                ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PostMapping("/addcomprasDetalle")
        fun insert(@RequestBody comprasDet: List<CompraDetalle>): ResponseEntity<Any> {
            return try {
                ResponseEntity(compraDetBusiness!!.saveComprasDetalle(comprasDet), HttpStatus.CREATED)
            } catch (e: BusinessException) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }

        @PutMapping("")
        fun update(@RequestBody compraDet: CompraDetalle): ResponseEntity<Any> {
            return try {
                compraDetBusiness!!.updateCompraDetalle(compraDet)
                ResponseEntity(compraDet, HttpStatus.OK)
            } catch (e: BusinessException) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }

        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable("id") idCompraDet: Long): ResponseEntity<Any> {
            return try {
                compraDetBusiness!!.removeCompraDetalle(idCompraDet)
                ResponseEntity(HttpStatus.OK)
            } catch (e: BusinessException) {
                ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            } catch (e: NotFoundException) {
                ResponseEntity(HttpStatus.NOT_FOUND)
            }
        }
    }
}