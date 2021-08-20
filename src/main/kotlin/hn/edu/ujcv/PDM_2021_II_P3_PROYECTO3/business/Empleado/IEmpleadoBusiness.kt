package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.business.Empleado

import hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3.model.Empleado

interface IEmpleadoBusiness {
    fun getEmpleados():List<Empleado>
    fun getEmpleadoById(idEmpleado: Long):Empleado
    fun saveEmpleado(empleado: Empleado): Empleado
    fun saveEmpleados(empleado: List<Empleado>):List<Empleado>
    fun removeEmpleado(idEmpleado: Long)
    fun getEmpleadoByNombre(nombreEmpleado: String): Empleado
    fun updateEmpleado(empleado: Empleado): Empleado
}