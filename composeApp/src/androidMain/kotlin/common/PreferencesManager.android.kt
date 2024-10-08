package common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual fun getPreferencesManager(context: Any?): PreferencesManager? {
    if (context !is Context) return null
    val dataStore = createDataStore(context)
    return PreferencesManagerImpl(dataStore)
}

fun createDataStore(context: Context): DataStore<Preferences> = createDataStore(
    producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
)