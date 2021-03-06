/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import android.app.Application
import androidx.lifecycle.*
import com.example.android.marsrealestate.database.DealDatabase
import com.example.android.marsrealestate.database.DealDatabaseDao
import com.example.android.marsrealestate.network.MarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.example.android.marsrealestate.models.Deal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */

class OverviewViewModel (
        val database: DealDatabaseDao,
        application: Application) : AndroidViewModel(application) {


    // The internal MutableLiveData String that stores the status of the most recent request
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val response: LiveData<String>
        get() = _response

    private val _list = mutableListOf<Deal>()
    private val _dealTempList = MutableLiveData<List<Deal>>()
    val dealTempList: LiveData<List<Deal>>
        get() = _dealTempList

    val dataSource = DealDatabase.getInstance(application).dealDatabaseDao

    /*init {
        getAsteroidProperties(AsteroidApiFilter.VIEW_SAVED)
        getImage()
        addtoList()
        Timber.i("Image1:" + imgURL.toString())
        Timber.i("Image2:" + _imgURL.toString())

        //_list.add(asteroid1)

    }*/

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
        addtoList()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties() {
        // TODO (05) Call the MarsApi to enqueue the Retrofit request, implementing the callbacks
        MarsApi.retrofitService.getProperties().enqueue( object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
                val gson = Gson()
                val arrayDealType = object : TypeToken<Array<Deal>>() {}.type

                var dealsList: Array<Deal> = gson.fromJson(response.body(), arrayDealType)
                //dealsList.forEachIndexed  { idx, deal -> println("> Item ${idx}:\n${deal}") }
                dealsList.forEach {  "BigDeals:" + Timber.i("BigDealsindex" + it.dealName) }
                dealsList.forEach {
                    //dataSource.insert(it)
                    _list.add(it)
                }
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        insertDealsToDatabase()
                    }

                }
            }
        })
        //_response.value = "Set the Mars API Response here!"
    }

    private suspend fun insertDealsToDatabase() {
        //_list.forEach{dataSource.insert(it)}
        dataSource.insertAll(_list)
        //_list.clear()
    }
    private fun addtoList(){
        val dealTemp1List = dataSource.getAllDeals()
        _list.clear()
        dealTemp1List.observeForever {
            it.forEach { _list.add(it) }
            _dealTempList.value = _list
        }
    }



}
