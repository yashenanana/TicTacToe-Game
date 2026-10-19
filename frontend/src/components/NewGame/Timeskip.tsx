import MoveStatus from "./MoveStatus.tsx"
import AscendingOrderButton from "./AscendingOrderButton.tsx"
import MoveButton from "./MoveButton.tsx"
import {Box} from "@mui/material";

export default function Timeskip() {
    return(
        <Box className="game-info" sx={{display:"flex", flexDirection:"column", justifyContent:"center", alignItems:"center", margin:"4px"}}>
            <MoveStatus />
            <MoveButton />
            <AscendingOrderButton />
        </Box>
    )
}