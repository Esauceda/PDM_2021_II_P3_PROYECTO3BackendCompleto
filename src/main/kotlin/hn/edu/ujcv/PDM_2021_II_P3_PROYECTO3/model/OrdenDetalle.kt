package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*

@Entity
@Table(name = "orden_detalle")
data class OrdenDetalle(val ordenId:Long = 0 ,val almacenId:Long = 0, val productoId:Long = 0, val cantidad: Int = 0, val precio:Double = 0.0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val ordenDetalleId: Long = 0
}