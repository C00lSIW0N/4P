import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleM from '../../images/sampleM.jpg';
import sampleMm from '../../images/sampleMm.jpg';
import sampleMmm from '../../images/sampleMmm.jpg';

function DetailpageCardM() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleM, sampleMm, sampleMmm];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "서울 중구(서울특별시) 퇴계로 130";
  const cardText = `오랜 역사를 자랑하는 호텔 프린스 서울은 4호선 명동역 2번 출구 바로 옆에 있습니다. 걸어서 10분이면 명동 거리, 신세계 백화점 본점 등까지 가실 수 있어 쇼핑하게 편리한 위치를 자랑합니다. 또한, 남산 케이블카 승차장과 남대문 시장 등도 도보 10분 이내에 가실 수 있습니다. 호텔 2층에는 트래블 센터가 있으며 러기지 보관, 심카드 대여 서비스를 유료로 제공하고 있습니다. 티머니 카드 구매 및 충전 서비스, 수하물 측정용 저울 비치 등 관광객을 위한 세심한 배려가 돋보이는 서비스도 이용하실 수 있습니다. 또한, 미팅룸도 마련되어 있습니다. 총 100개의 객실을 보유하고 있으며 일부 객실에서는 서울N타워 전경을 감상하실 수 있습니다. 뿐만 아니라, 온돌 객실, 장애인 편의 객실 등 다양한 유형의 객실을 보유하고 있습니다. 모든 객실에서는 LED TV, 냉장고, 금고 등이 있으며 객실 유형에 따라 샤워부스 또는 욕조가 있습니다. 호텔 2층 레스토랑에서는 미국식 조식 뷔페와 퓨전 한식 중식 뷔페를 제공하고 있으며 별도의 요금이 발생합니다.`;
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
        >평점 8.4</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "grey",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
            justifyContent: "flex-end"
          }}
          >542개의 확인된 투숙객 후기 참고
          </ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardM;