package ru.novotelecom.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChannelsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChannelsViewModel::class.java)) {
            val loader = ChannelsLoader()
            @Suppress("UNCHECKED_CAST") // Guaranteed to succeed at this point.
            return ChannelsViewModel(loader) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}