import {Button} from "@mui/material";
import {useGameStore} from "../../store/game-store.ts";

export default function AscendingOrderButton(){
    const isAscending = useGameStore((state)=>state.isAscending);
    const setIsAscendingInStore = useGameStore((state)=> state.setIsAscendingAsync)
    function handleSetAscending(){
        setIsAscendingInStore(useGameStore.getState().gameId);
    }
    return(
        <Button variant="contained" color="primary" onClick={()=>handleSetAscending()}>{isAscending ? 'descending' :'ascending'} order</Button>
    )
}
