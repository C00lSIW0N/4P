// import { Route, Routes } from "react-router-dom";
// import Main from './components/Main';
// import { getFirestore } from "./firebase_config.js";
// import { useEffect } from "react";
// import Title from "./components/Title";
// import Payment from "./components/Payment";
// import List from "./components/List";
// import DetailPage from "./components/Detailpage";
// // import Login from "./components/Login";
// import "./App.css";


// const App = () => {
//   return(
//     <div className="App">
//       <div style={{ height: '30px' }}></div>
//       <Title/>
//       <div style={{ height: '50px' }}></div> {/* Title과 Main 사이의 여백 */}
//       <Routes>
//         <Route path='/' element={<Main />}></Route>
//         <Route path='/payment' element={<Payment />}></Route>
//         <Route path='/list' element={<List />}></Route>
//         <Route path='/detailpage' element={<DetailPage />}></Route>
//   </Routes>
//   </div>
//   );
// };
//   export default App;

import { Route, Routes } from "react-router-dom";
import Main from './components/Main';
import { getFirestore } from "./firebase_config.js";
import { useEffect } from "react";
import Title from "./components/Title";
import Payment from "./components/Payment";
import List from "./components/List";
// import DetailPage from "./components/Detailpage"
import DetailPageA from "./components/DetailPage/DetailPageA";
import DetailPageB from "./components/DetailPage/DetailPageB";
import DetailPageC from "./components/DetailPage/DetailPageC";
import DetailPageD from "./components/DetailPage/DetailPageD";
import DetailPageE from "./components/DetailPage/DetailPageE";
import DetailPageF from "./components/DetailPage/DetailPageF";
import DetailPageG from "./components/DetailPage/DetailPageG";
import DetailPageH from "./components/DetailPage/DetailPageH";
import DetailPageI from "./components/DetailPage/DetailPageI";
import DetailPageJ from "./components/DetailPage/DetailPageJ";
import DetailPageK from "./components/DetailPage/DetailPageK";
import DetailPageL from "./components/DetailPage/DetailPageL";
import DetailPageM from "./components/DetailPage/DetailPageM";
import DetailPageN from "./components/DetailPage/DetailPageN";
import DetailPageO from "./components/DetailPage/DetailPageO";
import DetailPageP from "./components/DetailPage/DetailPageP";
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
        {/* <Route path='/detailpage' element={<DetailPage />}></Route>         */}
        <Route path='/detailpagea' element={<DetailPageA />}></Route>
        <Route path='/detailpageb' element={<DetailPageB />}></Route>
        <Route path='/detailpagec' element={<DetailPageC />}></Route>
        <Route path='/detailpaged' element={<DetailPageD />}></Route>
        <Route path='/detailpagee' element={<DetailPageE />}></Route>
        <Route path='/detailpagef' element={<DetailPageF />}></Route>
        <Route path='/detailpageg' element={<DetailPageG />}></Route>
        <Route path='/detailpageh' element={<DetailPageH />}></Route>
        <Route path='/detailpagei' element={<DetailPageI />}></Route>
        <Route path='/detailpagej' element={<DetailPageJ />}></Route>
        <Route path='/detailpagek' element={<DetailPageK />}></Route>
        <Route path='/detailpagel' element={<DetailPageL />}></Route>
        <Route path='/detailpagem' element={<DetailPageM />}></Route>
        <Route path='/detailpagen' element={<DetailPageN />}></Route>
        <Route path='/detailpageo' element={<DetailPageO />}></Route>
        <Route path='/detailpagep' element={<DetailPageP />}></Route>


  </Routes>
  </div>
  );
};
  export default App;