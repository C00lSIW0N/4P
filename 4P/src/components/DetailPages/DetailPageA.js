import React, { useCallback, useEffect } from "react";
import { Link } from "react-router-dom";
import { firestore } from "../firebase_config";

const DetailPageA = () => {
  return (
    <>
      <h1>상세 페이지</h1>
      <img src="img/logo192.png" />
      <h1>
        <Link to="/list">장바구니 담기</Link>
      </h1>
      <h1>
        <Link to="/payment">결제</Link>
      </h1>
    </>
  );
};

export default DetailPageA;