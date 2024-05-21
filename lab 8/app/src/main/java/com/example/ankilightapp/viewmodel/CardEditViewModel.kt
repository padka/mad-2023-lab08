package com.example.ankilightapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ankilightapp.db.AppDatabase
import com.example.ankilightapp.model.Card
import kotlinx.coroutines.launch

class CardEditViewModel(application: Application) : AndroidViewModel(application) {
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

    fun saveCard(cardId: Long, word: String, translation: String, example: String) {
        viewModelScope.launch {
            if (cardId == 0L) {
                val newCard = Card(0, word, translation, example)
                cardDao.insert(newCard)
            } else {
                val card = cardDao.getCardById(cardId)
                card?.apply {
                    this.word = word
                    this.translation = translation
                    this.example = example
                }
                if (card != null) {
                    cardDao.update(card)
                }
            }
        }
    }
}
