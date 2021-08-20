package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Delivery

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Delivery

interface IDeliveryBusiness {
    fun getDeliverys():List<Delivery>
    fun getDeliveryById(idDelivery: Long): Delivery
    fun saveDelivery(delivery: Delivery): Delivery
    fun saveDeliverys(delivery: List<Delivery>):List<Delivery>
    fun removeDelivery(idDelivery: Long)
    fun getDeliveryByNombre(nombreDelivery: String): Delivery
    fun updateDelivery(delivery: Delivery): Delivery
}