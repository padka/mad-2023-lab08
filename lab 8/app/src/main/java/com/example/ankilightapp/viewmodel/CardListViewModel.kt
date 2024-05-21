package com.example.ankilightapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ankilightapp.db.AppDatabase
import com.example.ankilightapp.model.Card
import kotlinx.coroutines.launch

class CardListViewModel(application: Application) : AndroidViewModel(application) {
    private val cardDao = AppDatabase.getDatabase(application).cardDao()
    val allCards: LiveData<List<Card>> = cardDao.getAllCards()

    fun deleteCard(card: Card) {
        viewModelScope.launch {
            cardDao.delete(card)
        }
    }
}
