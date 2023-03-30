import './App.css';
import NavPage from "./components/NavPage";
import { BrowserRouter } from 'react-router-dom';
import Main from './components/Main';

function App() {
  return (
      <BrowserRouter>
      <div>
        <Main></Main>
        <NavPage></NavPage>
      </div>
      </BrowserRouter>
  );
}

export default App;
