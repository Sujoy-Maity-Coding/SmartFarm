package com.sujoy.smartfarming

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationTracker @Inject constructor(

    @ApplicationContext
    private val context: Context

) {

    private val client =
        LocationServices
            .getFusedLocationProviderClient(
                context
            )

    @SuppressLint("MissingPermission")
    suspend fun getLocation(): Location? =
        suspendCancellableCoroutine { cont ->

            client.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            )
                .addOnSuccessListener {

                    cont.resume(it)
                }
                .addOnFailureListener {

                    cont.resume(null)
                }
        }
}