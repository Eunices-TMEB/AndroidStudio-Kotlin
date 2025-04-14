package com.example.eva1progra2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eva1progra2.models.CuentaMesa
import com.example.eva1progra2.models.ItemMenu
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var cuentaMesa: CuentaMesa
    private val formatoMoneda = NumberFormat.getCurrencyInstance(Locale("es", "CL"))

    
    private lateinit var pastelChocloCantidad: EditText
    private lateinit var cazuelaCantidad: EditText
    private lateinit var arrozConLecheCantidad: EditText
    private lateinit var pastelChocloSubtotal: TextView
    private lateinit var cazuelaSubtotal: TextView
    private lateinit var arrozConLecheSubtotal: TextView
    private lateinit var totalSinPropina: TextView
    private lateinit var propinaMonto: TextView
    private lateinit var totalConPropina: TextView
    private lateinit var propinaSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cuentaMesa = CuentaMesa(1) // Mesa número 1

        initializeViews()

        setupListeners()
    }

    private fun initializeViews() {
        pastelChocloCantidad = findViewById(R.id.pastelChocloCantidad)
        cazuelaCantidad = findViewById(R.id.cazuelaCantidad)
        arrozConLecheCantidad = findViewById(R.id.arrozConLecheCantidad)
        pastelChocloSubtotal = findViewById(R.id.pastelChocloSubtotal)
        cazuelaSubtotal = findViewById(R.id.cazuelaSubtotal)
        arrozConLecheSubtotal = findViewById(R.id.arrozConLecheSubtotal)
        totalSinPropina = findViewById(R.id.totalSinPropina)
        propinaMonto = findViewById(R.id.propinaMonto)
        totalConPropina = findViewById(R.id.totalConPropina)
        propinaSwitch = findViewById(R.id.propinaSwitch)
    }

    private fun setupListeners() {
        pastelChocloCantidad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                cuentaMesa.agregarItem(ItemMenu("Pastel de Choclo", "12000"), cantidad)
                actualizarTotales()
            }
        })

        cazuelaCantidad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                cuentaMesa.agregarItem(ItemMenu("Cazuela", "10000"), cantidad)
                actualizarTotales()
            }
        })

        arrozConLecheCantidad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                cuentaMesa.agregarItem(ItemMenu("Arroz con Leche", "4500"), cantidad)
                actualizarTotales()
            }
        })

        propinaSwitch.setOnCheckedChangeListener { _, isChecked ->
            cuentaMesa.aceptaPropina = isChecked
            actualizarTotales()
        }
    }

    private fun actualizarTotales() {
        cuentaMesa.getItems().forEach { itemMesa ->
            when (itemMesa.itemMenu.nombre) {
                "Pastel de Choclo" -> pastelChocloSubtotal.text = formatoMoneda.format(itemMesa.calcularSubtotal())
                "Cazuela" -> cazuelaSubtotal.text = formatoMoneda.format(itemMesa.calcularSubtotal())
                "Arroz con Leche" -> arrozConLecheSubtotal.text = formatoMoneda.format(itemMesa.calcularSubtotal())
            }
        }

        val totalSinProp = cuentaMesa.calcularTotalSinPropina()
        totalSinPropina.text = formatoMoneda.format(totalSinProp)
        propinaMonto.text = formatoMoneda.format(cuentaMesa.calcularPropina())
        totalConPropina.text = formatoMoneda.format(cuentaMesa.calcularTotalConPropina())
    }
}