package com.example.calculadora

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.NumberFormat
import java.util.Locale

class ResultFragment : Fragment() {

    private lateinit var tvResult: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.result_fragment, container, false)
        tvResult = view.findViewById(R.id.Tvresult)
        return view
    }

    fun updateResult(principal: Double, rate: Double, time: Double) {
        val interest = (principal * rate * time) / 100
        val totalAmount = principal + interest

        val result = "Juros: ${NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(interest)}\n" +
                "Total: ${NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(totalAmount)}"
        tvResult.text = result
    }

    fun showError(message: String) {
        tvResult.text = message
    }
}

