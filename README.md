# ♟️ Knight's Dungeon (Chess Maze)

A minimalist 2D puzzle game built natively for Android. This project gamifies chess mechanics (Rook and Knight movements) into a logic puzzle adventure.

> **Note:** This game was built as a fun educational project for my son (Grade 4) to practice logic and math skills.

![Game Screenshot](https://via.placeholder.com/300x600?text=Screenshot+Placeholder)

## 🎮 How to Play
The goal is simple: Navigate the board, avoid walls, and capture the Star to advance.

* **Level 1 (The Rook):** Move in straight lines (Horizontal/Vertical). Paths must be clear of walls.
* **Level 2 (The Knight):** Master the "L-Shape" jump (2 squares one way, 1 square the other). Knights can jump *over* walls!
* **Touch Controls:** Simply tap a square to move. If the move is valid according to chess rules, the piece will travel.

## ✨ Features
* **Native Android Performance:** Built entirely in Kotlin.
* **Custom Game Engine:** Uses a custom `GameView` and Android `Canvas` for drawing (No heavy game engines like Unity).
* **Math & Logic:** Teaches coordinate geometry and pathfinding logic.
* **Minimalist Design:** Clean, distraction-free interface.
* **Kid-Friendly:** Simple controls, no ads, and fully mute (parent-friendly!).

## 🛠️ Tech Stack
* **Language:** Kotlin
* **IDE:** Android Studio
* **Graphics:** Android 2D Canvas & Bitmaps
* **Architecture:** Single Activity with a custom View loop.

## 🚀 How to Run
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/YOUR_USERNAME/REPO_NAME.git](https://github.com/YOUR_USERNAME/REPO_NAME.git)
    ```
2.  Open **Android Studio**.
3.  Select **Open an Existing Project** and navigate to the folder.
4.  Wait for Gradle to sync.
5.  Connect your Android device or start an Emulator.
6.  Click **Run (▶️)**.

## 🔮 Future Plans
* [ ] Add Bishop and Queen levels.
* [ ] Create a level editor.
* [ ] Add a move counter (High Score system).

## 📄 License
This project is open source and available for educational purposes.
