/*
 *  Copyright 2018, The Android Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.android.marsrealestate.detail

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
//import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.marsrealestate.MainActivity
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.databinding.FragmentDetailBinding
import com.example.android.marsrealestate.util.sendNotification
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * This [Fragment] will show the detailed information about a selected piece of Mars real estate.
 */
class DetailFragment : Fragment() {

    private lateinit var orderButton: Button
    private lateinit var notificationManager: NotificationManager

    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        @Suppress("UNUSED_VARIABLE")
        //val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val deal = DetailFragmentArgs.fromBundle(arguments!!).selectedDeal
        binding.deal = deal
        notificationManager = context?.getSystemService(
                NotificationManager::class.java
        )!!
        createChannel(
                getString(R.string.loading_notification_channel_id),
                getString(R.string.loading_notification_channel_name)
        )
        context?.let { notificationManager.sendNotification("test7", it) }
        context?.let { it1 -> this.notificationManager?.sendNotification("test5", it1) }
        orderButton = binding.root.findViewById(R.id.button)
        orderButton.setOnClickListener {
            context?.applicationContext?.let { it1 -> notificationManager?.sendNotification("test", it1) }
            context?.let { it1 -> this.notificationManager?.sendNotification("test3", it1) }
        }
        return binding.root


    }

    private fun createChannel(channelId: String, channelName: String) {
        // TODO: Step 1.6 START create a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                    channelId,
                    channelName,
                    // TODO: Step 2.4 change importance
                    NotificationManager.IMPORTANCE_HIGH
            )// TODO: Step 2.6 disable badges for this channel
                    .apply {
                        setShowBadge(false)
                    }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.loading_notification_channel_desc)

            val notificationManager = context?.getSystemService(
                    NotificationManager::class.java
            )
            notificationManager?.createNotificationChannel(notificationChannel)

        }
        // TODO: Step 1.6 END create a channel
    }
}
