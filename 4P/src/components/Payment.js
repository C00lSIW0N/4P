import React from "react";
import { Link } from "react-router-dom";
 
function Payment () {
  function clickTopay(e) {
    window.location.href = 'pay'
  }
  return ( 
    <div onClick={clickTopay}>
      <h1>결제하기</h1>
    </div>
  );
  function Pay(){
      window.alert("결제완료!");
  }
}

 
export default Payment;