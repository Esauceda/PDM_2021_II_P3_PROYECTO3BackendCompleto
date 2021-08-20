package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*

@Entity
@Table(name = "clientes")
data class Cliente(val nombreCompania:String = "", val nombre:String = "", val telefono:Int = 0, val correo:String = ""
, val pais:String = "", val direccion:String = "", val categoria:String = ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var clienteId:Long = 0
}
