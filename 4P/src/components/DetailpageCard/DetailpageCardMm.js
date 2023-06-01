import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

function DetailpageCardMm() {

    const GotoPayment = () => {
        window.location.href = "paymentm";
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
            >레드룸 - 무료 Wi-Fi
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
            >Twin Room A
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
            >디럭스 트윈룸 - 환불불가
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
            >디럭스 트윈+트윈 룸 (같은층 배정)
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
            >트리플
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

export default DetailpageCardMm;