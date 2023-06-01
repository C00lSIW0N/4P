import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

function DetailpageCardBb() {

    const GotoPayment = () => {
        window.location.href = "payment";
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
            >딜럭스 킹 룸 (일반 전망)
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
            >딜럭스 트윈 룸 (일반 전망)
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
            >프리미어 패밀리 트윈 룸
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
            >주니어 스위트 패밀리 트윈
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
            >클럽 듀플렉스 스위트 (바다 전망)
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

export default DetailpageCardBb;