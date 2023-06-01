import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

function DetailpageCardFf() {

    const GotoPayment = () => {
        window.location.href = "paymentf";
      };

      const [isHovered1, setIsHovered1] = useState(false);
      const [isHovered2, setIsHovered2] = useState(false);
      const [isHovered3, setIsHovered3] = useState(false);
      const [isHovered4, setIsHovered4] = useState(false);
      const [isHovered5, setIsHovered5] = useState(false);

  return (
    <Card style={{ width: '50rem' }}>
      <Card.Header>숙소유형</Card.Header>
      <ListGroup variant="flush">

      <ListGroup.Item style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <span
          style={{
            color: "grey",
            fontSize: "22px",
            fontWeight: "bold"
            }}
            >하얏트 패키지
            </span>
          <button
            onClick={GotoPayment}
            onMouseEnter={() => setIsHovered1(true)}
            onMouseLeave={() => setIsHovered1(false)}
            style={{
              fontSize: "20px",
              padding: "5px 10px",
              backgroundColor: isHovered1 ? "#887156" : "#70aeaa",
              color: "#FFFFFF",
              border: "none",
              borderRadius: "4px"
            }}
          >
            결제
          </button>
        </ListGroup.Item>

        <ListGroup.Item style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <span
          style={{
            color: "grey",
            fontSize: "22px",
            fontWeight: "bold"
            }}
            >팀버 주니어 룸 (1 킹 베드)
            </span>
          <button
            onClick={GotoPayment}
            onMouseEnter={() => setIsHovered2(true)}
            onMouseLeave={() => setIsHovered2(false)}
            style={{
              fontSize: "20px",
              padding: "5px 10px",
              backgroundColor: isHovered2 ? "#887156" : "#70aeaa",
              color: "#FFFFFF",
              border: "none",
              borderRadius: "4px"
            }}
          >
            결제
          </button>
        </ListGroup.Item>

        <ListGroup.Item style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <span
          style={{
            color: "grey",
            fontSize: "22px",
            fontWeight: "bold"
            }}
            >Timber Suite(2인조식 포함★)
            </span>
          <button
            onClick={GotoPayment}
            onMouseEnter={() => setIsHovered3(true)}
            onMouseLeave={() => setIsHovered3(false)}
            style={{
              fontSize: "20px",
              padding: "5px 10px",
              backgroundColor: isHovered3 ? "#887156" : "#70aeaa",
              color: "#FFFFFF",
              border: "none",
              borderRadius: "4px"
            }}
          >
            결제
          </button>
        </ListGroup.Item>

        <ListGroup.Item style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <span
          style={{
            color: "grey",
            fontSize: "22px",
            fontWeight: "bold"
            }}
            >프리미어 팀버 스위트
            </span>
          <button
            onClick={GotoPayment}
            onMouseEnter={() => setIsHovered4(true)}
            onMouseLeave={() => setIsHovered4(false)}
            style={{
              fontSize: "20px",
              padding: "5px 10px",
              backgroundColor: isHovered4 ? "#887156" : "#70aeaa",
              color: "#FFFFFF",
              border: "none",
              borderRadius: "4px"
            }}
          >
            결제
          </button>
        </ListGroup.Item>

        <ListGroup.Item style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <span
          style={{
            color: "grey",
            fontSize: "22px",
            fontWeight: "bold"
            }}
            >파인 스위트
            </span>
          <button
            onClick={GotoPayment}
            onMouseEnter={() => setIsHovered5(true)}
            onMouseLeave={() => setIsHovered5(false)}
            style={{
              fontSize: "20px",
              padding: "5px 10px",
              backgroundColor: isHovered5 ? "#887156" : "#70aeaa",
              color: "#FFFFFF",
              border: "none",
              borderRadius: "4px"
            }}
          >
            결제
          </button>
        </ListGroup.Item>

      </ListGroup>
    </Card>
  );
}

export default DetailpageCardFf;