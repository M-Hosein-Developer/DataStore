package com.example.datastorelib

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.datastorelib.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "new_data")


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //key
    private val sharedKey = stringPreferencesKey("new")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        writePreferences()

    }

    private fun writePreferences(){

        binding.btn.setOnClickListener {

            // Edite Text
            val text = binding.edt.text.toString()

            //write
            lifecycleScope.launch {

                dataStore.edit { hi ->
                    hi[sharedKey] = text
                }

            }

        }

    }



}

