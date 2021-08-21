package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "orden_encabezado")
data class OrdenEncabezado(val empleadoId:Long = 0, val clienteId:Long = 0, val fechaOrden:String = "",
                           val fechaEnvio:String = "", val direccionEnvio:String = "", val estado:String = "",
                           val total:Double = 0.0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var ordenId: Long = 0
 }
