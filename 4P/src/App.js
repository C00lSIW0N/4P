// import NavPage from "./components/NavPage";
// import { Route, Routes } from "react-router-dom";
// import Main from './components/MainPage';
import { getFirestore } from "./firebase-config.js";
import { useEffect } from "react";

const App = () => {
  useEffect(()=> {
    console.log(getFirestore);
  })
  return(
    <div className="App">
      firebase 확인해보기!
    </div>
//   <Routes>
//   <Route path='/' element={<Main />}/>
//   <Route path='/main' element={<Main />}/>
//   {/* <Route path='*' element={<Error />}/> */}
// </Routes>
//   );
  );
  };
export default App;

