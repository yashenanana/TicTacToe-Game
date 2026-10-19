import Square from "./Square.tsx"
import {useGameStore} from "../../store/game-store.ts";
import type {ButtonProps} from "@mui/material";

export default function Board() {

    const winningMoves = useGameStore((state)=> state.winningMoves)
    const currentSquares = useGameStore((state)=> state.currentSquares)
    const id = useGameStore((state)=> state.gameId)
    const handlePlay = useGameStore((state)=> state.handlePlay)

    function onPlay(m:number){
        handlePlay(id, m);
    }

    // html elemente werden in arrays gespeichert
    function renderBoard(){
        const rows = 3;
        const cols = 3;
        const board = [];
        let counter = 0;
        for(let i=0; i<cols; i++){
            const row =[];
            for(let j=0; j<rows;j++){
                const m= i*cols+j;

                //highlight winning squares
                let color:ButtonProps["color"] = "primary" ;
                if(winningMoves) {
                    if(m==winningMoves[counter]){
                        color="success";
                        counter+=1;
                    }
                    row.push(<Square key={m} value={currentSquares[m]} onSquareClick={() => onPlay(m)} buttonColor={color} />);
                }else {
                    row.push(<Square key={m} value={currentSquares[m]} onSquareClick={() => onPlay(m)} buttonColor={color}/>);
                }
            }
            board.push(
                <div className="board-row" key={i}>
                    {row}
                </div>
            );
        }
        return board;
    }

    return (
        <>
            {renderBoard()}
        </>
    );
}