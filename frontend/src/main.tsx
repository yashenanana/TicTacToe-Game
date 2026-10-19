import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './components/App.tsx'
import {createTheme, ThemeProvider} from "@mui/material";

const theme = createTheme({
    palette:{
        primary:{
            main: "#f5dbbb",
        },
        secondary:{
            main:"#E78F50",
        },
        success:{
            main:"#8B4513",
        }
    },
    typography:{
        htmlFontSize: 12,
    }
})
createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <ThemeProvider theme={theme}>
          <App />
      </ThemeProvider>
  </StrictMode>,
)
