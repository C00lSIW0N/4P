import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleL from '../../images/sampleL.jpg';
import sampleLl from '../../images/sampleLl.jpg';
import sampleLll from '../../images/sampleLll.jpg';

function DetailpageCardL() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleL, sampleLl, sampleLll];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "제주도 서귀포시 예래해안로 542";
  const cardText = `자연 친화적인 인피니티 풀로 유명한 히든 클리프 호텔 & 네이처는 제주국제공항에서 차로 약 50분 소요됩니다. 중문관광단지, 여미지 식물원 등 주요 관광지도 차로 10분이면 갈 수 있습니다. 이 호텔은 풀 사이드 바와 카바나가 마련된 인피니티 풀, 피트니스 센터, 키즈 플레이룸, 산책길인 히든 트레일 등의 즐길 거리가 많습니다. 또한, 대연회장, 회의실이 마련되어 다양한 행사를 진행하기도 좋습니다. 일반 객실뿐 아니라 스위트 객실도 마련되어 있습니다. 객실에는 에어컨, TV, 냉장고, 전기 주전자 등이 구비되어 있으며 욕실에는 록시땅 어메니티, 달바 스킨케어 키트가 갖춰져 있습니다. 270도 파노라마 전망을 감상할 수 있는 올 데이 다이닝 레스토랑 '파노라마’에서는 조식 뷔페부터 다채롭게 구성된 메뉴를, 제주 중문을 한눈에 내려다 볼 수 있는 '비욘드 루프탑 바'에서는 다양한 주류 컬렉션 및 서비스를 제공하고 있습니다. 라운지 카페 '치치'에서는 간단한 델리와 커피와 함께 여유를 즐기실 수 있습니다.`;
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
        >평점 8.3</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "grey",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
            justifyContent: "flex-end"
          }}
          >912개의 확인된 투숙객 후기 참고
          </ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardL;