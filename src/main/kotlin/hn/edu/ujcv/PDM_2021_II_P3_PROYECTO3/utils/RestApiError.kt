package hn.edu.ujcv.PDM_2021_II_P3_PROYECTO3

import org.springframework.http.HttpStatus

class RestApiError(val httpStatus: HttpStatus, val errorMessage:String, val errorDetails:String) {

}