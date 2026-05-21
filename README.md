# JBlackJack

Java implementation of the classic Blackjack card game developed using the MVC (Model-View-Controller) architectural pattern and the Observer-Observable design pattern.

The project features:
- Playable Blackjack game with bots and dealer AI
- GUI with custom graphics and sounds
- Persistent player profile and statistics
- Modular architecture divided into Model, View, and Controller

---

# Features

- Blackjack gameplay
- Up to 3 AI bots
- Custom player profile with avatar selection
- Persistent statistics system
- Sound effects and background music
- Card rendering with image assets
- Dealer AI logic
- Replay system
- MVC architecture
- Observer pattern for automatic GUI updates
- Singleton usage for shared resources

---

# Architecture

## Model

Contains the core game logic and data structures.

### Main Classes

| Class | Description |
|---|---|
| `Mazzo` | Deck manager using Singleton pattern |
| `Carta` | Represents a playing card |
| `Mano` | Represents a player's hand |
| `Giocatore` | Base player class implementing Observable |
| `Bot` | AI-controlled player |
| `Dealer` | Dealer AI logic |
| `Profilo` | Player profile and statistics |
| `Partita` | Main game logic and victory conditions |

### Design Patterns Used

#### Singleton
- `Mazzo`
- `Profilo`
- `AudioManager`

#### Observer-Observable
Used to automatically update the GUI when the game state changes.

---

## View

Handles the graphical user interface.

### Main Views

| View | Description |
|---|---|
| `MenuView` | Main menu |
| `CreazioneProfiloView` | Profile creation screen |
| `StatisticheView` | Player statistics |
| `SceltaBotView` | Bot selection menu |
| `GameView` | Main gameplay screen |
| `AudioManager` | Sound and music manager |

### GUI Features

- Custom backgrounds
- Card textures
- Sound effects
- Background music
- Dynamic updates through Observer pattern

---

## Controller

Connects Model and View by handling user interactions.

### Main Controllers

| Controller | Description |
|---|---|
| `MenuController` | Main menu interactions |
| `CreazioneProfiloController` | Profile creation logic |
| `StatisticheController` | Statistics screen logic |
| `SceltaBotController` | Bot selection logic |
| `GameController` | Gameplay controls |

---

# Gameplay

The player can:

- Draw cards (`Pesca`)
- Stand (`Stai`)
- Replay the match (`Rigioca`)
- Return to the main menu

The game supports:
- Human player
- Dealer
- Up to 3 AI bots

At the end of the round, the game automatically determines:
- Win
- Loss
- Draw

---

# Profile System

Profiles store:

- Username
- Avatar
- Level
- Games played
- Wins
- Losses
- Draws

Profiles are saved locally in a `.txt` file and automatically loaded at startup.

---

# Technologies Used

- Java
- Java Swing
- MVC Architecture
- Observer Pattern
- Singleton Pattern
- Java Streams API

---

# Authors

Developed as a university project for practicing:
- Object-Oriented Programming
- Design Patterns
- Java GUI development
- Software architecture principles
