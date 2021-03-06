package com.example.android.marsrealestate.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.marsrealestate.models.Deal

@Database(entities = [Deal::class], version = 1, exportSchema = false)
abstract class DealDatabase : RoomDatabase() {

    abstract val dealDatabaseDao: DealDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: DealDatabase? = null

        fun getInstance(context: Context): DealDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            DealDatabase::class.java,
                            "deal_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}


