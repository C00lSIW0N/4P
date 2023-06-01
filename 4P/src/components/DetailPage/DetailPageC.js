import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardC from "../DetailpageCard/DetailpageCardC";
import DetailpageCardCc from "../DetailpageCard/DetailpageCardCc";
import "../Detailpage.css"

const DetailpageC = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">메종 글래드 제주</h1>
      <DetailpageCardC />
      <br/>
      <DetailpageCardCc />
    </div>
  );
};

export default DetailpageC;