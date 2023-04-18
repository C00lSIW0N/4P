import { Route, Routes } from "react-router-dom";
import Main from './components/Main';
import { getFirestore } from "./firebase_config.js";
import { useEffect } from "react";
import Payment from "./components/Payment";
import Book from "./components/Book";
// import Login from "./components/Login";


const App = () => {
  return(
    <div className="App">
      {/* <Main /> */}
      <Routes>
        {/* <Route path='/' element={<Login />}></Route> */}
        <Route path='/' element={<Main />}></Route>
  {/* <Route path='/main' element={<Main />}/> */}
        <Route path='/payment' element={<Payment />}></Route>
        <Route path='/book' element={<Book />}></Route>
  {/* <Route path='*' element={<Error />}/> */}
  </Routes>
  </div>
  );
};
  export default App;

// import './App.css';
// import { firestore } from './firebase_config';
// import { useEffect } from 'react';

// function App() {

//   useEffect(() => {
//     const test = firestore.collection("test");
    
//     test.doc("hi").set({test: 'hello'});
//   });

//   return (
//     <div className="App">
//       firebase 확인해보기!
//     </div>
//   );
// }

// export default App;