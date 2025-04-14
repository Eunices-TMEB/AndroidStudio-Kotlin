package com.example.eva1progra2.models

class ItemMenu(
    val nombre: String,
    val precio: String
) {
    constructor(nombre: String, precioDouble: Double)
            : this(nombre, precioDouble.toString())
} 