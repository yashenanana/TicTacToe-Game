import {NavLink} from "react-router-dom";
import {Box, Button} from "@mui/material";
import {useGameStore} from "../store/game-store.ts";
import {GameHistoryService} from "../service/gameHistoryService.ts"

const buttonStyle = {color: '#f5dbbb', backgroundColor:'#8b4513', borderRadius: 3,padding:8}
export default function NavBar() {
    const setGameId = useGameStore((state)=>state.setGameId);
    const setGameHistoryInStore = GameHistoryService;
    const createGameInStore = useGameStore((state)=>state.createGame )

    function loadGameHistory(){
        setGameHistoryInStore().then();
        setGameId(-1);
    }

    function createGame(){
        createGameInStore();
    }

    return (

        <nav>
            <Box sx={{
                display: 'flex',
                gap:1,
                marginBottom:2
            }}>
                <Button sx={{
                    padding: 1,
                }} component={NavLink} to="/play" variant="contained" style={buttonStyle} onClick={()=>createGame()}>
                        Play
                </Button >
                <p></p>
                <Button sx={{
                    padding: 1,
                }} component={NavLink} to="/history" variant="contained" style={buttonStyle} onClick={()=>loadGameHistory() }>
                        History
                </Button>
            </Box>

        </nav>

    )
}