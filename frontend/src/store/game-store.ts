import {create} from "zustand";
import {isAxiosError} from "axios";
import {gameService} from "../service/apiService.ts";

type GameStore = {
    gameId: number
    isAscending: boolean
    history: string[][]
    currentSquares: string[]
    currentMove: number
    winner: unknown
    winningMoves: number[]
    setGameId:(val:number) => void
    setIsAscending:(val:boolean) => void
    setIsAscendingAsync:(val:number) => void
    createGame:() => void
    handlePlay:(val:number, i:number) => void
    jumpTo:(val:number, move:number) => void
    setLoadedGame: (val:number)=> void
}

export const useGameStore = create <GameStore>((set)=>({
    isAscending:true,
    gameId:0,
    history: [new Array(9).fill(null)],
    currentSquares:new Array(9).fill(null),
    currentMove:0,
    winner:null,
    winningMoves:new Array(3).fill(null),
    setGameId: (val) => set({gameId:val}),
    setIsAscending: (val:boolean) => set({isAscending: val}),
    setIsAscendingAsync: async(gameId:number)=>{
        try{
            const response = await gameService.setAscending(gameId);
            set({isAscending: response.data.isAscending});
        }catch (err: unknown) {
            if (isAxiosError(err)) {
                if (err.response) {
                    console.log(err.response.data);
                }
            }
            console.error(err);
        }
    },
    createGame: async() => {
        try {
            const response = await gameService.createGame();
            set(useGameStore.getInitialState())
            set({gameId: response.data.id});
        }catch (err: unknown) {
            if (isAxiosError(err)) {
                if (err.response) {
                    console.log(err.response.data);
                }
            }
            console.error(err);
        }
    },

    handlePlay:async(gameID:number, i:number) => {
        try {
            const response = await gameService.addMove(gameID, i);
            set({currentMove: response.data.currentMove});
            set({winner: response.data.winner});
            set({winningMoves: response.data.winningMoves});
            set({history: response.data.history});
            set({currentSquares: response.data.history[response.data.history.length-1]});
        } catch (err: unknown) {
            if (isAxiosError(err)) {
                if (err.response) {
                    console.log(err.response.data);
                }
            }
            console.error(err);
        }
    },

    jumpTo:async(gameID:number, move:number) =>{
        try{
            const response = await gameService.jumpTo(gameID, move);
            set({currentMove: response.data.currentMove});
            set({winner: response.data.winner});
            set({winningMoves: response.data.winningMoves});
            set({currentSquares: response.data.currentSquares});
        }catch (err: unknown) {
            if (isAxiosError(err)) {
                if (err.response) {
                    console.log(err.response.data);
                }
            }
            console.error(err);
        }
    },

    setLoadedGame: async(gameID:number) => {
        try {
            const response = await gameService.setLoadedGame(gameID);
            set({gameId: response.data.id});
            set({currentMove: response.data.currentMove});
            set({winner: response.data.winner});
            set({winningMoves: response.data.winningMoves});
            set({currentSquares: response.data.currentSquares});
            set({history: response.data.history});
        }catch (err: unknown) {
            if (isAxiosError(err)) {
                if (err.response) {
                    console.log(err.response.data);
                }
            }
            console.error(err);
        }
    },

}));


