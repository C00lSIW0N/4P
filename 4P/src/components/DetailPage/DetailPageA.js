import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardA from "../DetailpageCard/DetailpageCardA";
import DetailpageCardAa from "../DetailpageCard/DetailpageCardAa";
import "../Detailpage.css"

const DetailpageA = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">제주 신라호텔</h1>
      <DetailpageCardA />
      <br/>
      <DetailpageCardAa />
    </div>
  );
};

export default DetailpageA;