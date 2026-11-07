package com.example.test1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val etWeight = findViewById<EditText>(R.id.etWeight)
        val rgClass = findViewById<RadioGroup>(R.id.rgClass)
        val rbBusiness = findViewById<RadioButton>(R.id.rbBusiness)
        val etRate = findViewById<EditText>(R.id.etRate)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val tvExplanation = findViewById<TextView>(R.id.tvExplanation)
        val btnAbout = findViewById<Button>(R.id.btnAbout)

        // About Page
        btnAbout.setOnClickListener {
            val intent = android.content.Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        btnCalculate.setOnClickListener {
            val weightStr = etWeight.text.toString().trim()

            // ERROR VALIDATION
            if (weightStr.isEmpty() || weightStr.toDoubleOrNull() == null) {
                Toast.makeText(this, "⚠️ Error: Please enter a valid baggage weight first!", Toast.LENGTH_LONG).show()
                tvResult.text = ""
                tvExplanation.text = ""
                return@setOnClickListener
            }

            val weight = weightStr.toDouble()

            // FREE ALLOWANCE (Economy = 20kg, Business = 40kg)
            val isBusiness = rgClass.checkedRadioButtonId == rbBusiness.id
            val freeLimit = if (isBusiness) 40.0 else 20.0
            val className = if (isBusiness) "Business Class" else "Economy Class"

            // GET RATE PER KG (Default RM15 if empty)
            val rate = etRate.text.toString().toDoubleOrNull() ?: 15.0

            // CALCULATION
            if (weight <= freeLimit) {
                tvResult.text = "Status: Within Free Allowance\nTotal Amount Payable: RM 0.00"

                tvExplanation.text = "💡 Formula Info ($className):\n" +
                        "• Free Allowance: %.0f kg\n".format(freeLimit) +
                        "• Baggage Weight: %.1f kg (<= %.0f kg)\n".format(weight, freeLimit) +
                        "• Excess Weight: 0 kg\n" +
                        "• Total Payable: RM 0.00 (No charges applied)"
            } else {
                val excessWeight = weight - freeLimit
                val excessCharge = excessWeight * rate

                // ADDING 8% SERVICE TAX
                val serviceTax = excessCharge * 0.08
                val totalPayable = excessCharge + serviceTax

                tvResult.text = "Excess Weight: %.1f kg\nExcess Charge: RM %.2f\nTotal Amount Payable: RM %.2f".format(excessWeight, excessCharge, totalPayable)

                // CALCULATION
                tvExplanation.text = "💡 Formula & Breakdown ($className):\n" +
                        "1. Excess Weight: %.1f - %.0f = %.1f kg\n".format(weight, freeLimit, excessWeight) +
                        "2. Excess Charge: %.1f kg × RM %.2f = RM %.2f\n".format(excessWeight, rate, excessCharge) +
                        "3. Total with 8%% Tax: RM %.2f + (RM %.2f × 0.08) = RM %.2f".format(excessCharge, excessCharge, totalPayable)
            }
        }
    }
}