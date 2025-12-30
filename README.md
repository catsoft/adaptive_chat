# [Project Name] — Kotlin Multiplatform (KMP) Mobile Showcase

[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-blue.svg)](https://kotlinlang.org)
[![KMP](https://img.shields.io/badge/Platform-Android%20%7C%20iOS-lightgrey.svg)](https://kotlinlang.org/docs/multiplatform.html)
[![Compose Multiplatform](https://img.shields.io/badge/UI-Compose_Multiplatform-blueviolet.svg)](https://www.jetbrains.com/lp/compose-multiplatform/)

## 📌 Overview
This is a production-grade mobile application built using **Kotlin Multiplatform (KMP)**. It showcases a modern approach to cross-platform development where business logic, data management, and UI are shared between **Android** and **iOS**.

The goal of this project is to demonstrate **Clean Architecture**, **MVI state management**, and an **Offline-first** approach in a multiplatform environment.

## 🏗 Architecture & Code Sharing
The project is architected to maximize code reuse while maintaining high performance and native feel.

* **Shared UI:** Powered by **Compose Multiplatform**, allowing for a consistent design system across platforms.
* **Shared Logic:** 100% of the Domain and Data layers are shared in the `:shared` module.
* **MVI Pattern:** Ensures a unidirectional data flow (UDF) and a single source of truth for the UI state.
* **Platform Integration:** Uses `expect/actual` declarations for platform-specific features (e.g., KeyChain, Haptics, or local file system).

## 🛠 Tech Stack
### Common (Shared)
* **Compose Multiplatform**.
* **Concurrency:** Kotlin Coroutines & Flow.
* **Networking:** **Ktor** with Content Negotiation and Kotlinx Serialization.
* **Database:** **Room**.
* **DI:** **Koin** (Lightweight and KMP-ready).
* **Image Loading:** **Coil3** (Multiplatform support).
* **State Management:** **Store5** (or standard Flow-based MVI) for robust data syncing.

## 🚀 Key Features
- [x] **Chat with AI Gemini**
- [x] **Text, Voice, Image, Doc input**
- [x] **Conversations history**
- [x] **Agents list**
