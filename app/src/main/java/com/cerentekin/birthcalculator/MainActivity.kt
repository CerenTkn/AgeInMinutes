package com.cerentekin.birthcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.cerentekin.birthcalculator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedDate: TextView? = null
    private var ageInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val datepicker : Button = findViewById(R.id.datepicker)
        selectedDate = findViewById(R.id.selectedDate)
        ageInMinutes = findViewById(R.id.ageInMinutes)
        datepicker.setOnClickListener {
            clickDatePicker()

        }
    }
    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_    , Selectedyear, Selectedmonth, Selecteddayofmonth ->
                Toast.makeText(this,"Year was $Selectedyear, month was ${Selectedmonth+1}, day of month was $Selecteddayofmonth", Toast.LENGTH_LONG).show()

                val SelectedDate = "$Selecteddayofmonth/${Selectedmonth+1}/$Selectedyear"
                selectedDate?.text = SelectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(SelectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        ageInMinutes?.text = differenceInMinutes.toString()
                    }
                }
                                              },
            year,
            month,
            day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }

    }
