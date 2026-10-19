import {Button, type ButtonProps} from "@mui/material";

export default function Square({ value, onSquareClick, buttonColor }: Readonly<{
    value: string;
    onSquareClick: () => void;
    buttonColor: ButtonProps['color'];
}>) {

    return (
        <Button className="square" onClick={onSquareClick} color={buttonColor} variant="contained" sx={{
            fontFamily: "sans-serif",
        }}>
            {value}
        </Button>
    );
}