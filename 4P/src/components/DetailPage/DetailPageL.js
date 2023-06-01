import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardL from "../DetailpageCard/DetailpageCardL";
import DetailpageCardLl from "../DetailpageCard/DetailpageCardLl";
import "../Detailpage.css"

const DetailpageL = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">히든 클리프 호텔&네이처</h1>
      <DetailpageCardL />
      <br/>
      <DetailpageCardLl />
    </div>
  );
};

export default DetailpageL;