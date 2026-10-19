import Game from "./NewGame/Game.tsx";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import NavBar from "./NavBar.tsx";
import HistoryTable from "./ViewHistory/HistoryTable.tsx";
import {Box, Container} from "@mui/material";

export default function App() {
    return (
        <Box
            component={Container} sx={{display:"flex", flexDirection:"column", justifyContent:"center", alignItems:"center"}}
            >
            <BrowserRouter>
                <NavBar/>
                <Routes>
                    <Route path="/play" element={<Game/>}></Route>
                    <Route path="/history" element={<HistoryTable/>}></Route>
                </Routes>
            </BrowserRouter>
        </Box>
    );
}