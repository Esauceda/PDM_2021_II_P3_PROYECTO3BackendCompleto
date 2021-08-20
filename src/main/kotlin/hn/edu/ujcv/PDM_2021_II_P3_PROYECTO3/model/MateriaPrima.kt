package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*


@Entity
@Table(name = "materiaprima")
data class MateriaPrima(val nombreMateria:String = "", val proveedorId:Long = 0,
                        val almacenId:Long = 0, val descripcion: String ="",val cantidad:Int = 0 ){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var materiaprimaId:Long = 0
}