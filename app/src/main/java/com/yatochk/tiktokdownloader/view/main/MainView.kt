package com.yatochk.tiktokdownloader.view.main

interface MainView {
    fun showPrivacy()
    fun sendFeedback()
    fun showRate()
    fun goToHistory()
    var showDeleteToolbar: Boolean
}