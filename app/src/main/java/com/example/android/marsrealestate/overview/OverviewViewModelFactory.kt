package com.example.android.marsrealestate.overview



import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.marsrealestate.database.DealDatabaseDao


class OverviewViewModelFactory(
        private val dataSource: DealDatabaseDao,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            return OverviewViewModel(dataSource,application) as T //dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
