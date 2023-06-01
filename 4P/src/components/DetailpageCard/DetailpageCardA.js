import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleA from '../../images/sampleA.jpg';
import sampleAa from '../../images/sampleAa.jpg';
import sampleAaa from '../../images/sampleAaa.jpg';

function DetailpageCardA() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleA, sampleAa, sampleAaa];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "제주도 서귀포시 중문관광로72번길 75";
  const cardText = `제주 중문 관광 단지에 위치하고 있는 제주신라호텔은 사계절 휴양 리조트호텔입니다. 해변가가 내려다보이는 쉬리의 언덕이 유명한 제주 신라 호텔은 중문관광단지 내 자리하고 있어 여미지식물원, 천제연폭포, 별 내린 전망대 등이 아주 가까운 거리에 있습니다. 또한, 제주국제공항은 차로 약 1시간 5분 거리에 있습니다. 제주의 자연을 느낄 수 있는 숨비 정원을 사이로 실내, 외 수영장과 따뜻한 온수 풀 및 자쿠지 스파를 즐기며 카바나에서 휴식을 취하실 수 있습니다. 캠핑, 게임 등을 즐기실 수 있는 레저 프로그램과 외에도 피트니스센터, 비즈니스 센터, 연회장, 아케이드 등이 마련되어 있습니다. 38개의 스위트 객실을 포함한 429개 객실을 보유하고 있으며 산, 정원 또는 바다를 감상하실 수 있습니다. 객실 내부에는 TV, 냉장고, 에어컨, 금고, 슬리퍼 등이 완비되어 있으며 욕실에는 욕실용품, 비데, 헤어드라이어 등이 있습니다. 뷔페식당인 '더 파크뷰', 한식당 '천지', 일식당 '히노데'에서는 다양한 경험할 수 있습니다. 또한, 로비 라운지 '바당', 라이브러리 바인 '올래', '패스트리 부티크'도 갖춰져 있습니다.`;
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
        >평점 9.0</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "grey",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
            justifyContent: "flex-end"
          }}
          >1,542개의 확인된 투숙객 후기 참고
          </ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardA;