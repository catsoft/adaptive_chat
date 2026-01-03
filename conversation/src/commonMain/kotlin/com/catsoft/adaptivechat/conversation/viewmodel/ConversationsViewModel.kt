package com.catsoft.adaptivechat.conversation.viewmodel

import androidx.lifecycle.ViewModel
import com.catsoft.adaptivechat.conversation.domain.Conversation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConversationsViewModel(
) : ViewModel() {
    
    val conversations: StateFlow<List<Conversation>> = MutableStateFlow(emptyList())
}
