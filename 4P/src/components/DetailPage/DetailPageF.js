import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardF from "../DetailpageCard/DetailpageCardF";
import DetailpageCardFf from "../DetailpageCard/DetailpageCardFf";
import "../Detailpage.css"

const DetailpageF = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">그랜드 하얏트 제주</h1>
      <DetailpageCardF />
      <br/>
      <DetailpageCardFf />
    </div>
  );
};

export default DetailpageF;