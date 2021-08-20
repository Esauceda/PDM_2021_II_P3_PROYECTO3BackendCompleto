package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.web

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.Constants
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.RestApiError
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Maquinaria.IMaquinariaBusiness
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Maquinaria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(Constants.URL_BASE_MAQUINARIA)
class MaquinariaController {

    @Autowired
    val maquinariaBusiness: IMaquinariaBusiness? = null

    //GETS
    @GetMapping("")
    fun list(): ResponseEntity<List<Maquinaria>> {
        return try {
            ResponseEntity(maquinariaBusiness!!.getMaquinarias(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    //GET
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") maquinariaId:Long):ResponseEntity<Maquinaria>{
        return try {
            ResponseEntity(maquinariaBusiness!!.getMaquinariaById(maquinariaId), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    //GETBYFABRICAID
    @GetMapping("/fabricaId/{fabricaId}")
    fun loadByFabricaId(@PathVariable("fabricaId") fabricaId:Long):ResponseEntity<Maquinaria>{
        return try {
            ResponseEntity(maquinariaBusiness!!.getMaquinariaByFabricaId(fabricaId), HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    //ADD
    @PostMapping("/addMaquinaria")
    fun insert(@RequestBody maquinaria: Maquinaria):ResponseEntity<Any>{
        return try {
            maquinariaBusiness!!.saveMaquinaria(maquinaria)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_MAQUINARIA+"/"+maquinaria.maquinariaId)
            ResponseEntity(maquinaria, responseHeader, HttpStatus.CREATED)
        }catch (e:BusinessException){
            //return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Informacion Enviada no es Valida",
                    e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    //ADDS
    @PostMapping("/addMaquinarias")
    fun insert(@RequestBody maquinarias:List<Maquinaria>):ResponseEntity<Any>{
        return try {
            ResponseEntity(maquinariaBusiness!!.saveMaquinarias(maquinarias),HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    //UPDATE
    @PutMapping("")
    fun update(@RequestBody maquinaria:Maquinaria):ResponseEntity<Any>{
        return try {
            maquinariaBusiness!!.updateMaquinaria(maquinaria)
            ResponseEntity(maquinaria,HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    //DELETE
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") maquinariaId: Long):ResponseEntity<Any> {
        return try {
            maquinariaBusiness!!.removeMaquinaria(maquinariaId)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }
}//-----