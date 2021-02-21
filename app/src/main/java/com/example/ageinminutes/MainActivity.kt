package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.widget.Toast
import com.example.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnSelectDate.setOnClickListener { clickDatePicker() }
    }

    private fun clickDatePicker() {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val dayOfMonth = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            this,
            { _, sYear, sMonth, sDayOfMonth ->
                val selectedDate = "$sDayOfMonth/${sMonth + 1}/$sYear"
                binding.tvSelectedDate.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val todayDate = sdf.parse(sdf.format(Date()))
                val minutes = floor(((todayDate.time - theDate.time) / 60000).toDouble()).toInt()
                binding.tvSelectedDateInMinutes.text = minutes.toString()
//                Toast.makeText(this, "$minutes", Toast.LENGTH_LONG).show()
            },
            year, month, dayOfMonth
        ).show()
    }
}