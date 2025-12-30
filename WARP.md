# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

Adaptive Chat is a Kotlin Multiplatform Mobile (KMP) application for iOS and Android with AI chat capabilities powered by Google Gemini. The project uses Compose Multiplatform for shared UI and follows the MVVM architecture pattern.

## Common Commands

### Building
```bash
# Build Android debug APK
./gradlew :androidApp:assembleDebug

# Install on Android device/emulator
./gradlew :androidApp:installDebug

# Build shared framework for iOS
./gradlew :shared:embedAndSignAppleFrameworkForXcode

# Clean build
./gradlew clean
```

### Running
**Android**: Use Android Studio or `./gradlew :androidApp:installDebug` followed by launching the app on device/emulator

**iOS**: Open `iosApp/iosApp.xcodeproj` in Xcode, build the shared framework first, then run from Xcode

### Development
```bash
# Find Kotlin files in shared module
find shared/src -name "*.kt"

# Check Gradle configuration
./gradlew projects

# Verify dependencies
./gradlew :shared:dependencies
```

## Architecture

### Module Structure
- **shared/**: KMP module containing all shared business logic and UI
  - **commonMain/**: Platform-agnostic code (models, services, viewmodels, UI)
  - **androidMain/**: Android-specific implementations (Ktor client, file handlers, date formatters)
  - **iosMain/**: iOS-specific implementations (Ktor client, file handlers, date formatters)
- **androidApp/**: Android-specific application wrapper
- **iosApp/**: iOS-specific application wrapper (SwiftUI + Compose interop)

### MVVM Architecture
The application follows strict MVVM with these layers:

**Model Layer** (`model/`):
- `Message`: Chat messages with content, timestamp, type (TEXT, VOICE, IMAGE, DOCUMENT)
- `Conversation`: Conversation threads with message history
- `Agent`: AI agents with specialized system prompts and capabilities
- `MessageType`: Enum for input types

**Service Layer** (`service/`):
- `GeminiService`: Handles all Gemini API communication via Ktor HTTP client
  - Builds conversation context from history (last 10 messages)
  - Sends requests to `gemini-pro:generateContent` endpoint
  - Contains placeholder methods for voice, image, and document processing
- `DataRepository`: In-memory data store using StateFlow
  - Manages conversations and agents
  - Provides default agents with pre-configured system prompts
  - No persistence layer yet (future: SQLDelight)

**ViewModel Layer** (`viewmodel/`):
- `ChatViewModel`: Manages chat screen state, processes user input, integrates with GeminiService
- `ConversationsViewModel`: Manages conversation list state
- `AgentsViewModel`: Manages agent selection and new conversation creation

**UI Layer** (`ui/`):
- All UI built with Compose Multiplatform (shared between platforms)
- `ChatScreen`: Message list (LazyColumn), input controls, loading states
- `ConversationsScreen`: List of past conversations with navigation
- `AgentsScreen`: Grid of available AI agents

**Navigation**: Custom sealed class `Screen` in `App.kt` manages navigation state

### Platform-Specific Code (expect/actual)
- `FileHandler`: Placeholder interfaces for image/document picking and audio recording
- `DateFormatter`: Platform-specific date formatting (SimpleDateFormat on Android, NSDateFormatter on iOS)

### State Management
All ViewModels expose state via Kotlin `StateFlow`, which Compose UI observes with `collectAsState()`. UI automatically updates when state changes.

### API Integration
**Gemini API**:
- Endpoint: `https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent`
- Authentication: API key passed as query parameter (currently hardcoded - not production-ready)
- Request includes conversation history for context (last 10 messages)
- Generation config: temperature=0.9, topK=1, topP=1.0, maxOutputTokens=2048

**Networking**:
- Ktor HTTP client with content negotiation (JSON)
- Kotlinx Serialization for JSON parsing
- Platform-specific Ktor engines (ktor-client-android, ktor-client-darwin)

## Configuration

### API Keys
Gemini API keys are currently hardcoded in:
- Android: `androidApp/src/main/kotlin/com/catsoft/adaptivechat/android/MainActivity.kt`
- iOS: `shared/src/iosMain/kotlin/com/catsoft/adaptivechat/MainViewController.kt`

Replace `"YOUR_GEMINI_API_KEY_HERE"` with actual API key from [Google AI Studio](https://makersuite.google.com/app/apikey).

**Security Note**: For production, use EncryptedSharedPreferences (Android) or Keychain (iOS), or move API calls to a backend service.

### Build Configuration
- Kotlin 1.9.20
- Compose Multiplatform 1.5.10
- Android minSdk: 24, compileSdk: 34
- iOS: 13+
- JDK: 11 (jvmTarget: "11")
- Gradle: 8.2+

## Key Dependencies
- **Ktor**: 2.3.5 (HTTP client)
- **Kotlinx Serialization**: 1.6.0
- **Kotlinx Coroutines**: 1.7.3
- **Lifecycle ViewModel**: 2.6.2
- **Compose Material3**: Android 1.1.2
- **Activity Compose**: 1.8.0

## Development Notes

### Message Flow
1. User types in ChatScreen → `ChatViewModel.sendTextMessage()`
2. ViewModel creates user Message, updates `_messages` StateFlow
3. ViewModel calls `DataRepository.updateConversation()` to persist
4. ViewModel calls `GeminiService.sendMessage()` with message + history + agent's systemPrompt
5. GeminiService builds prompt with last 10 messages, sends HTTP POST to Gemini API
6. Response parsed, AI Message created, StateFlow updated
7. UI auto-updates via `collectAsState()`

### Agent System
Each agent has a unique `systemPrompt` that modifies the AI's behavior:
- **General Assistant**: Friendly, general-purpose
- **Code Assistant**: Expert software engineer with code examples
- **Creative Writer**: Story ideas and creative content
- **Teacher**: Patient, breaks down complex topics
- **Data Analyst**: Data analysis and statistical concepts

When creating a new conversation, the agent's systemPrompt is included in all API requests for that conversation.

### Placeholder Features
Voice, image, and document inputs have placeholder implementations in `GeminiService`:
- `processVoiceInput()`: Returns mock transcription text
- `processImageInput()`: Returns mock image analysis
- `processDocumentInput()`: Returns mock document processing

To fully implement:
- **Voice**: Use platform-specific audio recording (MediaRecorder/AVAudioRecorder) + speech recognition
- **Image**: Use platform-specific image pickers + Gemini vision API
- **Document**: Use platform-specific document pickers + Gemini multimodal API

### Error Handling
GeminiService catches all exceptions and returns error Messages to display in chat. No retry logic currently implemented.

### Data Persistence
DataRepository stores data in-memory only. Conversations are lost on app restart. Future enhancement: integrate SQLDelight for local database storage.

## Testing
No test infrastructure currently exists. Recommended setup:
- Unit tests for ViewModels with mock services
- Integration tests for API calls with test doubles
- UI tests for Compose screens

## Future Enhancements
From ARCHITECTURE.md:
1. Add SQLDelight for conversation persistence
2. Implement user authentication
3. Complete multimodal input features (voice, image, document)
4. Add dependency injection (Koin)
5. Improve error handling with retry logic
6. Implement rate limiting
7. Add offline support with local caching
8. Secure API key storage
