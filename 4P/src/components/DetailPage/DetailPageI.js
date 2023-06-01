import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardI from "../DetailpageCard/DetailpageCardI";
import DetailpageCardIi from "../DetailpageCard/DetailpageCardIi";
import "../Detailpage.css"

const DetailpageI = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">라마다 프라자 제주</h1>
      <DetailpageCardI />
      <br/>
      <DetailpageCardIi />
    </div>
  );
};

export default DetailpageI;