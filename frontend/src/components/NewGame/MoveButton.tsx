import {Box, Button} from "@mui/material";
import {useGameStore} from "../../store/game-store.ts";

export default function MoveButton()
{
    const history = useGameStore((state)=>state.history);
    const jumpTo = useGameStore((state) => state.jumpTo)
    const isAscending = useGameStore((state)=>state.isAscending);
    const gameId = useGameStore((state)=>state.gameId);
    const usedHistory = isAscending ? history : Array.from(history).reverse();
    const moves = usedHistory.map((_i: unknown, move: number) => { //counters to iterate through history; bc matrix
        let description;
        const key:string = move.toString() + "move" + gameId.toString();
        const condition = isAscending ? move : usedHistory.length - 1 - move;

        if (move > 0 && move < history.length) {
            description = 'Go to move #' + condition;

        } else if (move == history.length - 1) {
            return;

        } else {
            description = isAscending ? 'Go to start' : 'Go to end';
        }

        return (
            <li key={key}>
                <Button style={{fontSize: 12}} variant="outlined" color="success"
                        onClick={() => handleJump(move)}>{description}</Button>
            </li>
        )
    })

    function handleJump(move:number){
        jumpTo(gameId,move);
    }

    return(
        <Box sx={{display:"flex", flexDirection:"column", justifyContent:"center", textAlign:"center"}}>
            <ul>{moves}</ul>
        </Box>
    )
}