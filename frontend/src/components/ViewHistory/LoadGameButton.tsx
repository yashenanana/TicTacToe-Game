import {Button} from "@mui/material";
import {useGameStore} from "../../store/game-store.ts";

export default function LoadGameButton({id}: Readonly<{ id: number }>){
    const setLoadedGame = useGameStore((state)=>state.setLoadedGame);
    const setIsAscending = useGameStore((state)=>state.setIsAscending);
    const setId = useGameStore((state)=>state.setGameId);

    function loadGame(id:number){
        console.log("loadGame method called:" );
        setLoadedGame(id);
        setId(id);
        setIsAscending(true);
    }
    return(
            <Button onClick={() =>loadGame(id)}>Load Game</Button>
    )
}