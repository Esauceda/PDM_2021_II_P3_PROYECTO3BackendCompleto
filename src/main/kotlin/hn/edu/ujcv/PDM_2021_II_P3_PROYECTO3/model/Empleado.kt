package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*

@Entity
@Table(name = "empleado")
data class Empleado(val nombre:String = "", val direccion:String = "", val telefono:Int = 0, val salario:Double = 0.0,
val puesto:String = "", val fechaNacimiento:String = "", val fechaContratacion:String = "", val contrasena:String = ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var empleadoId:Long = 0
}
