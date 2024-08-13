package com.example.calculadora

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

class InputFragment : Fragment() {

    interface OnCalculateListener {
        fun onCalculate(principal: Double, rate: Double, time: Double)
        abstract fun onCalculateError(s: String)
    }

    private var listener: OnCalculateListener? = null

    fun setOnCalculateListener(listener: OnCalculateListener) {
        this.listener = listener
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.input_fragment, container, false)

        val edtPrincipal = view.findViewById<EditText>(R.id.edtPrincipal)
        val edtRate = view.findViewById<EditText>(R.id.edtRate)
        val edtTempo = view.findViewById<EditText>(R.id.edtTempo)
        val btnCalc = view.findViewById<Button>(R.id.btnCalc)

        btnCalc.setOnClickListener {
            val principalStr = edtPrincipal.text.toString()
            val rateStr = edtRate.text.toString()
            val timeStr = edtTempo.text.toString()

            Log.d("CalculationInput", "Principal: $principalStr, Rate: $rateStr, Time: $timeStr")

            if (principalStr.isNotEmpty() && rateStr.isNotEmpty() && timeStr.isNotEmpty()) {
                try {
                    val principal = principalStr.toDouble()
                    val rate = rateStr.toDouble()
                    val time = timeStr.toDouble()

                    Log.d("CalculationValues", "Principal: $principal, Rate: $rate, Time: $time")

                    listener?.onCalculate(principal, rate, time)
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                    Log.e("CalculationError", "Erro ao calcular os valores: ${e.message}")
                    listener?.onCalculateError("Erro ao calcular. Verifique os valores inseridos.")
                }
            } else {
                listener?.onCalculateError("Por favor, insira todos os valores.")
            }
        }

        return view
    }
}
