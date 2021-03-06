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

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.database.DealDatabase
import com.example.android.marsrealestate.databinding.FragmentOverviewBinding
import com.example.android.marsrealestate.models.Deal
import timber.log.Timber

/**
 * This fragment shows the the status of the Mars real-estate web services transaction.
 */
class OverviewFragment : Fragment() {

    /**
     * Lazily initialize our [OverviewViewModel].
     */

    /*val application = requireNotNull(this.activity).application
    val dataSource = DealDatabase.getInstance(application).dealDatabaseDao
    val viewModelFactory = OverviewViewModelFactory(dataSource, application)
    val DealViewModel =
            ViewModelProvider(
                    this, viewModelFactory).get(OverviewViewModel::class.java)*/
    //binding.viewModel = overviewViewModel
    /*private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }*/

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //val binding = FragmentOverviewBinding.inflate(inflater)
        val binding: FragmentOverviewBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_overview, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        val application = requireNotNull(this.activity).application
        val dataSource = DealDatabase.getInstance(application).dealDatabaseDao
        val viewModelFactory = OverviewViewModelFactory(dataSource, application)
        val DealViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(OverviewViewModel::class.java)

        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = DealViewModel

        setHasOptionsMenu(true)


        val adapter = DealAdapter(DealListener { deal ->
            DealViewModel.onDealClicked(deal)
        })
        binding.dealRecycler.adapter = adapter

        DealViewModel.dealTempList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.dealList = it as MutableList<Deal>
            }
        })

        DealViewModel.navigateToDealDetails.observe(viewLifecycleOwner, Observer { deal ->
            deal?.let { this.findNavController().navigate(
                    OverviewFragmentDirections.actionShowDetail(deal))
                DealViewModel.onDealNavigated()
            }
        })

        /*DealViewModel.navigateToAsteroidDetails.observe(viewLifecycleOwner, Observer { asteroid ->
            asteroid?.let { this.findNavController().navigate(
                    MainFragmentDirections.actionShowDetail(asteroid))
                DealViewModel.onAsteroidNavigated()
            }
        })*/
        Timber.plant(Timber.DebugTree())
        return binding.root
    }

    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
