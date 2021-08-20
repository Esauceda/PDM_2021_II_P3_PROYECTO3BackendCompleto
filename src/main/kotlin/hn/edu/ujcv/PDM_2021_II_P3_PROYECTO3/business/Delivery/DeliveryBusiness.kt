package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Delivery

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.DeliveryRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Delivery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeliveryBusiness: IDeliveryBusiness {

    @Autowired
    val deliveryRepository: DeliveryRepository?=null
    @Throws(BusinessException::class)
    override fun getDeliverys(): List<Delivery> {
        try {
            return deliveryRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getDeliveryById(idDelivery: Long): Delivery {
        val opt: Optional<Delivery>
        try{
            opt = deliveryRepository!!.findById(idDelivery)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el delivery $idDelivery")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveDelivery(delivery: Delivery): Delivery {
        try {
            if (delivery.deliveryId < 0){
                throw BusinessException("El id que ingreso no es valido")
            }else if (delivery.ordenId.toString().length <= 0){
                throw BusinessException("El id que ingreso no es valido")
            }else if (delivery.nombreCompania.length == 0){
                throw BusinessException("El nombre de la compania no puede estar vacio")
            }else if (delivery.numero.toString().length < 8) {
                throw BusinessException("El telefono no pueden ser menor a 8 digitos")
            }else if (delivery.numero.toString().length > 8) {
                throw BusinessException("El telefono no puede ser mayor a 8 digitos")
            }else if (delivery.correo.length == 0){
                throw BusinessException("El correo no puede estar vacio")
            }else if (delivery.fechaEntrega.length == 0){
                throw BusinessException("La fecha de entrega no puede estar vacia")
            }
            return deliveryRepository!!.save(delivery)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveDeliverys(delivery: List<Delivery>): List<Delivery> {
        try {
            return deliveryRepository!!.saveAll(delivery)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeDelivery(idDelivery: Long) {
        val opt: Optional<Delivery>
        try{
            if (idDelivery <= 0){
                throw BusinessException("El id que ingreso no es valido")
            }
            opt = deliveryRepository!!.findById(idDelivery)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el delivery $idDelivery")
        }
        else{
            try{
                deliveryRepository!!.deleteById(idDelivery)
            }catch (e:Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getDeliveryByNombre(nombreDelivery: String): Delivery {
        val opt: Optional<Delivery>
        try{
            if (nombreDelivery.isEmpty()){
                throw BusinessException("El nombre no puede estar vacio")
            }
            opt = deliveryRepository!!.findByNombreCompania(nombreDelivery)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el delivery $nombreDelivery")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateDelivery(delivery: Delivery): Delivery {
        val opt: Optional<Delivery>
        try{
            if (delivery.ordenId.toString().length <= 0){
                throw BusinessException("El id que ingreso no es valido")
            }else if (delivery.nombreCompania.length == 0){
                throw BusinessException("El nombre de la compania no puede estar vacio")
            }else if (delivery.numero.toString().length < 8) {
                throw BusinessException("El telefono no pueden ser menor a 8 digitos")
            }else if (delivery.numero.toString().length > 8) {
                throw BusinessException("El telefono no puede ser mayor a 8 digitos")
            }else if (delivery.correo.length == 0){
                throw BusinessException("El correo no puede estar vacio")
            }else if (delivery.fechaEntrega.length == 0){
                throw BusinessException("La fecha de entrega no puede estar vacia")
            }
            opt = deliveryRepository!!.findById(delivery.deliveryId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el delivery ${delivery.deliveryId}")
        }
        else{
            try {
                return deliveryRepository!!.save(delivery)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

}