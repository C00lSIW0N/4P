      import React from "react";
      import { useEffect } from "react";
      import { Link } from "react-router-dom";
      import { firestore } from "../firebase_config";
      
      const Payment = (effect, deps) => {

        useEffect(() => {
          const jquery = document.createElement("script");
          jquery.src = "https://code.jquery.com/jquery-1.12.4.min.js";
          const iamport = document.createElement("script");
          iamport.src = "https://cdn.iamport.kr/js/iamport.payment-1.1.7.js";
          document.head.appendChild(jquery);
          document.head.appendChild(iamport);

          return () => {
            document.head.removeChild(jquery);
            document.head.removeChild(iamport);
          }},
        []);
          const Pay_toss = () => {
            const sample1 = firestore.collection("user");;
            sample1.doc("user1@user.com").set({숙소: "호텔1", 예약날짜: "friday11"});
            sample1.doc("user2@user.com").set({숙소: "펜션1", 예약날짜: "monday14"});
            sample1.doc("user3@user.com").set({숙소: "게하1", 예약날짜: "thursday18"});
            const { IMP } = window;
          IMP.init('imp02656648');

            const data = {
              pg: 'tosspay',
              pay_method: 'card',
              merchant_uid: `mid_${new Date().getTime()}`,
              name: '토스페이 테스트',
              amount: '1000',
              custom_data: {
                  name: '부가정보',
                  desc: '세부 부가정보'
              },
              buyer_name: '홍길동',
              buyer_tel: '027604114',
              buyer_email: 'hansung@hansung.ac.kr',
              buyer_addr: '성북구 삼선교로 16길 116',
              buyer_postalcode: '02876'
            };
            IMP.request_pay(data,callback);
          }
          const Pay_kg = () => {
            const { IMP } = window;
            IMP.init('imp02656648');

            const data = {
              pg: 'html5_inicis',
              pay_method: 'card',
              merchant_uid: `mid_${new Date().getTime()}`,
              name: '결제 테스트',
              amount: '1000',
              custom_data: {
                  name: '부가정보',
                  desc: '세부 부가정보'
              },
                buyer_name: '홍길동',
                buyer_tel: '027604114',
                buyer_email: 'hansung@hansung.ac.kr',
                buyer_addr: '성북구 삼선교로 16길 116',
                buyer_postalcode: '02876'
            };
              IMP.request_pay(data,callback);
            }
          const Pay_kakao = () => {
            // const sample = firestore.collection("식당");
            // sample.doc("메뉴").set({식당: "아웃백"});
            
            const { IMP } = window;
            IMP.init('imp02656648');
        
              const data = {
                pg: 'kakaopay',
                pay_method: 'card',
                merchant_uid: `mid_${new Date().getTime()}`,
                name: '카카오페이 테스트',
                amount: '1000',
                custom_data: {
                    name: '부가정보',
                    desc: '세부 부가정보'
                },
                buyer_name: '홍길동',
                buyer_tel: '027604114',
                buyer_email: 'hansung@hansung.ac.kr',
                buyer_addr: '성북구 삼선교로 16길 116',
                buyer_postalcode: '02876'
              };
              IMP.request_pay(data,callback);
            }
          const Pay_payco = () => {
            // const sample = firestore.collection("비행기");
            // sample.doc("비행기").set({비행기: "대한항공"});
              const { IMP } = window;
              IMP.init('imp02656648');
          
                const data = {
                  pg: 'payco',
                  pay_method: 'card',
                  merchant_uid: `mid_${new Date().getTime()}`,
                  name: '페이코 테스트',
                  amount: '1000',
                  custom_data: {
                      name: '부가정보',
                      desc: '세부 부가정보'
                  },
                  buyer_name: '홍길동',
                  buyer_tel: '027604114',
                  buyer_email: 'hansung@hansung.ac.kr',
                  buyer_addr: '성북구 삼선교로 16길 116',
                  buyer_postalcode: '02876'
                };
                IMP.request_pay(data,callback);
              }      
            const callback = (rsp) => {
              const {success, error_msg, imp_uid, merchant_uid, pay_method, paid_amount, status} = rsp;
            if (success){
              alert('결제 성공');
            } else {
              alert(`결제 실패: ${error_msg}`);
            }
          }
        // const response = () => {
        // 	if(response.success) {
        // 		alert('결제 성공');
        // 	}
        // 	else{
        // 		alert('결제 실패: ${error_msg}');
        // }
        // }
        return (
          <>
          <h1>결제 페이지</h1>
          <img src="img/logo192.png" />
          <br />
          <button onClick={Pay_toss}>토스페이로 결제하기</button>
        <br /><br />
          <button onClick={Pay_kg}>신용카드로 결제하기</button>
        <br /><br />
          <button onClick={Pay_kakao}>카카오로 결제하기</button>
        <br /><br />
          <button onClick={Pay_payco}>페이코로 결제하기</button>  
          </>
      );
  }

      
      export default Payment;