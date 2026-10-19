import Board from "./Board.tsx"
import Timeskip from "./Timeskip.tsx"
import PlayerStatus from "./PlayerStatus.tsx";
import {Box} from "@mui/material";

export default function Game() {

    return (
            <Box className="game" sx={{display:"flex", flexDirection:"column", justifyContent:"center", alignItems:"center"}}>
                <PlayerStatus/>
                <Box className="game-board" sx={{padding:"10px"}} >
                    <Board />
                </Box>
                    <Box className="game-info">
                        <Timeskip />
                    </Box>
            </Box>
    );
}