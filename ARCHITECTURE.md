# Architecture Documentation

## Overview

Adaptive Chat is a Kotlin Multiplatform Mobile (KMP) application that demonstrates modern cross-platform development using Compose Multiplatform and integrates with Google's Gemini AI API.

## Architecture Pattern

The application follows the **MVVM (Model-View-ViewModel)** architecture pattern with a clear separation of concerns:

```
┌─────────────────────────────────────────┐
│              UI Layer (View)            │
│    Compose Multiplatform Screens        │
└──────────────┬──────────────────────────┘
               │ observes StateFlow
┌──────────────▼──────────────────────────┐
│         ViewModel Layer                  │
│   ChatViewModel, AgentsViewModel, etc.   │
└──────────────┬──────────────────────────┘
               │ calls
┌──────────────▼──────────────────────────┐
│         Service Layer                    │
│  GeminiService, DataRepository           │
└──────────────┬──────────────────────────┘
               │ uses
┌──────────────▼──────────────────────────┐
│          Model Layer                     │
│  Message, Conversation, Agent            │
└──────────────────────────────────────────┘
```

## Modules

### 1. Shared Module

Contains all shared business logic and UI code that runs on both platforms.

#### Structure:
- **commonMain**: Platform-agnostic code
- **androidMain**: Android-specific implementations
- **iosMain**: iOS-specific implementations

#### Key Components:

##### Models (`model/`)
- **Message**: Represents a single chat message with content, timestamp, and type
- **Conversation**: Represents a conversation thread with history
- **Agent**: Represents an AI agent with specific capabilities and prompts
- **MessageType**: Enum for different input types (TEXT, VOICE, IMAGE, DOCUMENT)

##### Services (`service/`)

**GeminiService**
- Handles communication with Google Gemini API
- Manages API requests and responses
- Processes different input types (text, voice, image, document)
- Key methods:
  - `sendMessage()`: Sends text and gets AI response
  - `processVoiceInput()`: Handles voice input (placeholder)
  - `processImageInput()`: Handles image analysis (placeholder)
  - `processDocumentInput()`: Handles document processing (placeholder)

**DataRepository**
- In-memory data storage for conversations and agents
- Manages conversation state using StateFlow
- Provides default AI agents with specialized prompts
- Key methods:
  - `addConversation()`: Creates new conversation
  - `updateConversation()`: Updates existing conversation
  - `getConversation()`: Retrieves conversation by ID
  - `getAgent()`: Retrieves agent by ID

##### ViewModels (`viewmodel/`)

**ChatViewModel**
- Manages chat screen state
- Handles user input and AI responses
- Maintains message history
- Key features:
  - Real-time message updates via StateFlow
  - Loading state management
  - Integration with GeminiService for AI responses

**ConversationsViewModel**
- Manages conversations list
- Provides access to all conversations
- Handles conversation selection

**AgentsViewModel**
- Manages agents list
- Creates new conversations with selected agents
- Provides agent information

##### UI (`ui/`)

**ChatScreen**
- Main chat interface
- Message bubble display
- Input controls (text, voice, image, document)
- Auto-scrolling message list
- Loading indicators

**ConversationsScreen**
- List of past conversations
- Navigation to chat or agents screen
- Empty state handling

**AgentsScreen**
- Grid of available AI agents
- Agent selection interface
- Agent descriptions

##### Platform-Specific Code

**FileHandler** (expect/actual)
- Defines interface for file operations
- Platform-specific implementations for:
  - Image picking
  - Document picking
  - Audio recording

**DateFormatter** (expect/actual)
- Defines interface for date formatting
- Platform-specific implementations using:
  - Android: `SimpleDateFormat`
  - iOS: `NSDateFormatter`

### 2. Android App Module

Contains Android-specific application code.

**MainActivity**
- Entry point for Android app
- Sets up Compose UI with App composable
- Configures API key

**AndroidManifest.xml**
- Declares permissions (INTERNET, RECORD_AUDIO, etc.)
- Configures app metadata

### 3. iOS App Module

Contains iOS-specific application code.

**iOSApp.swift**
- SwiftUI app entry point
- Wraps Compose UI in UIViewController

**MainViewController.kt**
- Creates ComposeUIViewController
- Configures API key for iOS

## Data Flow

### Message Sending Flow

```
User types message
    ↓
ChatScreen captures input
    ↓
ChatViewModel.sendTextMessage()
    ↓
1. Create user Message
2. Update local state (_messages)
3. Update repository (updateConversation)
    ↓
GeminiService.sendMessage()
    ↓
1. Build request with history
2. HTTP POST to Gemini API
3. Parse response
    ↓
Create AI Message from response
    ↓
Update local state (_messages)
    ↓
Update repository
    ↓
UI automatically updates (StateFlow)
```

