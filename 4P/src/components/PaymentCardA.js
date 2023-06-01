import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleA from '../images/sampleA.jpg';

function PaymentCardA() {
  const cardTitle = "제주 신라호텔";
  const priceText = "457,000원";
//   const cardText = `${priceText}\n화사한 아침에 #모닝밀\n5월~6월 전객실 혜택 #풀장 #힐링`;
//   const MAX_TEXT_LINES = 3;
//   const textLines = cardText.split("\n").slice(0, MAX_TEXT_LINES);
//   const truncatedText = textLines.join("\n");
//   const isTruncated = textLines.length < cardText.split("\n").length;


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
        {/* <Card.Text
          style={{
            whiteSpace: "pre-wrap",
            display: "-webkit-box",
            WebkitBoxOrient: "vertical",
            WebkitLineClamp: MAX_TEXT_LINES,
            maxHeight: `${MAX_TEXT_LINES * 1.5}em`,
            overflow: "hidden",
            textOverflow: "ellipsis"
          }}
        >
          {truncatedText}
          {isTruncated && "..."}
        </Card.Text> */}
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