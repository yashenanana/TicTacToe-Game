import {Typography} from "@mui/material";
import {useGameStore} from "../../store/game-store.ts";

export default function MoveStatus(){

    const currentMove = useGameStore((state) => state.currentMove);

    return(
        <div>
            <Typography>You are at move #{currentMove} </Typography>
        </div>
    )
}