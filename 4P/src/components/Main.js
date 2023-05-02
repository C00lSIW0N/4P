import React from "react";
import { Link } from "react-router-dom";
import { firestore } from "../firebase_config";
import { firebase } from "../firebase_config";


const Main = () => {
  const GotoDetail = () => {
    window.location.href = "detailpage"
  }
  return (
    <>
      <h1>메인</h1>
      <button><img src="/img/logo192.png" onClick={GotoDetail}/></button>
    </>
  );
}

export default Main;