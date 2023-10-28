package com.example.newmusicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newmusicapp.databinding.ActivityEmployeeDetailsBinding

class EmployeeDetails : AppCompatActivity() {

    var binding:ActivityEmployeeDetailsBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding?.toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }

        // creating an employee list
        // of type Employee class
        var emplist:MusicList?=null

        // checking if the intent has extra
        if(intent.hasExtra(MainActivity.NEXT_SCREEN)){
            // get the Serializable data model class with the details in it
            emplist =
                intent.getSerializableExtra(MainActivity.NEXT_SCREEN) as MusicList
        }
        // it the emplist is not null the it has some data and display it
        if(emplist!=null){
            binding?.displayName?.text=emplist!!.name   // displaying name
            binding?.displayEmail?.text=emplist!!.type
            binding?.displayEmail?.text=emplist!!.time
        // displaying email
        }

    }
}