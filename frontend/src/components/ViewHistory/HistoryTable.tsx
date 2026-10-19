import {Table, TableContainer, TableHead, TableRow, TableCell, TableBody} from "@mui/material";

import type Stats from "../../interfaces/Stats.ts";
import {useGameStatStore} from "../../store/game-stats-store.ts";
import LoadedGame from "./LoadedGame.tsx";
import LoadGameButton from "./LoadGameButton.tsx";
import DeleteGameButton from "./DeleteGameButton.tsx";
import {useGameStore} from "../../store/game-store.ts";
import type {ReactNode} from "react";

function renderArray(array:number[]){
    const arrayFormat:string[] = [];
    if(!array){
        arrayFormat.push('None')
        return arrayFormat
    }
    array.forEach((value:number) =>{
        arrayFormat.push(value.toString(), ', ');
    })
    arrayFormat.pop();
    return arrayFormat;
}

export default function HistoryTable(){

    const gameHistory = useGameStatStore((state)=>state.gameHistory);
    const gameId = useGameStore((state)=>state.gameId);

    function renderTable(){
        const code:ReactNode[] = [];
        gameHistory.forEach((game:Stats) => {
            const rowCode:ReactNode[] = [];
            rowCode.push(<TableCell>{game.winner? game.winner:'None'}</TableCell>,
                <TableCell>{renderArray(game.winningMoves)}</TableCell>, <TableCell>{game.currentMove}</TableCell>,
                <TableCell><LoadGameButton id={game.id}/></TableCell>, <TableCell><DeleteGameButton id={game.id}/></TableCell>)
            code.push(<TableRow key={game.id}>{rowCode}</TableRow>)
            if (gameId && gameId === game.id) {
                code.push(
                    <LoadedGame keyValue={game.id + 'loaded game'}/>
                )
            }
        })
        return code;
    }

    return (
        <TableContainer aria-label="simple table">
            <Table>
                <TableHead>
                    <TableRow key="header">
                        <TableCell>Winner</TableCell>
                        <TableCell>Winning&nbsp;Moves</TableCell>
                        <TableCell>Rounds</TableCell>
                        <TableCell>Options</TableCell>
                        <TableCell></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {renderTable()}
                </TableBody>
            </Table>
        </TableContainer>
    )
}