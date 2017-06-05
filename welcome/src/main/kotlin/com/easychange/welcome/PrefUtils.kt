package com.easychange.welcome

import android.content.Context
import android.preference.PreferenceManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory


object PrefUtils {
    private val LOGGER: Logger = LoggerFactory.getLogger(PrefUtils::class.java)

    fun saveToPrefs(context: Context, key: String, value: Boolean) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getFromPrefs(context: Context, key: String, defaultValue: Boolean): Boolean {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        try {
            return sharedPrefs.getBoolean(key, defaultValue)
        } catch (e: Exception) {
            LOGGER.error("An error occurred while getting $key property from shared preferences", e)
            return defaultValue
        }
    }

    fun removeFromPrefs(context: Context, key: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.remove(key)
        editor.apply()
    }
}