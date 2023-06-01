import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardH from "../DetailpageCard/DetailpageCardH";
import DetailpageCardHh from "../DetailpageCard/DetailpageCardHh";
import "../Detailpage.css"

const DetailpageH = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">휘닉스 제주 섭지코지</h1>
      <DetailpageCardH />
      <br/>
      <DetailpageCardHh />
    </div>
  );
};

export default DetailpageH;