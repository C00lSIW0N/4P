import { Route, Routes } from "react-router-dom";
import Main from './components/Main';
import { getFirestore } from "./firebase_config.js";
import { useEffect } from "react";
import Title from "./components/Title";
import Payment from "./components/Payment";
import List from "./components/List";
import DetailPage from "./components/Detailpage";
// import Login from "./components/Login";
import "./App.css";


const App = () => {
  return(
    <div className="App">
      <div style={{ height: '30px' }}></div>
      <Title/>
      <div style={{ height: '50px' }}></div> {/* Title과 Main 사이의 여백 */}
      <Routes>
        <Route path='/' element={<Main />}></Route>
        <Route path='/payment' element={<Payment />}></Route>
        <Route path='/list' element={<List />}></Route>
        <Route path='/detailpage' element={<DetailPage />}></Route>
  </Routes>
  </div>
  );
};
  export default App;