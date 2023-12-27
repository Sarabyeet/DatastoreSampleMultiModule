package com.sarabyeet.datastoretest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sarabyeet.datastoretest.databinding.ActivityMainBinding
import com.sarabyeet.settings.Constants
import com.sarabyeet.settings.SettingsHelper
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var settings: SettingsHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setListeners()
    }

    private fun init() {
        settings = SettingsHelper(this)
    }

    private fun setListeners() {
        with(binding) {
            getSettings.setOnClickListener { saveToSettings() }
            saveSettings.setOnClickListener { getFromSettings() }
            reset.setOnClickListener { reset() }
        }
    }

    private fun saveToSettings() {
        if (::settings.isInitialized) {
            val name = binding.etInput.text?.toString() ?: ""
            lifecycleScope.launch {
                settings.putPreference(Constants.NAME_KEY, name)
            }
        }
    }

    private fun getFromSettings() {
        if (::settings.isInitialized) {
            lifecycleScope.launch {
                settings.getPreference(Constants.NAME_KEY, "").collectLatest {
                    binding.tvLabel.text = it.ifBlank { "No initial value/ Hard reset" }
                }
            }
        }
    }

    private fun reset() {
        lifecycleScope.launch {
            settings.clearAllPreference<String>()
        }
    }
}