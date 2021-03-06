package com.one4ll.xplayer.ui.Music

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.helpers.getExternalContentVideoUri
import com.one4ll.xplayer.helpers.getInternalContentVideoUri
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

class MusicViewModel(application: Application) : AndroidViewModel(application) {
    var mapplication: Application

    init {
        this.mapplication = application
    }

    val musicList = MutableLiveData<List<Media>>()
    suspend fun getMusicList() {
        withContext(IO) {
            val exUri = getExternalContentVideoUri(mapplication.applicationContext)
            val inUri = getInternalContentVideoUri(mapplication.applicationContext)
            exUri.addAll(inUri)
            exUri.forEach { println(it.name) }
            withContext(Main) {
                musicList.value = exUri
            }
        }
    }


}