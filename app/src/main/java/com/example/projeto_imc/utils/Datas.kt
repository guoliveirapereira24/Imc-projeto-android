package com.example.projeto_imc.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun convertStringToLocalDate(brazilDate: String) : LocalDate {

    val dateFormatForBrazil = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val localDateFormat = LocalDate.parse(brazilDate, dateFormatForBrazil)

    return localDateFormat

}