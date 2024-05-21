package com.example.ankilightapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ankilightapp.db.AppDatabase
import com.example.ankilightapp.model.Card
import kotlinx.coroutines.launch

class CardDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _card = MutableLiveData<Card>()
    val card: LiveData<Card> get() = _card

    private val cardDao = AppDatabase.getDatabase(application).cardDao()

    fun loadCard(cardId: Long) {
        viewModelScope.launch {
            val card = cardDao.getCardById(cardId)
            card?.let {
                _card.value = it
            }
        }
    }
}
