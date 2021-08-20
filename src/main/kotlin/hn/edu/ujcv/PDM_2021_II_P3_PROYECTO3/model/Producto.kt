package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*

@Entity
@Table(name = "productos")
data class Producto(val fabricaId:Int = 0, val nombreProducto:String = "", val descripcion:String = "", val precio:Double = 0.0,
val unidadesEnAlmacen:Int = 0, val unidadesMaximas:Int = 0, val unidadesMinimas:Int = 0){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var productoId:Long = 0
}
