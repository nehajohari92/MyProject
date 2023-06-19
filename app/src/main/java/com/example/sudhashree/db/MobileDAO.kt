package com.example.sudhashree.db

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*


@Dao
interface MobileDAO {

    @Insert
     suspend fun insertMobile (mobile : ArrayList<Mobile>)

    @Update
    suspend fun updateMobile(mobile: Mobile)

    @Delete
    suspend fun deleteMobile(mobile: Mobile)

    @Query("select * from MOBdata")
    fun getMobileList() : LiveData<List<Mobile>>

    @Query("UPDATE MOBdata SET mobilePrice=:price , quantityBlack= :quant WHERE id = :id")
    fun updateBlack(price: String?, id: Int, quant : String?)

    @Query("UPDATE MOBdata SET mobilePrice=:price , quantityWhite= :quant WHERE id = :id")
    fun updateWhite(price: String?, id: Int, quant : String?)

    @Query("UPDATE MOBdata SET mobilePrice=:price WHERE id = :id")
    fun updatePrice(price: String?, id: Int)

    @Query("SELECT * FROM MOBdata WHERE mobileModel LIKE '%' || :query || '%'")
    fun searchByName(query: String): List<Mobile>
}