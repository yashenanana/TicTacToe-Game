import {Typography} from "@mui/material";
import {useGameStore} from "../../store/game-store.ts";

export default function PlayerStatus(){
    const winner = useGameStore((state)=>state.winner);
    const xIsNext:boolean = useGameStore((state)=>state.currentMove)%2===0;
    let playerStatus:string;
    if(winner){
        playerStatus = 'Winner is ' + winner + ' !';
    }else{
        playerStatus = 'Next player is ' + (xIsNext ? 'X' : 'O');
    }

    return(
        <div className="playerStatus">
            <Typography>{playerStatus} </Typography>
        </div>
    )
}