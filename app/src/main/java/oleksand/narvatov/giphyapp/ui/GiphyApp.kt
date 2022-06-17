package oleksand.narvatov.giphyapp.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import oleksand.narvatov.giphyapp.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class GiphyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}