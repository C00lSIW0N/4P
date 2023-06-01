import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleE from '../../images/sampleE.jpg';

function PaymentCardE() {
  const cardTitle = "신라스테이 제주";

  return (
    <Card style={{ width: '50rem', height: '50rem' }}>
      <div style={{ height: '10cm', overflow: 'hidden' }}>
      <Card.Img variant="top" src={sampleE} style={{ height: '100%', objectFit: 'cover' }} />
        </div>
      <Card.Body>
        <Card.Title
        style={{
            fontWeight: "bold",
            color: "grey",
            textDecoration: "none",
            fontSize: "24px", // 원하는 크기로 조정해주세요
            display: "flex",
            justifyContent: "flex-end"
          }}
          >
          {cardTitle}
        </Card.Title>
      </Card.Body>
      <ListGroup className="list-group-flush">
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "#896b48",
            textDecoration: "none",
            fontSize: "22px",
            display: "flex",
            justifyContent: "flex-end"
          }}
        >예약정보</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "#404040",
            textDecoration: "none",
            fontSize: "22px",
            display: "flex",
          }}
        >날짜</ListGroup.Item>
        <ListGroup.Item
        style={{
            color: "#404040",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
          }}
        >7월 18일~20일</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "#404040",
            textDecoration: "none",
            fontSize: "22px",
            display: "flex",
          }}
        >인원</ListGroup.Item>
        <ListGroup.Item
        style={{
            color: "#404040",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
          }}
        >2명</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "#404040",
            textDecoration: "none",
            fontSize: "22px",
            display: "flex",
          }}
        >요금 세부정보</ListGroup.Item>
        <ListGroup.Item
        style={{
            color: "#404040",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
          }}
        >\149,270 X 1박</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "#404040",
            textDecoration: "none",
            fontSize: "30px",
            display: "flex",
            justifyContent: "flex-end"
          }}
        >\149,270</ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default PaymentCardE;