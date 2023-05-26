// import React, { useCallback, useEffect } from "react";
// import { Link } from "react-router-dom";
// import { firestore } from "../firebase_config";

// const Detailpage = () => {

//  const GotoList = () => {
//     window.location.href = 'list'
//  }
//  const GotoPayment = () => {
//       window.location.href = 'payment'
//    }
//  return(
//     <>
//     <h1>상세 페이지</h1>
//     <img src="img/logo192.png" />
//     <h1><button onClick={GotoList}>장바구니 담기</button></h1>
//     <h1><button onClick={GotoPayment}>결제</button></h1>
//     </>
//  );
//  }
// export default Detailpage;








// import React, { useState, useEffect } from "react";
// import sampleA from '../images/sampleA.jpg';
// import sampleB from '../images/sampleB.jpg';
// import sampleC from '../images/sampleC.jpg';

// const DetailPage = ({ cardTitle }) => {
//   const [currentImageIndex, setCurrentImageIndex] = useState(0);
//   const images = [sampleA, sampleB, sampleC];
//   const imageRotationInterval = 2000;

//   useEffect(() => {
//     const imageRotationTimer = setInterval(() => {
//       setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
//     }, imageRotationInterval);

//     return () => {
//       clearInterval(imageRotationTimer);
//     };
//   }, []);

//   const GotoList = () => {
//     window.location.href = 'list';
//   };

//   const GotoPayment = () => {
//     window.location.href = 'payment';
//   };

//   return (
//     <div className="detail-page">
//       <h1>{cardTitle}</h1>
//       <div className="image-container">
//         <div className="image-wrapper">
//           <img
//             className="sliding-image"
//             src={images[currentImageIndex]}
//             alt="호텔 이미지"
//           />
//         </div>
//       </div>
//       <div className="info-container">
//         <h2>상세 정보</h2>
//         {/* 상세 정보를 여기에 표시 */}
//       </div>
//       <div className="price-container">
//         <h2>가격</h2>
//         {/* 가격 정보를 여기에 표시 */}
//       </div>
//       <div className="payment-container">
//         <button className="payment-button" onClick={GotoPayment}>결제</button>
//       </div>
//     </div>
//   );
// };

// export default DetailPage;




import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom"; // 추가
import sampleA from '../images/sampleA.jpg';
import sampleB from '../images/sampleB.jpg';
import sampleC from '../images/sampleC.jpg';

const DetailPage = () => { // 매개변수 제거
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleA, sampleB, sampleC];
  const imageRotationInterval = 2000;

  useEffect(() => {
    const imageRotationTimer = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, imageRotationInterval);

    return () => {
      clearInterval(imageRotationTimer);
    };
  }, []);

  const { cardTitle } = useParams(); // URL에서 cardTitle을 읽어옴

  const GotoList = () => {
    window.location.href = 'list';
  };

  const GotoPayment = () => {
    window.location.href = 'payment';
  };

  return (
    <div className="detail-page">
      <h1>{cardTitle}</h1>
      <div className="image-container">
        <div className="image-wrapper">
          <img
            className="sliding-image"
            src={images[currentImageIndex]}
            alt="호텔 이미지"
          />
        </div>
      </div>
      <div className="info-container">
        <h2>상세 정보</h2>
        {/* 상세 정보를 여기에 표시 */}
      </div>
      <div className="price-container">
        <h2>가격</h2>
        {/* 가격 정보를 여기에 표시 */}
      </div>
      <div className="payment-container">
        <button className="payment-button" onClick={GotoPayment}>결제</button>
      </div>
    </div>
  );
};

export default DetailPage;

