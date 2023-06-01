import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardE from "../DetailpageCard/DetailpageCardE";
import DetailpageCardEe from "../DetailpageCard/DetailpageCardEe";
import "../Detailpage.css"

const DetailpageE = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">신라스테이 제주</h1>
      <DetailpageCardE />
      <br/>
      <DetailpageCardEe />
    </div>
  );
};

export default DetailpageE;