import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleG from '../../images/sampleG.jpg';
import sampleGg from '../../images/sampleGg.jpg';
import sampleGgg from '../../images/sampleGgg.jpg';

function DetailpageCardG() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleG, sampleGg, sampleGgg];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "제주도 서귀포시 표선면 민속해안로 537";
  const cardText = `제주 민속촌 바로 옆에 자리한 해비치 호텔 & 리조트는 근처에는 표선 해비치 해변도 있습니다. 차로 1시간이면 성산 일출봉까지 가실 수 있습니다. 아쿠아플라넷 제주와 섭지코지는 차로 50분 거리에 있습니다. 또한, 제주국제공항까지는 차로 1시간 30분이 소요됩니다. 야외 수영장은 실내로도 연결되며, 유아풀과 자쿠지도 갖추었습니다. 이외에 스파, 사우나, 피트니스센터, 테니스코트, 보드게임존, 키즈 전용 놀이 공간과 엔터테인먼트 존 등 즐길 거리와 편의점, 소품샵 등 볼거리도 많습니다. 주차장에는 급속 3대와 완속 6대의 전기차 충전기를 보유하고 있습니다. 288실의 호텔 객실과 215실의 리조트 객실을 포함한 503의 객실을 보유하며, 최소 객실 면적이 47평방미터로 국내에서 가장 넓은 공간을 자랑합니다. 호텔과 리조트형으로 나뉘어 있으며 내부에는 에어컨, TV, 냉장고, 전기 주전자가 있으며 욕실에는 비데와 욕실용품, 헤어드라이어 등이 구비되어 있습니다. 별도로, 리조트형에는 주방이 마련되어 있습니다. 호텔동과 리조트동 두 건물에 프렌치, 한식뿐 아니라 뷔페식으로 제공되는 레스토랑이 있으며, 실내외에 운치 있는 바, 신선한 음료와 베이커리를 제공하는 베이커리 샵 마고 등 다양한 먹거리가 있습니다.`;
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
        >평점 8.6</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "grey",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
            justifyContent: "flex-end"
          }}
          >1,203개의 확인된 투숙객 후기 참고
          </ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardG;