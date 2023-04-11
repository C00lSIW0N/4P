// import React from "react";
// import { Link } from "react-router-dom";
 
// function Payment () {
//   function clickTopay(e) {
//     window.location.href = 'pay'
//   }
//   return ( 
//     <div onClick={clickTopay}>
//       <h1>결제하기</h1>
//     </div>
//   );
//   function Pay(){
//       window.alert("결제완료!");
//   }
// }

 
// export default Payment;
// import React, { useEffect } from 'react';

// function Payment () {
  
	
// 	useEffect(() => {
//         const jquery = document.createElement("script");
//         jquery.src = "https://code.jquery.com/jquery-1.12.4.min.js";
//         const iamport = document.createElement("script");
//         iamport.src = "https://cdn.iamport.kr/v1/iamport.js";
//         document.head.appendChild(jquery);
//         document.head.appendChild(iamport);
//         return () => {
//         	document.head.removeChild(jquery);
//         	document.head.removeChild(iamport);
//         }
//     }, []);

//     const onClickPayment = () =>{
// 		var IMP = window.IMP;
//     	IMP.init('imp02656648');

//     IMP.request_pay ({
//     		pg: 'kakopay.TC0ONETIME',
//     		pay_method: 'card',
//     		merchant_uid: `mid_${new Date().getTime()}`,
//     		name: '테스트',
//     		amount: '1000',
//     		custom_data: {
//                 name: '부가정보',
//                 desc: '세부 부가정보'
//     		},
//     		buyer_name: '홍길동',
//     		buyer_tel: '027604114',
//     		buyer_email: 'hansung@hansung.ac.kr',
//     		buyer_addr: '성북구 삼선교로 16길 116',
//     		buyer_postalcode: '02876'
//     	},
//     function callback (rsp) {
//     	if (rsp.success){
//     		alert('결제 성공');
//     	} else {
//     		alert(`결제 실패: ${rsp.error_msg}`);
//     	}
//     });
// 	const response = () => {
// 		if(response.success) {
// 			alert('결제 성공');
// 		}
// 		else{
//			alert('결제 실패: ${error_msg}');
// 	}
// 	}

//     return (
//         <>
// 		{onClickPayment()};
//         </>
//     );
// }
// }
// export default Payment;
