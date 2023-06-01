import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardB from "../DetailpageCard/DetailpageCardB";
import DetailpageCardBb from "../DetailpageCard/DetailpageCardBb";
import "../Detailpage.css"

const DetailpageB = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">파르나스 호텔 제주</h1>
      <DetailpageCardB />
      <br/>
      <DetailpageCardBb />
    </div>
  );
};

export default DetailpageB;