import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleB from '../../images/sampleB.jpg';
import sampleBb from '../../images/sampleBb.jpg';
import sampleBbb from '../../images/sampleBbb.jpg';

function DetailpageCardA() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleB, sampleBb, sampleBbb];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "제주도 서귀포시 중문관광로72번길 100";
  const cardText = `파르나스 호텔 제주는 중문 색달 해변 근처 주상절리 위에 위치하고 있어, 휴식을 위한 최적의 장소에 위치하고 있습니다. 새롭게 선보이는 파르나스 호텔 제주의 디자인은 런던 유명 인테리어 스튜디오인 헤타(HETA)와 스튜디오 1508에서 진행했으며, 제주의 자연과 모던한 현대적인 디자인을 접목해 색다른 고급스러움을 표현했습니다. 또한 객실의 가구는 이스턴 에디션에서 자연의 돌, 나무, 전통 대문 '정남' 등에서 영감을 받아 제작했습니다. 총 307객실과 올 데이 다이닝 레스토랑, 로비 라운지와 바가 있으며 중, 소규모 미팅룸까지 보유하고 있어 고객의 여행 목적에 맞춰 시설 및 서비스 제공이 가능합니다. 모든 객실은 발코니를 가지고 있어 제주의 자연을 객실 안에서도 즐길 수 있습니다. 특히 파르나스 호텔 제주의 가장 시그니처인 인피니티 풀은 국내에서 가장 긴 110미터로 제주의 바다를 한눈에 즐길 수 있는 최적의 장소입니다. 이외에도 본에스티스 스파, 프리미엄 잉글리쉬 멤버십 클럽 프로맘킨더, 스페니쉬 레스토랑 만추안, GS25 편의점 등의 편의 시설도 제공하고 있습니다.`;
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
        >평점 9.3</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "grey",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
            justifyContent: "flex-end"
          }}
          >911개의 확인된 투숙객 후기 참고</ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardA;