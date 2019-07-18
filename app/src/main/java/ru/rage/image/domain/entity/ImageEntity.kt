package ru.rage.image.domain.entity

import java.io.File
import java.util.*

interface ImageEntity{
    enum class Type{
        LOCAL, TEMPORARY
    }

    val id : Long
    val type: Type
    val date: Date
    val image: File
}