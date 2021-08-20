package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*

@Entity
@Table(name = "factura")
data class Factura(val ordenId:Long = 0, val fechaFactura:String = "", val total:Double = 0.0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val facturaId: Long = 0
}
