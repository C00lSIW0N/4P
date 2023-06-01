import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardJ from "../DetailpageCard/DetailpageCardJ";
import DetailpageCardJj from "../DetailpageCard/DetailpageCardJj";
import "../Detailpage.css"

const DetailpageJ = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">에코랜드 호텔</h1>
      <DetailpageCardJ />
      <br/>
      <DetailpageCardJj />
    </div>
  );
};

export default DetailpageJ;