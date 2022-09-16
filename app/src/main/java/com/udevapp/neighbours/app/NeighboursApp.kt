package com.udevapp.neighbours.app

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NeighboursApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("833f2f86-df08-4f5f-b253-dab7b746a814")
    }
}