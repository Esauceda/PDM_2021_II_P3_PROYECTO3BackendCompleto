package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*

@Entity
@Table(name = "proveedores")
data class Proveedor(val nombreCompania:String = "", val nombreContacto:String = "", val numero:Int = 0, val correo:String = "",
val pais:String = "", val direccion:String = ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var proveedorId:Long = 0
}
