package com.vadim.mvptest.model

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.rxjava3.subjects.BehaviorSubject

class AndroidNetworkStatus(context: Context) : INetworkStatus {
    private val statusSubject: BehaviorSubject<Boolean> =
        BehaviorSubject.create()
    init {
        statusSubject.onNext(false)
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                statusSubject.onNext(true)
            }
            override fun onUnavailable() {
                statusSubject.onNext(false)
            }
            override fun onLost(network: Network) {
                statusSubject.onNext(false)
            }
        })
    }

    override fun isOnline() = statusSubject
    override fun isOnlineSingle() = statusSubject.first(false)
}
