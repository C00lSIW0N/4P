// import NavPage from "./components/NavPage";
// import { Route, Routes } from "react-router-dom";
//  import Main from './components/MainPage';
// import { getFirestore } from "./firebase_config.js";
// import { useEffect } from "react";

// const App = () => {
//   // useEffect(()=> {
//   //   console.log(getFirestore);
//   // })
//   // return(
//   //   <div className="App">
//   //     firebase 확인해보기!
//   //   </div>
//   <Routes>
//   <Route path='/' element={<Main />}/>
//   <Route path='/main' element={<Main />}/>
//   {/* <Route path='*' element={<Error />}/> */}
// </Routes>
//   // );
//   // );
//   };
// export default App;

// import './App.css';
/*import { firestore } from './firebase_config';
import { useEffect } from 'react';

function App() {

  useEffect(() => {
    const test = firestore.collection("test");
    
    test.doc("hi").set({test: 'hello'});
  });

  return (
    <div className="App">
      firebase 확인해보기!
    </div>
  );
}

export default App;
*/

import "./App.css";
import React from "react";
//import { RecoilRoot } from "recoil";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import EyTitle from "./components/EyTitle";
import ReMainPage from "./pages/ReMainPage";
import ReservationPage from "./pages/ReservationPage";
import CancellationPage from "./pages/CancellationPage";
import ConfirmationPage from "./pages/ConfirmationPage";


function App() {
  return (
    <div className="App">
      <EyTitle />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<ReMainPage/>} />  
          <Route path="/remain" element={<ReMainPage/>} />
          <Route path="/reservation" element={<ReservationPage/>}/>
          <Route path="/confirmation" element={<ConfirmationPage/>}/>
          <Route path="/cancellation" element={<CancellationPage/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App;