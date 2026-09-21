# Day 18 - LLD of a Music Player App (Spotify Clone)

## 1. Requirements Gathering
Designing a complex system like a Music Player (e.g., Spotify, Apple Music) requires combining multiple design patterns to ensure a clean, scalable architecture.

*   **Functional Requirements:**
    *   Users can play individual songs or entire playlists.
    *   Support multiple playback algorithms: Sequential, Shuffle, Custom Queue.
    *   Support multiple output devices: Bluetooth Speakers, Wired Headphones, Smart TVs.
*   **Non-Functional Requirements:**
    *   **Scalability:** Easy to add new playback algorithms or output devices.
    *   **Loose Coupling:** The core audio engine should not be tightly coupled to the UI or specific device drivers.

---

## 2. Design Patterns Applied

### A. Strategy Pattern (Playback Algorithms)
*   **Problem:** We need different ways to play a playlist (Normal, Shuffle, Repeat All). Hardcoding these into the playlist manager causes `if-else` explosion.
*   **Solution:** Create an `IPlaybackStrategy` interface. 
    *   `SequentialPlaybackStrategy`
    *   `ShufflePlaybackStrategy`
    *   The player simply calls `strategy.getNextSong(playlist)`.

### B. Adapter Pattern (Output Devices)
*   **Problem:** Playing audio through a wired headphone uses different system APIs than streaming audio via Bluetooth or Chromecast.
*   **Solution:** Create an `IOutputDevice` interface. For specific external APIs, create adapters like `BluetoothSpeakerAdapter` and `WiredSpeakerAdapter` that translate our app's `playAudio(bytes)` command into the specific format the external device expects.

### C. Singleton Pattern (Managers)
*   **Problem:** We need a centralized registry for all the songs and playlists on the device to prevent data duplication and memory leaks.
*   **Solution:** A `MusicLibraryManager` implemented as a Singleton. It acts as the single source of truth for querying songs and playlists.

### D. Facade Pattern (The Orchestrator)
*   **Problem:** The front-end UI shouldn't have to manually interact with the `LibraryManager`, `AudioEngine`, `OutputDeviceManager`, and `PlaybackStrategy` simultaneously.
*   **Solution:** A `MusicPlayerFacade` (or `SpotifyApp` class) that provides simple methods like `playPlaylist("Rock 90s")` or `connectDevice("Bluetooth")`. It hides the complex subsystem wiring from the client.

### E. Factory Pattern (Object Creation)
*   **Solution:** An `AudioFactory` can be used to instantiate complex media objects, differentiating between local MP3 files, lossless FLAC audio, or remote stream URLs.

---

## 3. Architecture Overview (UML)

```mermaid
classDiagram
    class MusicPlayerFacade {
        +playSong(songId)
        +playPlaylist(playlistId)
        +changeOutputDevice(deviceId)
    }
    class MusicLibraryManager {
        <<Singleton>>
        +getSong(id)
        +getPlaylist(id)
    }
    class IPlaybackStrategy {
        <<interface>>
        +getNextSong(playlist): Song
    }
    class ShuffleStrategy {
        +getNextSong(playlist): Song
    }
    class IOutputDevice {
        <<interface>>
        +playAudio(data)
    }
    class BluetoothAdapter {
        +playAudio(data)
    }
    
    MusicPlayerFacade --> MusicLibraryManager : queries
    MusicPlayerFacade --> IPlaybackStrategy : uses
    MusicPlayerFacade --> IOutputDevice : routes audio
    IPlaybackStrategy <|.. ShuffleStrategy
    IOutputDevice <|.. BluetoothAdapter