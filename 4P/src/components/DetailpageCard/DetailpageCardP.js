import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleP from '../../images/sampleP.jpg';
import samplePp from '../../images/samplePp.jpg';
import samplePpp from '../../images/samplePpp.jpg';

function DetailpageCardP() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleP, samplePp, samplePpp];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "부산 해운대구 해운대해변로 296";
  const cardText = `파라다이스 호텔 부산 - <5성급:인증> - 부산 해운대 해수욕장에 자리한 파라다이스 호텔 부산은 부산2호선 해운대역에서 도보로 약 10분, KTX 부산역에서 차로 약 50분 정도 소요됩니다. 부산의 대표 랜드마크인 센텀시티 신세계백화점, BEXCO 그리고 해운대 달맞이길 등이 모두 차로 약 15분 거리에 있습니다. 호텔 내에는 약 400평 규모의 실내 키즈카페가 마련되어 있습니다. 키즈 카페는 BMW 실내 드라이빙 센터, 웅진 북클럽 등의 공간으로 구성되어 있습니다. 또한, 바다 전망의 야외 수영장과 스파 공간 '오션 스파 씨메르', 사우나, 실내 골프장, 피트니스센터 등도 갖춰져 있습니다. 바다 및 가든 뷰의 레스토랑 '온더플레이트'와 '닉스 그릴&와인', 음료를 즐길 수 있는 로비 라운지 '크리스탈 가든', 베이커리 '파라다이스 부티크' 등의 다이닝 공간이 있습니다. 그뿐만 아니라, 이그제큐티브 라운지와 라운지 파라다이스에서 티타임과 해피아워를 즐기실 수 있습니다.`;
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
        >평점 8.9</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "grey",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
            justifyContent: "flex-end"
          }}
          >1,445개의 확인된 투숙객 후기 참고</ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardP;