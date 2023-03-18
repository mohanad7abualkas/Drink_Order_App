package com.example.drinkapp

import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    var Tag = "MainActivity"

    lateinit var textInputLayoutt: AutoCompleteTextView
    lateinit var Btn: Button
    lateinit var Price: TextView



    private val drink1 = "Orange Juice"
    private val drink2 = "Apple Juice"
    private val drink3 = "mango Juice"
    private val drink4 = "Kewi Juice"

    val prices = mapOf(
        drink1 to 15,
        drink2 to 20,
        drink3 to 25,
        drink4 to 30
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        intialization()
        menu()


        textInputLayoutt.setOnItemClickListener { adapterView, view, i, l ->
            val drinks = prices[textInputLayoutt.text.toString()]
            Price.setText(drinks.toString())
        }

        Btn.setOnClickListener{
            val sharedata = Intent(Intent.ACTION_SENDTO)
            val finalResalt = "I Want To Order: ${textInputLayoutt.text.toString()}" +
                    "\n Total Price is: ${Price.text.toString()}"

            sharedata.data = Uri.parse("mailto: ")

            sharedata.type = "text/plain"
            sharedata.putExtra(Intent.EXTRA_EMAIL, arrayOf("mohanadabualkas@gmail.com"))
            sharedata.putExtra(Intent.EXTRA_SUBJECT,"Order")
            sharedata.putExtra(Intent.EXTRA_TEXT,finalResalt)
            startActivity(sharedata)


        }
    }






    private fun menu (){
        val listDrinks = listOf(drink1, drink2, drink3, drink4)
        val adapterforlist = ArrayAdapter(this, R.layout.menu_of_drinks, listDrinks)
        textInputLayoutt.setAdapter(adapterforlist)
    }

    private fun intialization(){
        textInputLayoutt = findViewById(R.id.autoCompleteTextView)
        Btn = findViewById(R.id.button)
        Price = findViewById(R.id.prc)
    }
}