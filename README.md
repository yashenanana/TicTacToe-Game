# Tic Tac Toe
A local tic-tac-toe game built to familiarize myself with React and Spring Boot that builds on the official React
tutorial with additional features and functionalities.

## Features
- Turn-based XO gameplay between two local players
- Winning moves are highlighted
- Turn indicator to alert current player's turn and move
- Timetravel function: 
  - Allows players to resume playing from a previous turn
  - Ascending order button to control the order of displayed moves
- Persistent Data Storage : 
  - Games are automatically saved during gameplay
  - Allows players to load previous games in a read-only mode
  - Allows players to permanently delete previous games

## Structure & Technologies
- Frontend:
  - React
  - Axios for API calls to communicate with backend 
  - Zustand store for state management
  - MUI for standardized and simplified UI elements

- Backend:
  - Spring Boot
  - Log4j for logging purposes
  - Controller-Service-Model structure
  - Custom exception class
  - DTO objects for communication with frontend
  - Repository class for communication with server
  - Javadocs for documentation
- Database: H2 Server

## Setup
Run: 
```
cd frontend
yarn install
```

## Usage
### Gameplay
<img width="493" height="808" alt="Screenshot 2026-10-19 114910" src="https://github.com/user-attachments/assets/0f478c96-372e-44f6-bd2d-9abe2de50f46" />

### View History
<img width="1255" height="825" alt="Screenshot 2026-10-19 112430" src="https://github.com/user-attachments/assets/8c4fe953-d2f3-4320-9612-d9526770c399" />

