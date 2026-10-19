import axios from "axios";

const BASE_URL = "http://localhost:8080";
const api = axios.create({
    baseURL: BASE_URL,
    headers: {
        'Content-Type': 'application/json'
    }
});

export const gameService = {
    createGame: () => api.post('/new-game'),
    addMove: (gameID: number, position: number) => api.post(`${gameID}/new-move`, {position: position}),
    setAscending: (gameID: number) => api.patch(`${gameID}/set-ascending`),
    jumpTo: (gameID: number, move: number) => api.patch(`${gameID}/jump-to`, {move: move}),
    setLoadedGame: (gameID: number) => api.get(`${gameID}/set-loaded-game`),
};

export const gameStatService = {
    refreshHistory: () => api.post("/refresh-history"),
    delete: (gameID: number) => api.delete(`${gameID}/delete`),
}