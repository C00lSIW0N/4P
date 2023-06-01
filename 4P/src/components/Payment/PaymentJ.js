import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import { firestore } from "../../firebase_config";
import logoToss from "../../images/logo_toss.png";
import logoCreditCard from "../../images/logo_credit_card.png";
import logoKakao from "../../images/logo_kakao.png";
import logoPayco from "../../images/logo_payco.png";
import "../Payment.css";
import PaymentCardJ from "../PaymentCard/PaymentCardJ";



const PaymentJ = () => {
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
    };
  }, []);

  const Pay_toss = () => {
    const sample1 = firestore.collection("user");
    sample1.doc("user1@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    sample1.doc("user2@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    sample1.doc("user3@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    const { IMP } = window;
    IMP.init("imp02656648");

    const data = {
      pg: "tosspay",
      pay_method: "card",
      merchant_uid: `mid_${new Date().getTime()}`,
      name: "토스페이 테스트",
      amount: "1000",
      custom_data: {
        name: "부가정보",
        desc: "세부 부가정보",
      },
      buyer_name: "홍길동",
      buyer_tel: "027604114",
      buyer_email: "hansung@hansung.ac.kr",
      buyer_addr: "성북구 삼선교로 16길 116",
      buyer_postalcode: "02876",
    };
    IMP.request_pay(data, callback);
  };

  const Pay_kg = () => {
    const sample1 = firestore.collection("user");
    sample1.doc("user1@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    sample1.doc("user2@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    sample1.doc("user3@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    const { IMP } = window;
    IMP.init("imp02656648");

    const data = {
      pg: "html5_inicis",
      pay_method: "card",
      merchant_uid: `mid_${new Date().getTime()}`,
      name: "결제 테스트",
      amount: "1000",
      custom_data: {
        name: "부가정보",
        desc: "세부 부가정보",
      },
      buyer_name: "홍길동",
      buyer_tel: "027604114",
      buyer_email: "hansung@hansung.ac.kr",
      buyer_addr: "성북구 삼선교로 16길 116",
      buyer_postalcode: "02876",
    };
    IMP.request_pay(data, callback);
  };

  const Pay_kakao = () => {
    const sample1 = firestore.collection("user");
    sample1.doc("user1@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    sample1.doc("user2@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    sample1.doc("user3@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    const { IMP } = window;
    IMP.init("imp02656648");

    const data = {
      pg: "kakaopay",
      pay_method: "card",
      merchant_uid: `mid_${new Date().getTime()}`,
      name: "카카오페이 테스트",
      amount: "1000",
      custom_data: {
        name: "부가정보",
        desc: "세부 부가정보",
      },
      buyer_name: "홍길동",
      buyer_tel: "027604114",
      buyer_email: "hansung@hansung.ac.kr",
      buyer_addr: "성북구 삼선교로 16길 116",
      buyer_postalcode: "02876",
    };
    IMP.request_pay(data, callback);
  };

  const Pay_payco = () => {
    const sample1 = firestore.collection("user");
    sample1.doc("user1@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    sample1.doc("user2@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    sample1.doc("user3@user.com").set({숙소10이름: "에코랜드 호텔", 숙소10예약날짜: "friday16", 숙소10체크: "0"});
    const { IMP } = window;
    IMP.init("imp02656648");

    const data = {
      pg: "payco",
      pay_method: "card",
      merchant_uid: `mid_${new Date().getTime()}`,
      name: "페이코 테스트",
      amount: "1000",
      custom_data: {
        name: "부가정보",
        desc: "세부 부가정보",
      },
      buyer_name: "홍길동",
      buyer_tel: "027604114",
      buyer_email: "hansung@hansung.ac.kr",
      buyer_addr: "성북구 삼선교로 16길 116",
      buyer_postalcode: "02876",
    };
    IMP.request_pay(data, callback);
  };

  const callback = (rsp) => {
    const { success, error_msg, imp_uid, merchant_uid, pay_method, paid_amount, status } = rsp;
    if (success) {
      alert("결제 성공");
    } else {
      alert(`결제 실패: ${error_msg}`);
    }
  };

  return (
    <div className="payment-container">
    <h1 className="payment-heading">확인 및 결제</h1>
      <div className="payment-card-container">
        <PaymentCardJ />
      </div>
      <br />
      <div className="button-container">
      <button style={{ border: "none", background: "none" }} onClick={Pay_toss}>
        <img src={logoToss} alt="토스페이로 결제하기" style={{ width: "4cm" }} />
      </button>
      <br />
      <br />
      <button style={{ border: "none", background: "none" }} onClick={Pay_kg}>
        <img src={logoCreditCard} alt="신용카드로 결제하기" style={{ width: "2.8cm" }} />
      </button>
      <br />
      <br />
      <button onClick={Pay_kakao} style={{ border: "none", background: "none" }}>
        <img src={logoKakao} alt="카카오로 결제하기" style={{ width: "2cm" }} />
      </button>
      <br />
      <br />
      <button style={{ border: "none", background: "none" }} onClick={Pay_payco}>
        <img src={logoPayco} alt="페이코로 결제하기" style={{ width: "3.2cm" }} />
      <br />
      </button>
      </div>
      </div>
  );
};

export default PaymentJ;