import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import TasksContextProvider from './Context/TasksContext/TasksContextProvider.tsx'

createRoot(document.getElementById('root')!).render(
  <TasksContextProvider>
    <App />
  </TasksContextProvider>,
)
