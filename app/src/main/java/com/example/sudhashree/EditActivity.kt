package com.example.sudhashree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.sudhashree.db.Mobile
import com.example.sudhashree.db.SudhaShreeDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

class EditActivity : AppCompatActivity() {

    lateinit var database: SudhaShreeDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        database= SudhaShreeDB.getDBInstance(this)
        val modelNmae=intent.getStringExtra("Modelname")
        val modelPrice=intent.getStringExtra("Modelprice")
        val modelID= intent.getIntExtra("ModelID",0)
        val Quantity = intent.getStringExtra("Quantity")

       /* val ed_price= findViewById<EditText>(R.id.ed_price)
        val ed_model= findViewById<EditText>(R.id.ed_mobile)
        val ed_Quantity= findViewById<EditText>(R.id.ed_Quantity)
        val confirm_button= findViewById<Button>(R.id.confirm_button)
        ed_price.setText(modelPrice)
        ed_model.setText(modelNmae)
        ed_Quantity.setText(Quantity)*/

        /*confirm_button.setOnClickListener(View.OnClickListener {
            GlobalScope.launch {
                Log.d("test", modelID.toString())
                database.mobiledao().update(ed_price.text.toString(),modelID,ed_Quantity.text.toString())
                finish()
            }
        })*/



    }
}