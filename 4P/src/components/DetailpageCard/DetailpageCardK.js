import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleK from '../../images/sampleK.jpg';
import sampleKk from '../../images/sampleKk.jpg';
import sampleKkk from '../../images/sampleKkk.jpg';

function DetailpageCardK() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleK, sampleKk, sampleKkk];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "제주도 제주시 해안마을북길 14-6";
  const cardText = `제주 샹그릴라 호텔앤리조트는 제주국제공항에서 차량으로 약 20분 거리에 위치해있습니다. 주변 관광 명소인 제주공룡랜드는 차로 약 5분이면 가실 수 있습니다. 이호테우 해변은 차로 약 15분, 곽지해수욕장은 차로 약 20분 소요됩니다. 호텔에는 편의점과 레스토랑이 갖춰져 있습니다.`;
  const MAX_TEXT_LINES = 12;
  const [isExpanded, setIsExpanded] = useState(false); // 상태 추가

  const toggleTextExpansion = () => {
    setIsExpanded(!isExpanded);
  };

  const textLines = isExpanded
    ? cardText.split("\n") // 전체 텍스트를 모두 표시
    : cardText.split("\n").slice(0, MAX_TEXT_LINES); // 최대 줄 수에 맞춰 텍스트 표시
  const truncatedText = textLines.join("\n");
  const isTruncated = textLines.length < cardText.split("\n").length;

  return (
    <Card style={{ width: '50rem', height: '50rem' }}>
      <div style={{ height: '10cm', overflow: 'hidden' }}>
        <Card.Img
          variant="top"
          src={images[currentImageIndex]}
          style={{ height: '100%', objectFit: 'cover' }}
        />
      </div>
      <Card.Body>
        <Card.Title
          style={{
            color: "grey",
            textDecoration: "none",
            fontSize: "20px"
          }}
        >
          {cardTitle}
        </Card.Title>
        <br/>
        <Card.Text
          style={{
            whiteSpace: "pre-wrap",
            display: "-webkit-box",
            WebkitBoxOrient: "vertical",
            WebkitLineClamp: isExpanded ? "unset" : MAX_TEXT_LINES, // 텍스트 펼쳐질 때 제한 없음
            maxHeight: isExpanded ? "unset" : `${MAX_TEXT_LINES * 1.5}em`, // 텍스트 펼쳐질 때 제한 없음
            overflow: "hidden",
            textOverflow: "ellipsis"
          }}
        >
          {truncatedText}
          {isTruncated && (
            <span style={{ color: "blue", cursor: "pointer" }} onClick={toggleTextExpansion}>
              ...
            </span>
          )}
        </Card.Text>
      </Card.Body>
      <ListGroup className="list-group-flush">
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
            justifyContent: "flex-end"
          }}
        >평점 7.8</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "grey",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
            justifyContent: "flex-end"
          }}
          >238개의 확인된 투숙객 후기 참고
          </ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardK;