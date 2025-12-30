# Getting Started with Adaptive Chat

This guide will help you get the Adaptive Chat application running on both Android and iOS.

## Prerequisites

### For Both Platforms
- **JDK 11 or higher**: Required for Kotlin compilation
- **Git**: For cloning the repository

### For Android Development
- **Android Studio Arctic Fox or later** (recommended: latest stable version)
- **Android SDK 24 or higher**
- **Gradle 8.2** (included via wrapper)

### For iOS Development (macOS only)
- **Xcode 13 or later** (recommended: latest stable version)
- **CocoaPods** (optional, for additional dependencies)
- **macOS Monterey or later**

## Initial Setup

### 1. Clone the Repository

```bash
git clone https://github.com/catsoft/adaptive_chat.git
cd adaptive_chat
```

### 2. Get a Gemini API Key

The application uses Google's Gemini AI for chat functionality.

1. Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Sign in with your Google account
3. Click "Create API Key"
4. Copy your API key

**Important**: Keep your API key secure and never commit it to version control.

### 3. Configure API Keys

#### For Android:
Edit `androidApp/src/main/kotlin/com/catsoft/adaptivechat/android/MainActivity.kt`:

```kotlin
setContent {
    App(
        geminiApiKey = "YOUR_ACTUAL_API_KEY_HERE",  // Replace with your key
        // ...
    )
}
```

#### For iOS:
Edit `shared/src/iosMain/kotlin/com/catsoft/adaptivechat/MainViewController.kt`:

```kotlin
return ComposeUIViewController {
    App(
        geminiApiKey = "YOUR_ACTUAL_API_KEY_HERE",  // Replace with your key
        // ...
    )
}
```

## Building and Running

### Android

#### Option 1: Using Android Studio (Recommended)

1. Open Android Studio
2. Select "Open" and navigate to the `adaptive_chat` directory
3. Wait for Gradle sync to complete
4. Select the `androidApp` run configuration
5. Choose a device or emulator
6. Click the "Run" button (green triangle)

#### Option 2: Using Command Line

```bash
# Build debug APK
./gradlew :androidApp:assembleDebug

# Install on connected device
./gradlew :androidApp:installDebug

# Build and run
./gradlew :androidApp:installDebug && adb shell am start -n com.catsoft.adaptivechat.android/.MainActivity
```

### iOS

#### Using Xcode (Required for iOS)

1. Open Xcode
2. Open the `iosApp/iosApp.xcodeproj` file (if it exists) or create a new iOS project
3. In the iOS project:
   - Set the Development Team (required for running on device)
   - Set the Bundle Identifier
4. Build the shared framework:
   ```bash
   ./gradlew :shared:embedAndSignAppleFrameworkForXcode
   ```
5. Select a simulator or connected device
6. Click the "Run" button

**Note**: If `iosApp.xcodeproj` doesn't exist, you'll need to create it:

1. Open Xcode
2. Create a new iOS App project
3. Save it in the `iosApp` directory
4. Add the compiled `shared.framework` to the project

## Project Structure

```
adaptive_chat/
├── shared/                          # Shared Kotlin Multiplatform code
│   ├── src/
│   │   ├── commonMain/             # Platform-agnostic code
│   │   │   └── kotlin/com/catsoft/adaptivechat/
│   │   │       ├── model/          # Data models (Message, Conversation, Agent)
│   │   │       ├── service/        # Business logic (GeminiService, DataRepository)
│   │   │       ├── viewmodel/      # UI state management
│   │   │       ├── ui/             # Compose UI screens
│   │   │       └── App.kt          # Main application entry point
│   │   ├── androidMain/            # Android-specific code
│   │   └── iosMain/                # iOS-specific code
│   └── build.gradle.kts            # Shared module build config
├── androidApp/                      # Android application
│   └── src/main/
│       ├── kotlin/                 # Android-specific Kotlin code
│       ├── res/                    # Android resources
│       └── AndroidManifest.xml     # Android manifest
├── iosApp/                          # iOS application
│   └── iosApp/
│       └── iOSApp.swift            # iOS app entry point
├── build.gradle.kts                # Root build configuration
└── settings.gradle.kts             # Project settings
```

## Features Overview

### 1. Conversations Screen
- View all your past conversations
- See the last message and timestamp for each conversation
- Tap a conversation to continue chatting
- Tap the "+" button to start a new chat with an AI agent

### 2. Agents Screen
- Browse available AI agents with different specializations:
  - **General Assistant**: Helpful for everyday questions
  - **Code Assistant**: Expert in programming and development
  - **Creative Writer**: Assists with creative writing
  - **Teacher**: Explains concepts clearly
  - **Data Analyst**: Helps with data analysis
- Tap an agent to start a new conversation

### 3. Chat Screen
- Send text messages to the AI
- View message history with timestamps
- Auto-scrolls to the latest message
- Input options:
  - **Text**: Type your message in the text field
  - **Voice**: Record audio (placeholder - requires platform implementation)
  - **Image**: Upload images (placeholder - requires platform implementation)
  - **Document**: Attach documents (placeholder - requires platform implementation)

## Troubleshooting

### Android Issues

**Problem**: Gradle sync fails
- **Solution**: Ensure you have JDK 11+ installed and configured in Android Studio

**Problem**: App crashes on startup
- **Solution**: Check that you've added a valid Gemini API key

**Problem**: Cannot connect to Gemini
- **Solution**: Ensure the device/emulator has internet connectivity

### iOS Issues

**Problem**: Framework not found
- **Solution**: Build the shared framework first:
  ```bash
  ./gradlew :shared:embedAndSignAppleFrameworkForXcode
  ```

**Problem**: Code signing error
- **Solution**: Set your Development Team in Xcode project settings

**Problem**: App crashes on device
- **Solution**: Ensure you've added a valid Gemini API key

## Implementing Full Input Features

The current implementation includes placeholders for voice, image, and document inputs. To implement these:

### Voice Input

**Android**: Use `MediaRecorder` and `SpeechRecognizer`
**iOS**: Use `AVAudioRecorder` and `Speech` framework

### Image Input

**Android**: Use `ActivityResultContracts.PickVisualMedia`
**iOS**: Use `UIImagePickerController` or `PHPickerViewController`

### Document Input

**Android**: Use `ActivityResultContracts.OpenDocument`
**iOS**: Use `UIDocumentPickerViewController`

## API Usage and Costs

- Gemini API has free tier with rate limits
- Monitor your usage at [Google AI Studio](https://makersuite.google.com/)
- Consider implementing rate limiting for production use

## Next Steps

1. **Add Persistence**: Implement local storage using SQLDelight to save conversations
2. **Enhance UI**: Add custom themes, animations, and better layouts
3. **Implement Full Multimodal**: Complete the voice, image, and document input features
4. **Add Authentication**: Implement user accounts and cloud sync
5. **Improve Error Handling**: Add retry logic and better error messages

## Support

For issues or questions:
- Check the [GitHub Issues](https://github.com/catsoft/adaptive_chat/issues)
- Review the Kotlin Multiplatform docs: https://kotlinlang.org/docs/multiplatform.html
- Review the Compose Multiplatform docs: https://www.jetbrains.com/lp/compose-multiplatform/

## License

See the LICENSE file in the repository root.
