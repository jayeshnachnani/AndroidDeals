package com.example.android.marsrealestate.database




import androidx.lifecycle.LiveData
import androidx.room.*
//import com.udacity.asteroidradar.api.nextsevendays
import com.example.android.marsrealestate.models.Deal

@Dao
interface DealDatabaseDao {

    /*val today: String
        get() = nextsevendays.first()
    val lastdayofweek: String
        get() = nextsevendays.last()*/

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insert(deal: Deal)

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(deals: MutableList<Deal>)

    @Update
    fun update(deal: Deal)

    @Query("SELECT * FROM FIXED_INCOME_DEALS")// ORDER BY FIXED_INCOME_DEALS..close_approach_date DESC" )
    fun getAllDeals(): LiveData<List<Deal>>

    /*@Query("SELECT * FROM asteroid_details where asteroid_details.close_approach_date >= date('now') and asteroid_details.close_approach_date <= date('now') ORDER BY asteroid_details.close_approach_date DESC" )
    fun getTodaysAsteroids(): LiveData<List<Asteroid>>

    //@Query("SELECT * FROM asteroid_details where asteroid_details.close_approach_date >= date('now') and asteroid_details.close_approach_date <= date('now','+7days') ORDER BY asteroid_details.close_approach_date DESC LIMIT 6" )
    @Query("SELECT * FROM asteroid_details where asteroid_details.close_approach_date between datetime ('now') and datetime ('now', '+7 days') ORDER BY asteroid_details.close_approach_date DESC" )
    fun getWeeklyAsteroids(): LiveData<List<Asteroid>>*/

}