package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.MateriaPrima

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.MateriaPrimaRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.MateriaPrima
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MateriaPrimaBusiness: IMateriaPrimaBusiness {


    @Autowired
    val materiaRepository: MateriaPrimaRepository?=null

    @Throws(BusinessException::class)
    override fun getMateriaPrimas(): List<MateriaPrima> {
        try {
            return materiaRepository!!.findAll();
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getMateriaById(materiaprimaId: Long): MateriaPrima {
        val opt: Optional<MateriaPrima>
        try{
            opt = materiaRepository!!.findById(materiaprimaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la materia prima $materiaprimaId")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveMateria(materia: MateriaPrima): MateriaPrima {
        try {
            if (materia.materiaprimaId<0) {
                throw BusinessException("El id de la materia prima no puede estar vacio")
            }
            if (materia.almacenId<=0) {
                throw BusinessException("El id del almacen no puede ser menor o igual a 0")
            }
            if (materia.almacenId.toString().length == 0) {
                throw BusinessException("El id del almacen no puede estar vacio")
            }
            if (materia.cantidad.toString().length == 0) {
                throw BusinessException("La cantidad de materia prima no puede estar vacio")
            }
            if (materia.cantidad <= 0) {
                throw BusinessException("La cantidad de materia prima no puede ser menor a 0")
            }
            if (materia.descripcion.length == 0) {
                throw BusinessException("La descripcion no puede estar vacio")
            }
            if (materia.nombreMateria.length == 0) {
                throw BusinessException("El nombre de la materia prima no puede estar vacio no puede estar vacio")
            }
            if (materia.proveedorId.toString().length == 0) {
                throw BusinessException("El id del proveedor no puede estar vacio")
            }
            if (materia.proveedorId < 0) {
                throw BusinessException("El id del proveedor no puede ser menor a 0")
            }
            return materiaRepository!!.save(materia)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveMateriaPrimas(materias: List<MateriaPrima>): List<MateriaPrima> {
        try {
            return materiaRepository!!.saveAll(materias)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeMateria(materiaprimaId: Long) {
        val opt: Optional<MateriaPrima>
        try{
            opt = materiaRepository!!.findById(materiaprimaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la materia prima $materiaprimaId")
        }
        else{
            try{
                materiaRepository!!.deleteById(materiaprimaId)
            }catch (e:Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getMateriaByNombre(nombreMateria: String): MateriaPrima {
        val opt: Optional<MateriaPrima>
        try{
            opt = materiaRepository!!.findByNombreMateria(nombreMateria)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la materia prima $nombreMateria")
        }
        return opt.get()
    }


    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateMateria(materia: MateriaPrima): MateriaPrima {
        val opt: Optional<MateriaPrima>
        try{
        if (materia.almacenId<=0) {
            throw BusinessException("El id del almacen no puede ser menor o igual a 0")
        }
        if (materia.almacenId.toString().length == 0) {
            throw BusinessException("El id del almacen no puede estar vacio")
        }
        if (materia.cantidad.toString().length == 0) {
            throw BusinessException("La cantidad de materia prima no puede estar vacio")
        }
        if (materia.cantidad <= 0) {
            throw BusinessException("La cantidad de materia prima no puede ser menor a 0")
        }
        if (materia.descripcion.length == 0) {
            throw BusinessException("La descripcion no puede estar vacio")
        }
        if (materia.nombreMateria.length == 0) {
            throw BusinessException("El nombre de la materia prima no puede estar vacio no puede estar vacio")
        }
        if (materia.proveedorId.toString().length == 0) {
            throw BusinessException("El id del proveedor no puede estar vacio")
        }
        if (materia.proveedorId < 0) {
            throw BusinessException("El id del proveedor no puede ser menor a 0")
        }
            opt = materiaRepository!!.findById(materia.materiaprimaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la materia prima ${materia.materiaprimaId}")
        }
        else{
            try {
                return materiaRepository!!.save(materia)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}