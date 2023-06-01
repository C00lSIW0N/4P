import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleA from '../images/sampleA.jpg';

function PaymentCardA() {
  const cardTitle = "제주 신라호텔";

  return (
    <Card style={{ width: '50rem', height: '50rem' }}>
      <div style={{ height: '10cm', overflow: 'hidden' }}>
      <Card.Img variant="top" src={sampleA} style={{ height: '100%', objectFit: 'cover' }} />
        </div>
      <Card.Body>
        <Card.Title
        style={{
            fontWeight: "bold",
            color: "black",
            textDecoration: "none",
            fontSize: "36px" // 원하는 크기로 조정해주세요
          }}
          >
          {cardTitle}
        </Card.Title>
      </Card.Body>
      <ListGroup className="list-group-flush">
        <ListGroup.Item>예약정보</ListGroup.Item>
        <ListGroup.Item>날짜</ListGroup.Item>
        <ListGroup.Item>7월 18일~20일</ListGroup.Item>
        <ListGroup.Item>인원</ListGroup.Item>
        <ListGroup.Item>2명</ListGroup.Item>
        <ListGroup.Item>요금 세부정보</ListGroup.Item>
        <ListGroup.Item>\457,000 X 1박</ListGroup.Item>
        <ListGroup.Item>\457,000</ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default PaymentCardA;