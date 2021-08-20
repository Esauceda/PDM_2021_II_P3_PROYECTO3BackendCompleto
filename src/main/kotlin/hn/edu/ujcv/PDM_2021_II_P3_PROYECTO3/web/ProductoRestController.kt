package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Producto.IProductoBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Producto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PRODUCTOS)
class ProductoRestController {
    @Autowired
    val productoBusiness: IProductoBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Producto>> {
        return try {
            ResponseEntity(productoBusiness!!.getProductos(), HttpStatus.OK)
        }catch(e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idProducto: Long): ResponseEntity<Producto> {
        return try{
            ResponseEntity(productoBusiness!!.getProductoById(idProducto), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/nombreProducto/{nombreProducto}")
    fun loadByNombre(@PathVariable("nombreProducto") nombreProducto:String): ResponseEntity<Producto> {
        return try{
            ResponseEntity(productoBusiness!!.getProductoByNombre(nombreProducto), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addProducto")
    fun insert(@RequestBody producto: Producto): ResponseEntity<Any> {
        return try {
            productoBusiness!!.saveProducto(producto)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_PRODUCTOS+"/"+producto.productoId)
            ResponseEntity(producto, responseHeader, HttpStatus.CREATED)
        }catch(e: BusinessException) {
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",
                    e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addProductos")
    fun insert(@RequestBody productos:List<Producto>): ResponseEntity<Any> {
        return try {
            ResponseEntity(productoBusiness!!.saveProductos(productos), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody producto: Producto): ResponseEntity<Any> {
        return try {
            productoBusiness!!.updateProducto(producto)
            ResponseEntity(producto, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idProducto: Long): ResponseEntity<Any> {
        return try{
            productoBusiness!!.removeProducto(idProducto)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}