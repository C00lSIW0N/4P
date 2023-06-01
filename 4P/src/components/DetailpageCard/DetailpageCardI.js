import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleI from '../../images/sampleI.jpg';
import sampleIi from '../../images/sampleIi.jpg';
import sampleIii from '../../images/sampleIii.jpg';

function DetailpageCardI() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleI, sampleIi, sampleIii];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "제주도 제주시 탑동로 66";
  const cardText = `제주시에 위치한 라마다 프라자 제주 호텔은 제주 공항에서 차로 단 10분 거리에 있습니다. 주변 관광지로는 용두암과 칠성로 쇼핑상가까지 차로 약 5분, 삼성혈과 민속자연사박물관까지 차로 약 10분 정도 소요됩니다. 이 호텔은 북유럽의 초호화유람선(SILJA LINE)을 모티브로 독특한 디자인으로 설계되었으며, 호텔 내에 야외 및 실내 수영장, 사우나, 피트니스센터, 다양한 규모의 연회장 등이 갖춰져 있습니다. 총 400개 객실을 보유하고 있으며, 바다 전망의 디럭스트윈 객실, 온돌 객실, 어린이 공간이 분리된 '키즈 스위트' 등 다양한 타입으로 구성되어 있습니다. 다이닝 공간으로는 크루즈 분위기의 뷔페 레스토랑 '더 블루', 한식과 일식 메뉴를 선보이는 '탐모라', 로비 라운지 '스코트라', '블랙스톤즈' 바 등이 있습니다.`;
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
        >평점 8.2</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "grey",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
            justifyContent: "flex-end"
          }}
          >1,289개의 확인된 투숙객 후기 참고
          </ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardI;