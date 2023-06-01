import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleO from '../../images/sampleO.jpg';
import sampleOo from '../../images/sampleOo.jpg';
import sampleOoo from '../../images/sampleOoo.jpg';

function DetailpageCardO() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleO, sampleOo, sampleOoo];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "부산 해운대구 달맞이길 30";
  const cardText = `시그니엘 부산은 엘시티(LCT) 타워 3층부터 19층까지 자리하고 있는 럭셔리 호텔입니다. 부산 2호선 중동역 7번 출구에서 도보로 약 13분, 2호선 해운대역 1번 출구에서 도보로 약 15분 정도 소요됩니다. 해운대 해수욕장이 걸어서 약 10분, 해운대 달맞이길과 광안대교가 차로 약 10분 거리에 있습니다. 400평 규모의 인피니티 풀에서는 해운대 경치를 감상하며 카바나에서 휴식을 취하실 수 있습니다. 실내에는 별도의 수영장과 고급 브랜드인 '샹테카이' 스파, 사우나 등이 마련되어 있습니다. 또한, 어린이 전용 놀이 공간인 '키즈 라운지', 투숙객 전용 라운지인 '살롱 드 시그니엘' 그리고 투숙객 전용 산책로 '가든 테라스'도 프라이빗하게 이용하실 수 있습니다. 총 260실의 객실을 보유하고 있으며, 대부분의 객실 테라스에서는 해운대 해수욕장과 미포항 전경을 감상하실 수 있습니다. 모든 침구는 고급 브랜드 프레떼로 갖춰져 있으며, 별도로 맞춤형 베개와 턴다운 서비스, 신문, 다림질 서비스, 슈 폴리싱 서비스, 유무선 인터넷 서비스 등을 제공합니다. '더 뷰'에서는 뷔페 식사를 드실 수 있으며, 월드 클래스 스타 세프가 선보이는 타파스 & 바 '차오란', 에프터눈 티와 커피를 드실 수 있는 ' 더 라운지', 간단한 스낵을 드실 수 있는 '풀 바', 베이커리샵인 '페이스트리 살롱'이 있어 다양한 미식 체험을 하실 수 있습니다.`;
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
        >평점 9.2</ListGroup.Item>
        <ListGroup.Item
        style={{
            fontWeight: "bold",
            color: "grey",
            textDecoration: "none",
            fontSize: "20px",
            display: "flex",
            justifyContent: "flex-end"
          }}
          >1,408개의 확인된 투숙객 후기 참고</ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardO;