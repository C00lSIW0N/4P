import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardN from "../DetailpageCard/DetailpageCardN";
import DetailpageCardNn from "../DetailpageCard/DetailpageCardNn";
import "../Detailpage.css"

const DetailpageN = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">파라스파라 서울</h1>
      <DetailpageCardN />
      <br/>
      <DetailpageCardNn />
    </div>
  );
};

export default DetailpageN;