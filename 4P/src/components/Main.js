import React from "react";
import { Link } from "react-router-dom";
import { firestore } from "../firebase_config";
import { firebase } from "../firebase_config";


const Main = () => {
  return (
    <>
      <h1>메인</h1>
      <h1><Link to='payment'>결제 페이지</Link></h1>
    </>
  );
}

export default Main;