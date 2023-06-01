import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleC from '../../images/sampleC.jpg';
import sampleCc from '../../images/sampleCc.jpg';
import sampleCcc from '../../images/sampleCcc.jpg';

function DetailpageCardC() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleC, sampleCc, sampleCcc];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "제주도 제주시 노연로 80";
  const cardText = `2022 올해의 브랜드 대상 '라이프 스타일 호텔 부문' 1위로 4년 연속 선정된, 40여 년의 역사와 전통을 자랑하는 메종 글래드 제주는 제주 관광의 랜드마크로서 제주 고유의 매력을 경험할 수 있는 호텔입니다. 제주 국제공항에서 차로 약 10분 거리에 위치하여 교통이 편리하며, 주변 관광지로는 차로 20분 거리에 용두암, 삼성혈, 민속 자연사 박물관 등이 있습니다. 호텔 야외에는 성인 전용의 인피티니풀과, 패밀리풀, 자쿠지가 갖춰져 있습니다. 또한, 피트니스센터와 사우나가 회원제로 운영되며, 스파, 글램핑 존, 멀티샵 '피렌체', '메종드누보 아베다살롱' 헤어 살롱 등의 부대시설이 마련되어 있습니다. 총 513개의 객실을 보유하고 있으며, 스탠다드부터 스위트까지 다양한 타입으로 구성되어 있습니다. 일부 객실은 바디 프렌드, 안다르 등의 인기 브랜드와 콜라보레이션을 하여 꾸며져 있습니다. 호텔 내에는 프리미엄 뷔페레스토랑 '삼다정', 갓포요리 전문 레스토랑 '갓포아키', 청담동 앨리스 바에서 새롭게 런칭하는 라운지 바 ‘정글북by앨리스바' 등이 있습니다. 또한, 음료를 드실 수 있는 공간으로는 'The lounge'도 있습니다.`;
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
          >2,216개의 확인된 투숙객 후기 참고
          </ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardC;