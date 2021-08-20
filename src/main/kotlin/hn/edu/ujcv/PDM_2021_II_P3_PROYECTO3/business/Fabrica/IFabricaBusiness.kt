package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Fabrica

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Fabrica

interface IFabricaBusiness {
    fun getFabricas():List<Fabrica>
    fun getFabricaById(idFabrica: Long):Fabrica
    fun saveFabrica(fabrica: Fabrica): Fabrica
    fun saveFabricas(fabricas:List<Fabrica>):List<Fabrica>
    fun removeFabrica(idFabrica: Long)
    fun getFabricaByNombre(nombreFabrica: String): Fabrica
    fun updateFabrica(fabrica: Fabrica): Fabrica
}