package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Fabrica

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.FabricaRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Fabrica
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.Throws

@Service
class FabricaBusiness: IFabricaBusiness {

    @Autowired
    val fabricaRepository: FabricaRepository?=null

    @Throws(BusinessException::class)
    override fun getFabricas(): List<Fabrica> {
        try{
            return fabricaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFabricaById(fabricaId: Long): Fabrica {
        val opt: Optional<Fabrica>
        try{
            opt = fabricaRepository!!.findById(fabricaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la Fabrica $fabricaId")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveFabrica(fabrica: Fabrica): Fabrica {
        try {
            if (fabrica.fabricaId<0)
                throw BusinessException("El id no puede ser menor o igual a 0")
            if (fabrica.nombreFabrica.length == 0){
                throw BusinessException("El nombre no puede estar vacio")
            }
            if (fabrica.telefono.toString().length <8){
                throw BusinessException("El telefono no puede tener menos de 8 digitos")
            }
            if (fabrica.telefono.toString().length >8){
                throw BusinessException("El telefono no puede tener mas de 8 digitos")
            }
            if (fabrica.tipoProduccion.length == 0){
                throw BusinessException("El tipo de produccion no puede estar vacio")
            }
            if (fabrica.direccion.length == 0){
                throw BusinessException("La direccion no puede estar vacia")
            }
            if (fabrica.encargado.length == 0){
                throw BusinessException("El encargado no puede estar vacio")
            }
            return fabricaRepository!!.save(fabrica)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveFabricas(fabricas: List<Fabrica>): List<Fabrica> {
        try {
            return fabricaRepository!!.saveAll(fabricas)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeFabrica(fabricaId: Long) {
        val opt: Optional<Fabrica>
        try{
            if (fabricaId < 0)
                throw BusinessException("El Id de fabrica no puede ser menor o igual a 0")
            opt = fabricaRepository!!.findById(fabricaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la fabrica $fabricaId")
        }
        else{
            try{
                fabricaRepository!!.deleteById(fabricaId)
            }catch (e:Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFabricaByNombre(nombreFabrica:String): Fabrica {
        val opt: Optional<Fabrica>
        try{
            opt = fabricaRepository!!.findBynombreFabrica(nombreFabrica)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la Fabrica $nombreFabrica")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateFabrica(fabrica: Fabrica): Fabrica {
        val opt: Optional<Fabrica>
        try{
            if (fabrica.nombreFabrica.length == 0){
                throw BusinessException("El nombre no puede estar vacio")
            }
            if (fabrica.telefono.toString().length <8){
                throw BusinessException("El telefono no puede tener menos de 8 digitos")
            }
            if (fabrica.telefono.toString().length >8){
                throw BusinessException("El telefono no puede tener mas de 8 digitos")
            }
            if (fabrica.tipoProduccion.length == 0){
                throw BusinessException("El tipo de produccion no puede estar vacio")
            }
            if (fabrica.direccion.length == 0){
                throw BusinessException("La direccion no puede estar vacia")
            }
            if (fabrica.encargado.length == 0){
                throw BusinessException("El encargado no puede estar vacio")
            }
            opt = fabricaRepository!!.findById(fabrica.fabricaId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la fabrica ${fabrica.fabricaId}")
        }
        else{
            try {
                return fabricaRepository!!.save(fabrica)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}