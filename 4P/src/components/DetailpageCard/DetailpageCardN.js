import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleN from '../../images/sampleN.jpg';
import sampleNn from '../../images/sampleNn.jpg';
import sampleNnn from '../../images/sampleNnn.jpg';

function DetailpageCardN() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleN, sampleNn, sampleNnn];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "서울 강북구 삼양로 689";
  const cardText = `파라스파라 서울 오퍼레이티드 바이 조선 호텔 & 리조트는 북한산 국립공원 내 위치한 서울 최초의 단지형 리조트입니다. 가까운 지하철역은 북한산 우이역이며, 도보로 약 3분 거리에 있습니다. 주변에는 우이동 먹거리 마을이 있고, 차로 약 25분 거리에는 북서울꿈의숲이 있습니다. 야외에는 더 라운드 풀이 있으며, 루프탑에는 자쿠지가 마련되어 있습니다. 실내에는 건식 및 습식 사우나, 노천탕이 있어 피로를 풀며 시간을 보내실 수 있습니다. 이외에 피트니스센터, 연회장을 이용하실 수 있습니다. 총 130개의 객실을 보유하며 3개의 동으로 나뉘어 있습니다. 현대적인 인테리어의 객실 내부에는 발코니, TV, 에어컨, 시몬스사 침대 등이 갖춰져 있습니다. 루프탑에는 북한산을 바라보며 휴식을 취할 수 있는 카페와 내부에는 올데이 다이닝 ‘우디 플레이트’와 모던 아시안 & 그릴 ‘파크 689’와 ‘파라스파라 델리’카페가 있습니다.`;
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
        >평점 7.7</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "grey",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
            justifyContent: "flex-end"
          }}
          >8개의 확인된 투숙객 후기 참고</ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardN;