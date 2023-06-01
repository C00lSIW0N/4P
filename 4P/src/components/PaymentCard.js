import React, { useState, useEffect } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';
import sampleA from '../images/sampleA.jpg';
import sampleAa from '../images/sampleAa.jpg';
import sampleAaa from '../images/sampleAaa.jpg';

function PaymentCard() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleA, sampleAa, sampleAaa]; // 사진 배열

  useEffect(() => {
    const interval = setInterval(() => {
      // 다음 사진으로 인덱스를 업데이트
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000); // 2초마다 변경

    return () => {
      clearInterval(interval); // 컴포넌트 언마운트 시 인터벌 정리
    };
  }, [images.length]);

  const cardTitle = "제주 신라호텔";
  const priceText = "457,000원";
  const cardText = `${priceText}\n화사한 아침에 #모닝밀\n5월~6월 전객실 혜택 #풀장 #힐링`;
  const MAX_TEXT_LINES = 3;
  const textLines = cardText.split("\n").slice(0, MAX_TEXT_LINES);
  const truncatedText = textLines.join("\n");
  const isTruncated = textLines.length < cardText.split("\n").length;

  return (
    <Card style={{ width: '50rem', height: '50rem' }}>
        <div style={{ height: '10cm', overflow: 'hidden' }}>
          <Card.Img
            variant="top"
            src={images[currentImageIndex]} // 현재 인덱스에 해당하는 사진 표시
            style={{ height: '100%', objectFit: 'cover' }}
          />
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
        <Card.Text
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
        </Card.Text>
      </Card.Body>
    </Card>
  );
}

export default PaymentCard;