import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardP from "../DetailpageCard/DetailpageCardP";
import DetailpageCardPp from "../DetailpageCard/DetailpageCardPp";
import "../Detailpage.css"

const DetailpageP = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">파라다이스 호텔 부산</h1>
      <DetailpageCardP />
      <br/>
      <DetailpageCardPp />
    </div>
  );
};

export default DetailpageP;