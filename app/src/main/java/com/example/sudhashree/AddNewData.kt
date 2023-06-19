package com.example.sudhashree

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sudhashree.db.Mobile
import com.example.sudhashree.db.SudhaShreeDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddNewData : AppCompatActivity() {

    lateinit var database: SudhaShreeDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_data)

        val ed_price = findViewById<EditText>(R.id.ed_newprice)
        val ed_model = findViewById<EditText>(R.id.ed_newmobile)
        val ed_Quantity = findViewById<EditText>(R.id.ed_newQuantity)
        val ed_newwhiteQuantity= findViewById<EditText>(R.id.ed_newwhiteQuantity)
        val add_button = findViewById<Button>(R.id.add_button)

        database = SudhaShreeDB.getDBInstance(this)
        add_button.setOnClickListener(View.OnClickListener {

            val price = ed_price.text.toString()
            val Model= ed_model.text.toString()
            val blackQuantity = ed_Quantity.text.toString()
            val whiteQuantity = ed_newwhiteQuantity.text.toString()

            if(price!="" && Model != "" && blackQuantity != "" && whiteQuantity!="" ) {
                val modelObject = ArrayList<Mobile>()
                modelObject.add(
                    Mobile(
                        mobileModel = ed_model.text.toString(),
                        mobilePrice = ed_price.text.toString(),
                        quantityWhite = ed_newwhiteQuantity.text.toString(),
                        quantityBlack = ed_Quantity.text.toString()
                    )
                )
                GlobalScope.launch {
                    try {
                        database.mobiledao().insertMobile(modelObject)
                        finish()
                    } catch (exception: SQLiteConstraintException) {
                    }
                }
            }else{
                Toast.makeText(this,"Provide all details",Toast.LENGTH_LONG).show()
            }
        })
    }
}