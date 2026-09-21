
# 5 Essential Interview Questions: Day 18 (Music Player LLD)

### 1. How did you handle the "Shuffle" vs. "Sequential" playlist playback in your design?
**Answer:** I used the **Strategy Design Pattern**. Instead of hardcoding `if (mode == "shuffle")` inside the player, I created an `IPlaybackStrategy` interface. I then implemented `ShuffleStrategy` and `SequentialStrategy`. The player context simply delegates the responsibility of picking the next song to whichever strategy is currently injected, perfectly adhering to the Open/Closed Principle.

### 2. If you want to stream music to a new third-party Smart TV that has a completely different audio API, how does your architecture support it?
**Answer:** This is where the **Adapter Design Pattern** shines. The core music player relies on an `IOutputDevice` interface to route audio. To support the new Smart TV, I would simply write a `SmartTVAdapter` that implements `IOutputDevice` and wraps the third-party TV's specific API. The core application code wouldn't need to change at all.

### 3. Why did you use the Facade Pattern for the `MusicPlayerApp` class? Doesn't it violate the Single Responsibility Principle?
**Answer:** A Facade class is designed to hide the complexity of multiple subsystems (like the Audio Engine, Library Manager, and Device Manager) from the client (the UI). While it orchestrates many things, its single responsibility is to act as a unified gateway. In a real-world enterprise backend like Spring Boot, this would be equivalent to an Application Service or a Controller that orchestrates domain services.

### 4. Why is the `MusicLibraryManager` implemented as a Singleton?
**Answer:** The library manager acts as the in-memory cache and registry for all available songs, albums, and playlists. Having multiple instances of the library manager could lead to fragmented data, inconsistent states, and huge memory leaks. Making it a Singleton (and ensuring thread-safety) guarantees a single source of truth across the entire app.

### 5. If we wanted to add a feature where the user can undo their last action (e.g., undo "Skip Song" to go back to the previous track), which pattern would you use?
**Answer:** I would use the **Command Design Pattern**. By encapsulating actions like `SkipSong` or `PlayPlaylist` into Command objects with an `undo()` method, the system could maintain a stack of executed commands. If the user hits "Previous/Undo," we simply pop the last command off the stack and execute its reverse logic.