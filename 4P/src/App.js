import { Route, Routes } from "react-router-dom";
import Main from './components/Main';
import { getFirestore } from "./firebase_config.js";
import { useEffect } from "react";
import Title from "./components/Title";

import PaymentA from "./components/Payment/PaymentA";
import PaymentB from "./components/Payment/PaymentB";
import PaymentC from "./components/Payment/PaymentC";
import PaymentD from "./components/Payment/PaymentD";
import PaymentE from "./components/Payment/PaymentE";
import PaymentF from "./components/Payment/PaymentF";
import PaymentG from "./components/Payment/PaymentG";
import PaymentH from "./components/Payment/PaymentH";
import PaymentI from "./components/Payment/PaymentI";
import PaymentJ from "./components/Payment/PaymentJ";
import PaymentK from "./components/Payment/PaymentK";
import PaymentL from "./components/Payment/PaymentL";
import PaymentM from "./components/Payment/PaymentM";
import PaymentN from "./components/Payment/PaymentN";
import PaymentO from "./components/Payment/PaymentO";
import PaymentP from "./components/Payment/PaymentP";

import List from "./components/List";

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
import "./App.css";


const App = () => {
  return(
    <div className="App">
      <div style={{ height: '30px' }}></div>
      <Title/>
      <div style={{ height: '50px' }}></div> {/* Title과 Main 사이의 여백 */}
      <Routes>
        <Route path='/' element={<Main />}></Route>

        <Route path='/paymenta' element={<PaymentA />}></Route>
        <Route path='/paymentb' element={<PaymentB />}></Route>
        <Route path='/paymentc' element={<PaymentC />}></Route>
        <Route path='/paymentd' element={<PaymentD />}></Route>
        <Route path='/paymente' element={<PaymentE />}></Route>
        <Route path='/paymentf' element={<PaymentF />}></Route>
        <Route path='/paymentg' element={<PaymentG />}></Route>
        <Route path='/paymenth' element={<PaymentH />}></Route>
        <Route path='/paymenti' element={<PaymentI />}></Route>
        <Route path='/paymentj' element={<PaymentJ />}></Route>
        <Route path='/paymentk' element={<PaymentK />}></Route>
        <Route path='/paymentl' element={<PaymentL />}></Route>
        <Route path='/paymentm' element={<PaymentM />}></Route>
        <Route path='/paymentn' element={<PaymentN />}></Route>
        <Route path='/paymento' element={<PaymentO />}></Route>
        <Route path='/paymentp' element={<PaymentP />}></Route>

        <Route path='/list' element={<List />}></Route>
        
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