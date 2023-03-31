// import NavPage from "./components/NavPage";
import { Route, Routes } from "react-router-dom";
import Main from './components/MainPage';

const App = () => (
  <Routes>
  <Route path='/' element={<Main />}/>
  <Route path='/main' element={<Main />}/>
  {/* <Route path='*' element={<Error />}/> */}
</Routes>

);
export default App;