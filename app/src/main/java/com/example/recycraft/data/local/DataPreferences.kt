package com.example.recycraft.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.recycraft.data.remote.Session
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow
import java.util.prefs.Preferences

class DataPreferences private constructor(private val dataStore: DataStore<Preferences>){

    private val loginKey = booleanPreferencesKey("login_setting")
    private val tokenKey = stringPreferencesKey("token_key")

    fun getLoginSetting() : Flow<Session>{
        return dataStore.data.map { preferences ->
            Session(
                preferences[tokenKey] ?: "",
                preferences[loginKey] ?:false,
            )
        }
    }

    suspend fun saveLoginSetting(isLogin: Boolean, token: String){
        dataStore.edit { preferences ->
            preferences[loginKey] = isLogin
            preferences[tokenKey] = token
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : DataPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>):DataPreferences{
            return INSTANCE ?: synchronized(this){
                val instance = DataPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}