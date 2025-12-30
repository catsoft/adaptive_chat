# Adaptive Chat

A Kotlin Multiplatform Mobile (KMP) application for iOS and Android with AI chat capabilities powered by Google Gemini.

## Features

- **Three Main Screens:**
  - **Chat Screen**: Interactive chat interface with AI powered by Google Gemini
  - **Conversations List**: View and manage past conversations
  - **Agents List**: Select from different AI agents with specialized capabilities

- **Multiple Input Types:**
  - Text input
  - Voice input (placeholder implementation)
  - Image input (placeholder implementation)
  - Document input (placeholder implementation)

- **AI Agents:**
  - General Assistant
  - Code Assistant
  - Creative Writer
  - Teacher
  - Data Analyst

## Project Structure

```
adaptive_chat/
├── shared/                    # Shared KMP code
│   └── src/
│       ├── commonMain/       # Platform-agnostic code
│       │   └── kotlin/
│       │       └── com/catsoft/adaptivechat/
│       │           ├── model/          # Data models
│       │           ├── service/        # Business logic and API clients
│       │           ├── viewmodel/      # ViewModels for UI state
│       │           ├── ui/             # Compose UI screens
│       │           └── App.kt          # Main app composable
│       ├── androidMain/      # Android-specific implementations
│       └── iosMain/          # iOS-specific implementations
├── androidApp/               # Android application module
└── iosApp/                   # iOS application module
```

## Setup

### Prerequisites

- JDK 11 or higher
- Android Studio Arctic Fox or later
- Xcode 13 or later (for iOS development)
- Kotlin 1.9.20 or later

### Configuration

1. **Get a Gemini API Key:**
   - Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
   - Create a new API key
   
2. **Add your API key:**
   - For Android: Edit `androidApp/src/main/kotlin/com/catsoft/adaptivechat/android/MainActivity.kt`
   - For iOS: Edit `shared/src/iosMain/kotlin/com/catsoft/adaptivechat/MainViewController.kt`
   - Replace `"YOUR_GEMINI_API_KEY_HERE"` with your actual API key

### Building

#### Android

```bash
./gradlew :androidApp:assembleDebug
```

#### iOS

1. Open the `iosApp` folder in Xcode
2. Select a simulator or device
3. Click Run

## Architecture

The application follows the MVVM (Model-View-ViewModel) pattern:

- **Models**: Define data structures for Messages, Conversations, and Agents
- **Services**: Handle API communication with Gemini and data persistence
- **ViewModels**: Manage UI state and business logic
- **UI**: Compose Multiplatform screens shared between platforms

## Dependencies

- **Compose Multiplatform**: UI framework
- **Ktor**: HTTP client for API calls
- **Kotlinx Serialization**: JSON serialization
- **Kotlinx Coroutines**: Asynchronous programming
- **Lifecycle ViewModel**: State management

## Future Enhancements

- Complete implementation of voice input using platform-specific audio recorders
- Complete implementation of image input using platform-specific image pickers
- Complete implementation of document input using platform-specific document pickers
- Add local storage for conversations using SQLDelight
- Add user authentication
- Add more sophisticated error handling and retry logic
- Implement Gemini's multimodal capabilities for vision and audio

## License

See LICENSE file for details.