### Navigation Flow

```
App Launch
    ↓
ConversationsScreen (default)
    │
    ├─► Tap "+" or "New Chat" → AgentsScreen
    │       └─► Select Agent → Create Conversation → ChatScreen
    │
    └─► Tap Conversation → ChatScreen
            └─► Tap Back → ConversationsScreen
```

## State Management

### StateFlow for Reactive UI

All ViewModels expose state using Kotlin `StateFlow`:

```kotlin
private val _messages = MutableStateFlow<List<Message>>(emptyList())
val messages: StateFlow<List<Message>> = _messages.asStateFlow()
```

UI screens collect state:

```kotlin
val messages by viewModel.messages.collectAsState()
```

This ensures:
- UI automatically updates when state changes
- Type-safe state access
- Proper lifecycle handling

## API Integration

### Gemini API

**Endpoint**: `https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent`

**Request Structure**:
```json
{
  "contents": [{
    "parts": [{
      "text": "user message with context"
    }]
  }],
  "generationConfig": {
    "temperature": 0.9,
    "topK": 1,
    "topP": 1.0,
    "maxOutputTokens": 2048
  }
}
```

**Authentication**: API key passed as query parameter

### Network Layer

Uses Ktor HTTP client:
- Content negotiation with JSON
- Cross-platform support (Android, iOS)
- Kotlinx Serialization for JSON parsing

## UI Components

### Compose Multiplatform

All UI is built with Compose Multiplatform using Material3:

**Key Features**:
- Shared UI code for both platforms
- Material Design 3 components
- Responsive layouts
- Platform-specific look and feel

**Reusable Components**:
- `MessageBubble`: Displays individual messages
- `ConversationItem`: Shows conversation in list
- `AgentCard`: Displays agent information

## Security Considerations

### Current Implementation

1. **API Key Storage**: Currently hardcoded (not secure for production)
2. **HTTPS**: All API calls use HTTPS
3. **Permissions**: Properly declared in manifests

### Production Recommendations

1. **Secure API Key Storage**:
   - Android: Use EncryptedSharedPreferences
   - iOS: Use Keychain Services
   - Backend: Move API calls to backend service

2. **User Authentication**: Add user accounts and auth

3. **Rate Limiting**: Implement client-side rate limiting

4. **Input Validation**: Validate and sanitize user inputs

## Performance Optimization

### Current Optimizations

1. **Lazy Loading**: Uses LazyColumn for message lists
2. **State Hoisting**: Proper state management prevents unnecessary recompositions
3. **Coroutines**: Async operations don't block UI thread

### Future Optimizations

1. **Message Pagination**: Load older messages on demand
2. **Image Caching**: Cache downloaded images
3. **Request Debouncing**: Prevent rapid-fire API calls
4. **Conversation Persistence**: Save to local database

## Testing Strategy

### Unit Tests (Recommended)

- Test ViewModels with mock services
- Test business logic in services
- Test data models serialization

### Integration Tests (Recommended)

- Test API integration with test doubles
- Test navigation flows
- Test state management

### UI Tests (Recommended)

- Test user interactions
- Test navigation
- Test error states

## Dependencies

### Shared Module
- Kotlin 1.9.20
- Compose Multiplatform 1.5.10
- Ktor Client 2.3.5
- Kotlinx Serialization 1.6.0
- Kotlinx Coroutines 1.7.3
- Lifecycle ViewModel 2.6.2

### Android
- Android SDK 24+
- Compose UI 1.5.4
- Material3 1.1.2

### iOS
- iOS 13+
- SwiftUI (for wrapper)

## Build Configuration

### Gradle

- **Root**: Configures plugins for all modules
- **Shared**: Multiplatform targets and dependencies
- **Android App**: Android-specific configuration

### Kotlin Multiplatform Targets

- Android: JVM target with Android compilation
- iOS: Native targets (arm64, x64, simulatorArm64)

## Future Architecture Enhancements

1. **Dependency Injection**: Add Koin or similar
2. **Database**: Integrate SQLDelight for persistence
3. **Offline Support**: Cache conversations locally
4. **Background Sync**: Sync conversations in background
5. **Analytics**: Add usage tracking
6. **Crash Reporting**: Integrate crash analytics
7. **Feature Flags**: Add remote configuration

## References

- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- [Gemini API](https://ai.google.dev/docs)
- [Ktor](https://ktor.io/)
- [Material Design 3](https://m3.material.io/)
