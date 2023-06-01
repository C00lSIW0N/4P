import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardD from "../DetailpageCard/DetailpageCardD";
import DetailpageCardDd from "../DetailpageCard/DetailpageCardDd";
import "../Detailpage.css"

const DetailpageD = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">롯데 호텔 제주</h1>
      <DetailpageCardD />
      <br/>
      <DetailpageCardDd />
    </div>
  );
};

export default DetailpageD;