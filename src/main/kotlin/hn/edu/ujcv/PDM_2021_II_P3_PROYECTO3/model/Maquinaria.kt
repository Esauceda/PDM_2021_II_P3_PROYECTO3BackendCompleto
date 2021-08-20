package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*

@Entity
@Table(name = "maquinaria")
data class Maquinaria(val fabricaId:Long = 0, val marca:String = "", val horasUso:Double = 0.0, val tipoMaquina:String = ""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var maquinariaId:Long = 0
}