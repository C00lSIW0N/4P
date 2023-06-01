import React, { useCallback, useEffect } from "react";
import { Link } from "react-router-dom";
import DetailpageCard from "../DetailpageCard";

const DetailpageE = () => {
  const GotoList = () => {
    window.location.href = "list";
  };

  const GotoPayment = () => {
    window.location.href = "payment";
  };

  return (
    <>
      <h1>상세 페이지</h1>
      <DetailpageCard />
      {/* <h1>
        <button onClick={GotoList}>장바구니 담기</button>
      </h1> */}
      <h1>
        <button onClick={GotoPayment}>결제</button>
      </h1>
    </>
  );
};

export default DetailpageE;