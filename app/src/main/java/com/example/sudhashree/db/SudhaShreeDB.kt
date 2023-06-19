package com.example.sudhashree.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Mobile::class],version  =1)
 abstract class SudhaShreeDB :RoomDatabase(){

    abstract fun mobiledao() : MobileDAO

    companion object{
        @Volatile
        private  var INSTANCE : SudhaShreeDB? =null

        fun getDBInstance(context :Context) : SudhaShreeDB {

            if(INSTANCE ==null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    SudhaShreeDB::class.java,
                    "ssDB").build()

                }

            }
            return INSTANCE!!
        }


    }

}