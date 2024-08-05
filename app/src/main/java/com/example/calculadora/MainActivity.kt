package com.example.calculadora

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtPrincipal = findViewById<EditText>(R.id.edtPrincipal)
        val edtJuro = findViewById<EditText>(R.id.edtRate)
        val edtTempo = findViewById<EditText>(R.id.edtTempo)
        val btnCalc = findViewById<Button>(R.id.btnCalc)
        val tvResult = findViewById<TextView>(R.id.Tvresult)

        btnCalc.setOnClickListener {
            val principalStr = edtPrincipal.text.toString()
            val rateStr = edtJuro.text.toString()
            val timeStr = edtTempo.text.toString()

            Log.d("CalculationInput", "Principal: $principalStr, Rate: $rateStr, Time: $timeStr")

            if (principalStr.isNotEmpty() && rateStr.isNotEmpty() && timeStr.isNotEmpty()) {
                try {
                    val principal = principalStr.toDouble()
                    val rate = rateStr.toDouble()
                    val time = timeStr.toDouble()

                    Log.d("CalculationValues", "Principal: $principal, Rate: $rate, Time: $time")

                    val interest = (principal * rate * time) / 100
                    val totalAmount = principal + interest

                    val result = "Juros: ${NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(interest)}\n" +
                            "Total: ${NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(totalAmount)}"
                    tvResult.text = result
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                    Log.e("CalculationError", "Erro ao calcular os valores: ${e.message}")
                    tvResult.text = "Erro ao calcular. Verifique os valores inseridos."
                }
            } else {
                tvResult.text = "Por favor, insira todos os valores."
            }
        }
    }
}





