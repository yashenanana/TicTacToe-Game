import Board from "../NewGame/Board.tsx";
import MoveStatus from "../NewGame/MoveStatus.tsx";
import MoveButton from "../NewGame/MoveButton.tsx";
import {TableCell, TableRow} from "@mui/material";

interface LoadedGameProps {
    keyValue: string
}

export default function LoadedGame({keyValue}: Readonly<LoadedGameProps>) {

    return (
        <TableRow key={keyValue}>
            <TableCell>
                <Board/>
                <MoveStatus/>
            </TableCell>
            <TableCell>
                <MoveButton/>
            </TableCell>
        </TableRow>
    )
}