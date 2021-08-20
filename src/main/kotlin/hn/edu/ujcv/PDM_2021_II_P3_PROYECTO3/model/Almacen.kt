package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*

@Entity
@Table(name = "almacen")
data class Almacen(val telefono:Int = 0, val direccion:String = "", val encargado:String = ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var almacenId:Long = 0
}
