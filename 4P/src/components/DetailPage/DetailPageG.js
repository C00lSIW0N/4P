import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardG from "../DetailpageCard/DetailpageCardG";
import DetailpageCardGg from "../DetailpageCard/DetailpageCardGg";
import "../Detailpage.css"

const DetailpageG = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">해비치 호텔&리조트</h1>
      <DetailpageCardG />
      <br/>
      <DetailpageCardGg />
    </div>
  );
};

export default DetailpageG;