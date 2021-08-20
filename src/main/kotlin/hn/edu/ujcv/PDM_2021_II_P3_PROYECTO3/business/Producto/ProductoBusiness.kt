package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Producto

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.BusinessException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.NotFoundException
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.dao.ProductoRepository
import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Producto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductoBusiness: IProductoBusiness {

    @Autowired
    val productoRepository: ProductoRepository?=null

    @Throws(BusinessException::class)
    override fun getProductos(): List<Producto> {
        try {
            return productoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getProductoById(idProducto: Long): Producto {
        val opt: Optional<Producto>
        try{
            opt = productoRepository!!.findById(idProducto)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el producto $idProducto")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveProducto(producto: Producto): Producto {
        try {
            if (producto.productoId < 0){
                throw BusinessException("El Id no puede ser menor o igual a 0")
            } else if (producto.nombreProducto.length == 0) {
                throw BusinessException("El nombre no puede estar vacio")
            } else if (producto.descripcion.length == 0) {
                throw BusinessException("La descripcion no puede estar vacia")
            } else if (producto.precio <= 0.0) {
                throw BusinessException("El precio no puede ser menor o igual a 0.0")
            } else if (producto.precio.toString().length == 0) {
                throw BusinessException("El precio no puede estar vacio")
            }else if  (producto.unidadesEnAlmacen < 0) {
                throw BusinessException("Las unidades en almacen no pueden ser menor a 0")
            }else if  (producto.unidadesEnAlmacen.toString().length == 0) {
                throw BusinessException("Las unidades en almacen no pueden estar vacias")
            }else if  (producto.unidadesEnAlmacen > producto.unidadesMaximas) {
                throw BusinessException("Las unidades en almacen no pueden ser mayor a las unidades maximas")
            }else if  (producto.unidadesEnAlmacen > producto.unidadesMinimas) {
                throw BusinessException("Las unidades en almacen no pueden ser menor a las unidades minimas")
            }else if (producto.unidadesEnAlmacen.toString().length > 8) {
                throw BusinessException("Las unidades en almacen no pueden ser mayor a 8 digitos")
            }else if  (producto.unidadesMaximas <= 0) {
                throw BusinessException("Las unidades maximas no pueden ser menor o igual a 0")
            }else if  (producto.unidadesMaximas.toString().length == 0) {
                throw BusinessException("Las unidades maximas no pueden estar vacias")
            }else if (producto.unidadesMaximas.toString().length > 8) {
                throw BusinessException("Las unidades maximas no pueden ser mayor a 8 digitos")
            }else if (producto.unidadesMinimas < 0) {
                throw BusinessException("Las unidades minimas no pueden ser menor a 0")
            }else if (producto.unidadesMinimas.toString().length > 8) {
                throw BusinessException("Las unidades minimas no pueden ser mayor a 8 digitos")
            }else if  (producto.unidadesMinimas.toString().length == 0) {
                throw BusinessException("Las unidades minimas no pueden estar vacias")
            }else if (producto.unidadesMinimas > producto.unidadesMaximas) {
                throw BusinessException("Las unidades minimas no pueden ser mayor a las unidades maximas")
            }
            return productoRepository!!.save(producto)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveProductos(productos: List<Producto>): List<Producto> {
        try {
            return productoRepository!!.saveAll(productos)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun removeProducto(idProducto: Long) {
        val opt: Optional<Producto>
        try{
            if (idProducto < 0) {
                throw BusinessException("El id que ingreso no es valido")
            }
            opt = productoRepository!!.findById(idProducto)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el producto $idProducto")
        }
        else{
            try{
                productoRepository!!.deleteById(idProducto)
            }catch (e:Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getProductoByNombre(nombreProducto: String): Producto {
        val opt: Optional<Producto>
        try{
            if (nombreProducto.isEmpty()) {
                throw BusinessException("El nombre que ingreso no es valido")
            }
            opt = productoRepository!!.findByNombreProducto(nombreProducto)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el producto $nombreProducto")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateProducto(producto: Producto): Producto {
        val opt: Optional<Producto>
        try{
            if (producto.nombreProducto.length == 0) {
                throw BusinessException("El nombre no puede estar vacio")
            } else if (producto.descripcion.length == 0) {
                throw BusinessException("La descripcion no puede estar vacia")
            } else if (producto.precio <= 0.0) {
                throw BusinessException("El precio no puede ser menor o igual a 0.0")
            } else if (producto.precio.toString().length == 0) {
                throw BusinessException("El precio no puede estar vacio")
            }else if  (producto.unidadesEnAlmacen < 0) {
                throw BusinessException("Las unidades en almacen no pueden ser menor a 0")
            }else if  (producto.unidadesEnAlmacen.toString().length == 0) {
                throw BusinessException("Las unidades en almacen no pueden estar vacias")
            }else if  (producto.unidadesEnAlmacen > producto.unidadesMaximas) {
                throw BusinessException("Las unidades en almacen no pueden ser mayor a las unidades maximas")
            }else if  (producto.unidadesEnAlmacen > producto.unidadesMinimas) {
                throw BusinessException("Las unidades en almacen no pueden ser menor a las unidades minimas")
            }else if (producto.unidadesEnAlmacen.toString().length > 8) {
                throw BusinessException("Las unidades en almacen no pueden ser mayor a 8 digitos")
            }else if  (producto.unidadesMaximas <= 0) {
                throw BusinessException("Las unidades maximas no pueden ser menor o igual a 0")
            }else if  (producto.unidadesMaximas.toString().length == 0) {
                throw BusinessException("Las unidades maximas no pueden estar vacias")
            }else if (producto.unidadesMaximas.toString().length > 8) {
                throw BusinessException("Las unidades maximas no pueden ser mayor a 8 digitos")
            }else if (producto.unidadesMinimas < 0) {
                throw BusinessException("Las unidades minimas no pueden ser menor a 0")
            }else if (producto.unidadesMinimas.toString().length > 8) {
                throw BusinessException("Las unidades minimas no pueden ser mayor a 8 digitos")
            }else if  (producto.unidadesMinimas.toString().length == 0) {
                throw BusinessException("Las unidades minimas no pueden estar vacias")
            }else if (producto.unidadesMinimas > producto.unidadesMaximas) {
                throw BusinessException("Las unidades minimas no pueden ser mayor a las unidades maximas")
            }
            opt = productoRepository!!.findById(producto.productoId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el producto ${producto.productoId}")
        }
        else{
            try {
                return productoRepository!!.save(producto)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}