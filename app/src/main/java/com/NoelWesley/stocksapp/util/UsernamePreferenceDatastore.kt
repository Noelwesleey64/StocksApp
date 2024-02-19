package com.NoelWesley.stocksapp.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UsernamePreferenceDatastore(private val context: Context) {

    companion object{
        // Declare a private read-only property named dataStore of type DataStore<Preferences> for the Context class
        // Use the preferencesDataStore() function to create a DataStore instance with the name "Username"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Username");

        // Declare a constant named USERNAME_KEY of type StringPreferencesKey with the value "user_email"
        // This is used as a key to store and retrieve the user email from the DataStore
        val USERNAME_KEY = stringPreferencesKey("username")
    }

    // to get the Username
    // Declare a public read-only property named getEmail of type Flow<String?>
    // A Flow is a stream of data that can be collected asynchronously
    val getUsername: Flow<String?> = context.dataStore.data
        // Use the map operator to transform each Preferences object to our getUsername variable
        .map {preferences ->

            // Return the value associated with the USERNAME_KEY from the preferences, or an empty string if none
            preferences[USERNAME_KEY]?: ""

        }

    // to save the email
    // Declare a public suspend function named saveUsername that takes a String parameter named username
    // A suspend function can be paused and resumed by the coroutine that calls it
    suspend fun saveUsername(username: String){
        // Use the edit function to update the DataStore with a suspending lambda expression
        context.dataStore.edit {preferences ->

            // Set the value of the USERNAME_KEY to the name parameter in the preferences
              preferences[USERNAME_KEY] = username

        }
    }


}