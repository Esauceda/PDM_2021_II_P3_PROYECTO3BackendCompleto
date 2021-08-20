package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Proveedor.IProveedorBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Proveedor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PROVEEDORES)
class ProveedorRestController {
    @Autowired
    val proovedorBusiness: IProveedorBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Proveedor>> {
        return try {
            ResponseEntity(proovedorBusiness!!.getProveedores(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idProveedor: Long): ResponseEntity<Proveedor> {
        return try {
            ResponseEntity(proovedorBusiness!!.getProveedorById(idProveedor), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombreCompania/{nombreCompania}")
    fun loadByNombre(@PathVariable("nombreCompania") nombreCompania: String): ResponseEntity<Proveedor> {
        return try {
            ResponseEntity(proovedorBusiness!!.getProveedorByNombreCompania(nombreCompania), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addProveedor")
    fun insert(@RequestBody proveedor: Proveedor): ResponseEntity<Any> {
        return try {
            proovedorBusiness!!.saveProveedor(proveedor)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_PROVEEDORES + "/" + proveedor.proveedorId)
            ResponseEntity(proveedor, responseHeader, HttpStatus.CREATED)
        } catch (e: BusinessException) {
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",
                    e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addProveedores")
    fun insert(@RequestBody proveedores: List<Proveedor>): ResponseEntity<Any> {
        return try {
            ResponseEntity(proovedorBusiness!!.saveProveedores(proveedores), HttpStatus.CREATED)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody proveedor: Proveedor): ResponseEntity<Any> {
        return try {
            proovedorBusiness!!.updateProveedor(proveedor)
            ResponseEntity(proveedor, HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idProveedor: Long): ResponseEntity<Any> {
        return try {
            proovedorBusiness!!.removeProveedor(idProveedor)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}