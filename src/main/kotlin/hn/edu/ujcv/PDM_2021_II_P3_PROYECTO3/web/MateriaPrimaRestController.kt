package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.MateriaPrima.IMateriaPrimaBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.MateriaPrima
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_MATERIAPRIMA)
class MateriaPrimaRestController {
    @Autowired
    val materiaBusiness: IMateriaPrimaBusiness?=null


    @GetMapping("")
    fun list(): ResponseEntity<List<MateriaPrima>> {
        return try{
            ResponseEntity(materiaBusiness!!.getMateriaPrimas(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idMateria: Long): ResponseEntity<MateriaPrima> {
        return try{
            ResponseEntity(materiaBusiness!!.getMateriaById(idMateria), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombreMateria/{nombreMateria}")
    fun loadByNombre(@PathVariable("nombreMateria") nombreMateria:String): ResponseEntity<MateriaPrima> {
        return try{
            ResponseEntity(materiaBusiness!!.getMateriaByNombre(nombreMateria), HttpStatus.OK)
        }catch(e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addMateria")
    fun insert(@RequestBody materia: MateriaPrima): ResponseEntity<Any> {
        return try {
            materiaBusiness!!.saveMateria(materia)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_MATERIAPRIMA+"/"+materia.materiaprimaId)
            ResponseEntity(materia, responseHeader, HttpStatus.CREATED)
        }catch(e:BusinessException) {
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion enviada no es valida",
                    e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addMaterias")
    fun insert(@RequestBody materias:List<MateriaPrima>): ResponseEntity<Any> {
        return try {
            ResponseEntity(materiaBusiness!!.saveMateriaPrimas(materias), HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody materia:MateriaPrima): ResponseEntity<Any> {
        return try {
            materiaBusiness!!.updateMateria(materia)
            ResponseEntity(materia, HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idMateria: Long): ResponseEntity<Any> {
        return try{
            materiaBusiness!!.removeMateria(idMateria)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}