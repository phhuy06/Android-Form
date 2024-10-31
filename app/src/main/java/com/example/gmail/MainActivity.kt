package com.example.gmail

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mssvInput: EditText
    private lateinit var fullNameInput: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var emailInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var birthDateCalendar: CalendarView
    private lateinit var showDatePickerButton: Button
    private lateinit var provinceSpinner: Spinner
    private lateinit var districtSpinner: Spinner
    private lateinit var wardSpinner: Spinner
    private lateinit var sportsCheckBox: CheckBox
    private lateinit var musicCheckBox: CheckBox
    private lateinit var moviesCheckBox: CheckBox
    private lateinit var agreeCheckBox: CheckBox
    private lateinit var submitButton: Button

    private lateinit var addressHelper: AddressHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addressHelper = AddressHelper(resources)

        mssvInput = findViewById(R.id.MSSVInput)
        fullNameInput = findViewById(R.id.FullNameInput)
        genderRadioGroup = findViewById(R.id.GenderRadioGroup)
        emailInput = findViewById(R.id.EmailInput)
        phoneInput = findViewById(R.id.PhoneInput)
        birthDateCalendar = findViewById(R.id.BirthDateCalendar)
        showDatePickerButton = findViewById(R.id.ShowDatePickerButton)
        provinceSpinner = findViewById(R.id.ProvinceSpinner)
        districtSpinner = findViewById(R.id.DistrictSpinner)
        wardSpinner = findViewById(R.id.WardSpinner)
        sportsCheckBox = findViewById(R.id.SportsCheckBox)
        musicCheckBox = findViewById(R.id.MusicCheckBox)
        moviesCheckBox = findViewById(R.id.MoviesCheckBox)
        agreeCheckBox = findViewById(R.id.AgreeCheckBox)
        submitButton = findViewById(R.id.SubmitButton)

        val provinces = addressHelper.getProvinces()
        val provinceAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provinces)
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        provinceSpinner.adapter = provinceAdapter

        provinceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedProvince = provinces[position]
                val districts = addressHelper.getDistricts(selectedProvince)
                val districtAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, districts)
                districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                districtSpinner.adapter = districtAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        districtSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedProvince = provinceSpinner.selectedItem.toString()
                val selectedDistrict = districtSpinner.selectedItem.toString()
                val wards = addressHelper.getWards(selectedProvince, selectedDistrict)
                val wardAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, wards)
                wardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                wardSpinner.adapter = wardAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        showDatePickerButton.setOnClickListener {
            birthDateCalendar.visibility = if (birthDateCalendar.visibility != View.VISIBLE) View.VISIBLE else View.GONE
        }

        submitButton.setOnClickListener {
            if (mssvInput.text.isEmpty() || fullNameInput.text.isEmpty() ||
                emailInput.text.isEmpty() || phoneInput.text.isEmpty() ||
                genderRadioGroup.checkedRadioButtonId == -1 ||
                birthDateCalendar.date == 0L ||
                !agreeCheckBox.isChecked) {

                Toast.makeText(this, "Please fill in all required fields and agree to the terms.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_LONG).show()
            }
        }
    }
}