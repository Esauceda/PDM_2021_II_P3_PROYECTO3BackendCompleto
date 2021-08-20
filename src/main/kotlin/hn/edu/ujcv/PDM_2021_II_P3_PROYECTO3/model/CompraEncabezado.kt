package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model

import javax.persistence.*

@Entity
@Table(name = "compra_encabezado")
data class CompraEncabezado(val proveedorId:Long = 0, val empleadoId:Long = 0, val fechaCompra:String = "", val total:Double = 0.0,
                            val estado:String = "", val fechaRecepcion: String = ""){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var compraId:Long = 0
}