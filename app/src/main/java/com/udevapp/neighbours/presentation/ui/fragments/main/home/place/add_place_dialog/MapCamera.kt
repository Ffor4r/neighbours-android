package com.udevapp.neighbours.presentation.ui.fragments.main.home.place.add_place_dialog

import com.yandex.mapkit.Animation
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.Map

class MapCamera {
    companion object {
        fun move(map: Map, location: Location, zoom: Float = 18f) {
            map.move(
                CameraPosition(location.position, zoom, 0f, 0f),
                Animation(Animation.Type.SMOOTH, 1f),
                null
            )
        }
    }
}