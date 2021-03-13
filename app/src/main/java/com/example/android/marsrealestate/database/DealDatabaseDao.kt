package com.example.android.marsrealestate.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.marsrealestate.models.Deal

@Dao
interface DealDatabaseDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insert(deal: Deal)

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(deals: MutableList<Deal>)

    @Update
    fun update(deal: Deal)

    @Query("SELECT * FROM FIXED_INCOME_DEALS")// ORDER BY FIXED_INCOME_DEALS..close_approach_date DESC" )
    fun getAllDeals(): LiveData<List<Deal>>

}