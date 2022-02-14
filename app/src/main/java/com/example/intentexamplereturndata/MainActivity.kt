package com.example.intentexamplereturndata

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    // REQUEST_CODE can be any value you like, used for identifier
    private val REQUEST_CODE = 123 // any number that MUST BE in the range of 0 - 65535

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openSecondActivity(view: View){
        // Launch the Second Activity for a result

        // prepare the data to be sent to the second activity
        val firstName = firstname_text.text.toString()
        val lastName = lastname_text.text.toString()
        val age = age_text.text.toString().toIntOrNull()

        // Check to make sure the fields are not empty
        if (firstName.isEmpty() or lastName.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            return
        }
        if (age == null){
            Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show()
            return
        }

        // For testing purpose, print the values in the logcat
        Log.d(TAG, "firstName: $firstName")
        Log.d(TAG, "lastName: $lastName")
        Log.d(TAG, "age: $age")


        //Create an Intent object with two parameters: 1) context, 2) class of the activity to launch
        val myIntent = Intent(this, SecondActivity::class.java)

        // put "extras" into the intent for access in the second activity
        myIntent.putExtra("firstName", firstName)
        myIntent.putExtra("lastName", lastName)
        myIntent.putExtra("age", age)

        // Start the new Activity with startActivityForResult instead of startActivity
        // you must pass a requestCode unique identifier (see top part of the class)
        startActivityForResult(myIntent, REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            // come back from the second activity

            // extract the data from the intent
            val status = data?.getStringExtra("status")
            val phone = data?.getIntExtra("phone", 0)

            Log.d(TAG, "status: $status")
            Log.d(TAG, "phone: $phone")

            // Do something with the data
            tv_status.text = status.toString()
            tv_phone.text = phone.toString()

        }
    }
}