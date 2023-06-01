import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
import DetailpageCardK from "../DetailpageCard/DetailpageCardK";
import DetailpageCardKk from "../DetailpageCard/DetailpageCardKk";
import "../Detailpage.css"

const DetailpageK = () => {

  return (
    <div className="detailpage-container">
      <h1 className="detailpage-heading">제주 샹그릴라 호텔</h1>
      <DetailpageCardK />
      <br/>
      <DetailpageCardKk />
    </div>
  );
};

export default DetailpageK;