package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model
import javax.persistence.*

@Entity
@Table(name = "compra_detalle")
data class CompraDetalle(val compraId:Long = 0, val producto:String = "", val cantidad:Int = 0, val precio: Double = 0.0){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var compraDetalleId:Long = 0
}
