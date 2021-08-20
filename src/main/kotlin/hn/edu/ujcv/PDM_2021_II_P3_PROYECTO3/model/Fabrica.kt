package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*

@Entity
@Table(name = "fabricas")
data class Fabrica(val nombreFabrica:String = "", val telefono:Long = 0, val tipoProduccion:String = "",
                   val direccion:String = "", val encargado:String = ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var fabricaId:Long = 0
}