package com.example.eva1progra2.models

class ItemMesa(
    val itemMenu: ItemMenu,
    var cantidad: Int
) {
    fun calcularSubtotal(): Int {
        return (itemMenu.precio.toDouble() * cantidad).toInt()
    }
} 