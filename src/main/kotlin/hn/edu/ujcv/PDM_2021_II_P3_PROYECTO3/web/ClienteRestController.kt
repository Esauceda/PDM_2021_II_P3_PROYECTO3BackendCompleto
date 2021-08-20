package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Cliente.IClienteBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_CLIENTES)
class ClienteRestController {
    @Autowired
    val clienteBusiness: IClienteBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Cliente>> {
        return try {
            ResponseEntity(clienteBusiness!!.getClientes(), HttpStatus.OK)
        }catch(e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idCliente: Long): ResponseEntity<Cliente> {
        return try{
            ResponseEntity(clienteBusiness!!.getClienteById(idCliente), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreCliente:String): ResponseEntity<Cliente> {
        return try{
            ResponseEntity(clienteBusiness!!.getClienteByNombre(nombreCliente), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addCliente")
    fun insert(@RequestBody cliente: Cliente): ResponseEntity<Any> {
        return try {
            clienteBusiness!!.saveCliente(cliente)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_CLIENTES+"/"+cliente.clienteId)
            ResponseEntity(cliente, responseHeader, HttpStatus.CREATED)
        }catch(e: BusinessException) {
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",
                    e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addClientes")
    fun insert(@RequestBody clientes:List<Cliente>): ResponseEntity<Any> {
        return try {
            ResponseEntity(clienteBusiness!!.saveClientes(clientes), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody cliente: Cliente): ResponseEntity<Any> {
        return try {
            clienteBusiness!!.updateCliente(cliente)
            ResponseEntity(cliente, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idCliente: Long): ResponseEntity<Any> {
        return try{
            clienteBusiness!!.removeCliente(idCliente)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}