package com.example.calculadora

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), InputFragment.OnCalculateListener {

    private lateinit var resultFragment: ResultFragment

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

        val inputFragment = InputFragment()
        inputFragment.setOnCalculateListener(this)

        resultFragment = ResultFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_input, inputFragment)
            .replace(R.id.fragment_container_result, resultFragment)
            .commit()
    }

    override fun onCalculate(principal: Double, rate: Double, time: Double) {
        resultFragment.updateResult(principal, rate, time)
    }

    override fun onCalculateError(message: String) {
        resultFragment.showError(message)
    }
}






