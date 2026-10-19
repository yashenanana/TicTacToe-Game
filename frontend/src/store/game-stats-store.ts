import {create} from "zustand";
import type Stats from "../interfaces/Stats.ts";
import {gameStatService} from "../service/apiService.ts";
import {isAxiosError} from "axios";


type GameStatStore ={
    gameHistory: Stats[]
    refreshHistory: ()=> void
    deleteGame:(val:number)=> void
}
export const useGameStatStore = create<GameStatStore>((set) => ({
    gameHistory: [],

    refreshHistory: async() =>{
        try {
            const response = await gameStatService.refreshHistory();
            set({
                gameHistory: response.data,
            });
        } catch (err: unknown) {
            if (isAxiosError(err) && err.response) {
                console.log(err.response.data);
            }
            console.error(err);
        }
    },

    deleteGame: async(gameId:number) =>{
        try{
            await gameStatService.delete(gameId);
        }catch (err: unknown) {
            if (isAxiosError(err)) {
                if (err.response) {
                    console.log(err.response.data);
                }
            }
            console.error(err);
        }
    }
}));