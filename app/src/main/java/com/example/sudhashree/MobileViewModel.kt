package com.example.sudhashree

import android.app.Application
import androidx.lifecycle.*
import com.example.sudhashree.db.Mobile
import com.example.sudhashree.db.SudhaShreeDB

class MobileViewModel(app: Application) : AndroidViewModel(app) {

    lateinit var  datalist : LiveData<List<Mobile>>


    fun getAllData() : LiveData<List<Mobile>>{

        val data= SudhaShreeDB.getDBInstance((getApplication())).mobiledao()
        datalist = data.getMobileList()


        return datalist

    }

}