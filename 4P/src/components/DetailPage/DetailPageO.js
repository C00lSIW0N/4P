import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardO from "../DetailpageCard/DetailpageCardO";
import DetailpageCardOo from "../DetailpageCard/DetailpageCardOo";
import "../Detailpage.css"

const DetailpageO = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">시그니엘 부산</h1>
      <DetailpageCardO />
      <br/>
      <DetailpageCardOo />
    </div>
  );
};

export default DetailpageO;