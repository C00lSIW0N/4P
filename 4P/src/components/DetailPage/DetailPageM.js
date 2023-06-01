import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardM from "../DetailpageCard/DetailpageCardM";
import DetailpageCardMm from "../DetailpageCard/DetailpageCardMm";
import "../Detailpage.css"

const DetailpageM = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">호텔 프린스 서울</h1>
      <DetailpageCardM />
      <br/>
      <DetailpageCardMm />
    </div>
  );
};

export default DetailpageM;