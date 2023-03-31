// import NavPage from "./components/NavPage";
import { Route, Link } from "react-router-dom";
import Main from './components/Main';

function App() {
  return (
    <div>
      <ul>
      <li><Link to="/">newpage</Link></li>
      </ul>
      <Routes>
        <Route path="/" element={<Main />}></Route>
      </Routes>
    </div>
  );
}

export default App;
