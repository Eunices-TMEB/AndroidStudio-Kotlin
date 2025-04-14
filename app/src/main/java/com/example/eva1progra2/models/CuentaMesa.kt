package com.example.eva1progra2.models

class CuentaMesa(private val mesa: Int) {
    private val items: MutableList<ItemMesa> = mutableListOf()
    var aceptaPropina: Boolean = false

    init {
        
        val menuItems = listOf(
            ItemMenu("Pastel de Choclo", "12000"),
            ItemMenu("Cazuela", "10000"),
            ItemMenu("Arroz con Leche", "4500")
        )
        
        menuItems.forEach { 
            agregarItem(it, 0)
        }
    }

    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        val existingItem = items.find { it.itemMenu.nombre == itemMenu.nombre }
        if (existingItem != null) {
            existingItem.cantidad = cantidad
        } else {
            items.add(ItemMesa(itemMenu, cantidad))
        }
    }

    fun agregarItem(itemMesa: ItemMesa) {
        val existingItem = items.find { it.itemMenu.nombre == itemMesa.itemMenu.nombre }
        if (existingItem != null) {
            existingItem.cantidad = itemMesa.cantidad
        } else {
            items.add(itemMesa)
        }
    }

    fun getItems(): List<ItemMesa> = items

    fun calcularTotalSinPropina(): Int {
        return items.sumOf { it.calcularSubtotal() }
    }

    fun calcularPropina(): Int {
        return if (aceptaPropina) {
            (calcularTotalSinPropina() * 0.10).toInt()
        } else {
            0
        }
    }

    fun calcularTotalConPropina(): Int {
        return calcularTotalSinPropina() + calcularPropina()
    }
} 