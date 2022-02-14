package com.example.intentexamplereturndata

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private val TAG = "SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val age = intent.getIntExtra("age", 0)

        Log.d(TAG, "firstName: $firstName ")
        Log.d(TAG, "lastName: $lastName")
        Log.d(TAG, "age: $age")

        // Do something with the data
        first_name_text.text = firstName
        last_name_text.text = lastName
        age_text.text = age.toString()
    }

    fun returnDataToFirstActivity(view: View) {

        // Need to create an intent to go back
        val myIntent = Intent()

        // Store any extra data in the intent
        myIntent.putExtra("status", status_text.text.toString())
        myIntent.putExtra("phone", phone_text.text.toString().toIntOrNull())

        // Set the activity's result to RESULT_OK
        setResult(Activity.RESULT_OK, myIntent)
        // Stops and closes the second activity
        finish()

    }
}