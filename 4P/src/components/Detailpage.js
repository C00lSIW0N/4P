import React, { useCallback, useEffect } from "react";
import { Link } from "react-router-dom";
import { firestore } from "../firebase_config";

const Detailpage = () => {

 const GotoList = () => {
    window.location.href = 'list'
 }
 const GotoPayment = () => {
      // const sample = firestore.collection("숙소");
      // sample.doc("호텔").set({호텔: "호텔1"}, {호텔: "호텔2"}, {호텔: "호텔3"}, {호텔: "호텔4"});
      // sample.doc("펜션").set({펜션: "펜션1", 펜션: "펜션2", 펜션: "펜션3", 펜션: "펜션4"});
      // sample.doc("게하").set({게하: "게하1", 게하: "게하2", 게하: "게하3", 게하: "게하4"});
   window.location.href = 'payment'
   }
 return(
    <>
    <h1>상세 페이지</h1>
    <img src="img/logo192.png" />
    <h1><button onClick={GotoList}>장바구니 담기</button></h1>
    <h1><button onClick={GotoPayment}>결제</button></h1>
    </>
 );
 }
export default Detailpage;