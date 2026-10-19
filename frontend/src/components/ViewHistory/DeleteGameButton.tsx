import {Button} from "@mui/material";
import {useGameStatStore} from "../../store/game-stats-store.ts";
import {GameHistoryService} from "../../service/gameHistoryService.ts"


export default function DeleteGameButton({id}: Readonly<{ id: number }>) {
    const deleteGameInStore = useGameStatStore((state)=>state.deleteGame)
    const setGameHistoryInStore = GameHistoryService;

    async function deleteGame(id:number){
        console.log("deleteGame method called");
        deleteGameInStore(id);
        setGameHistoryInStore().then();
    }

    return(
            <Button onClick={() =>deleteGame(id)}>Delete Game</Button>
    )
}