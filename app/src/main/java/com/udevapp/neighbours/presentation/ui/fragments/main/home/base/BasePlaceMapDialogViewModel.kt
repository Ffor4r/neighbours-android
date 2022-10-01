package com.udevapp.neighbours.presentation.ui.fragments.main.home.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udevapp.neighbours.presentation.base.BaseViewModel
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManagerUtils
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.search.*
import com.yandex.runtime.Error

abstract class BasePlaceMapDialogViewModel : BaseViewModel() {

    private val _lastLocation = MutableLiveData<Location?>()
    val lastLocation: LiveData<Location?> = _lastLocation

    private val _userLocation = MutableLiveData<Location>()
    val userLocation: LiveData<Location> = _userLocation

    private val _currentPosition = MutableLiveData<Point>()
    val currentPosition: LiveData<Point> = _currentPosition

    private val _addressText = MutableLiveData<String?>()
    val addressText: LiveData<String?> = _addressText

    private val _locationText = MutableLiveData<String?>()
    val locationText: LiveData<String?> = _locationText

    private val listener: SearchListener = SearchListener()

    fun loadLastLocation() {
        val location = LocationManagerUtils.getLastKnownLocation()
        _lastLocation.value = location
        _currentPosition.value = location?.position
    }

    fun loadUserLocation() {
        val locationManager = MapKitFactory.getInstance().createLocationManager()
        val locationListener = object : LocationListener {
            override fun onLocationUpdated(location: Location) {
                _userLocation.value = location
                _currentPosition.value = location.position
            }

            override fun onLocationStatusUpdated(p0: LocationStatus) {
            }

        }
        locationManager.requestSingleUpdate(locationListener)
    }

    fun updateCurrentLocation(position: Point) {
        _currentPosition.value = position
    }

    inner class SearchListener : Session.SearchListener {
        override fun onSearchResponse(response: Response) {
            val geoObject = response.collection.children.first().obj
            _locationText.value = geoObject?.descriptionText.toString()
            _addressText.value = geoObject?.name.toString()
        }

        override fun onSearchError(error: Error) {
            _addressText.value = null
        }
    }

    fun searchAddress() {
        if (currentPosition.value != null) {
            SearchFactory.getInstance().createSearchManager(SearchManagerType.ONLINE)
                .submit(currentPosition.value!!, 18, SearchOptions(), listener)
        }
    }
}