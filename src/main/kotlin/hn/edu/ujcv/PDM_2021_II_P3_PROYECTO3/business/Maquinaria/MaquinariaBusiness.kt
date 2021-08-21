package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Maquinaria

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.MaquinariaRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Maquinaria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class MaquinariaBusiness: IMaquinariaBusiness {

    @Autowired
    val maquinariaRepository: MaquinariaRepository? = null

    //GETS
    @Throws(BusinessException::class)
    override fun getMaquinarias(): List<Maquinaria> {
        try {
            return maquinariaRepository!!.findAll();
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    //GET
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getMaquinariaById(maquinariaId: Long): Maquinaria {
        val opt: Optional<Maquinaria>
        try {
            if (maquinariaId <= 0)
                throw BusinessException("El Id de maquinaria no puede ser menor o igual a 0")

            opt = maquinariaRepository!!.findById(maquinariaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $maquinariaId")
        }
        return opt.get()
    }

    //SAVE
    @Throws(BusinessException::class)
    override fun saveMaquinaria(maquinaria: Maquinaria): Maquinaria {
        try {
            if (maquinaria.maquinaId < 0)
                throw BusinessException("El Id de la maquinaria no puede ser menor o igual a 0")
            if (maquinaria.fabricaId <= 0)
                throw BusinessException("El Id de la fabrica no puede ser menor o igual a 0")
            if (maquinaria.fabricaId.toString().length == 0)
                throw BusinessException("El Id de la fabrica no puede estar vacio")
            if (maquinaria.marca.length == 0)
                throw BusinessException("La marca esta vacia")
            if (maquinaria.horasUso <= 0)
                throw BusinessException("La horas de uso no puede ser menor o igual a 0")
            if (maquinaria.horasUso.toString().length == 0)
                throw BusinessException("La horas de uso no pueden estar vacias")
            if (maquinaria.tipoMaquina.length == 0)
                throw BusinessException("El tipo de maquinaria esta vacia")

            return maquinariaRepository!!.save(maquinaria)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    //SAVES
    @Throws(BusinessException::class)
    override fun saveMaquinarias(maquinarias: List<Maquinaria>): List<Maquinaria> {
        try {
            return maquinariaRepository!!.saveAll(maquinarias)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    //REMOVE
    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeMaquinaria(maquinariaId: Long) {
        val opt:Optional<Maquinaria>
        try {
            if (maquinariaId <= 0)
                throw BusinessException("El Id de la maquinaria no puede ser menor o igual a 0")

            opt = maquinariaRepository!!.findById(maquinariaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la maquinaria $maquinariaId")
        }
        else{
            try {
                maquinariaRepository!!.deleteById(maquinariaId)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    //GETBYFABRICAID
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getMaquinariaByFabricaId(fabricaId: Long): Maquinaria {
        val opt:Optional<Maquinaria>
        try {
            opt = maquinariaRepository!!.findByFabricaId(fabricaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la maquinaria con fabricaId $fabricaId")
        }
        return opt.get()
    }

    //UPDATE
    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateMaquinaria(maquinaria: Maquinaria): Maquinaria {
        val opt:Optional<Maquinaria>
        try {
            if (maquinaria.fabricaId <= 0)
                throw BusinessException("El Id de la fabrica no puede ser menor o igual a 0")
            if (maquinaria.fabricaId.toString().length == 0)
                throw BusinessException("El Id de la fabrica no puede estar vacio")
            if (maquinaria.marca.length == 0)
                throw BusinessException("La marca esta vacia")
            if (maquinaria.horasUso <= 0)
                throw BusinessException("La horas de uso no puede ser menor o igual a 0")
            if (maquinaria.horasUso.toString().length == 0)
                throw BusinessException("La horas de uso no pueden estar vacias")
            if (maquinaria.tipoMaquina.length == 0)
                throw BusinessException("El tipo de maquinaria esta vacia")

            opt = maquinariaRepository!!.findById(maquinaria.maquinaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la maquinaria ${maquinaria.maquinaId}")
        }
        else{
            try {
                    return maquinariaRepository!!.save(maquinaria)

            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
}