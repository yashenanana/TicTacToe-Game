import {useGameStore} from "../store/game-store.ts";
import {useGameStatStore} from "../store/game-stats-store.ts";

export const GameHistoryService = async () => {
    try {
        await useGameStatStore.getState().refreshHistory();
        useGameStore.getState().setGameId(0);
    }catch(err) {
        console.log(err);
    }
}