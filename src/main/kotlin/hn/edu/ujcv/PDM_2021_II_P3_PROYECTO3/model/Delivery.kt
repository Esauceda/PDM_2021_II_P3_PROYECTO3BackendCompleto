package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*

@Entity
@Table(name = "delivery")
data class Delivery(val ordenId:Int = 0, val nombreCompania:String = "", val numero:Int = 0, val correo:String = "",
val fechaEntrega:String = ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var deliveryId:Long = 0
}
