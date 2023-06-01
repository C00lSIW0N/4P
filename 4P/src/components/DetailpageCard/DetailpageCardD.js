import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleD from '../../images/sampleD.jpg';
import sampleDd from '../../images/sampleDd.jpg';
import sampleDdd from '../../images/sampleDdd.jpg';

function DetailpageCardD() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleD, sampleDd, sampleDdd];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "제주도 서귀포시 중문관광로72번길 35";
  const cardText = `제주 중문관광단지에 자리한 롯데호텔 제주는 총 500개의 객실을 갖춘 리조트호텔입니다. 제주국제공항에서 차로 약 80분 거리에 있으며 무료 리무진 서비스를 운영하고 있습니다. 또한, 천제연 폭포, 여미지식물원 등이 도보로 10분, 중문색달해수욕장, 갯깍주상절리대 등이 도보로 약 20분 내외의 가까운 거리에 있습니다. 호텔 내에는 숲을 그대로 옮겨 놓은 듯한 정원, 사계절 온수 풀 '해온(海溫)', 프라이빗 카바나, 피트니스센터, 웰니스 트리트먼트를 제공하는 'V스파' 등의 시설이 마련되어 있습니다. 호텔 8층에는 내국인도 구매가 가능한 루이비통 매장이 있으며 어린이들의 놀이 체험을 위한 공간 '플레이토피아'에서는 패밀리 락 볼링장, VR체험 등의 다양한 액티비티를 즐길 수 있습니다. 객실에서는 오션뷰, 가든뷰, 마운틴 뷰 등을 감상하실 수 있습니다. 또한, 특별한 테마 객실인 헬로키티 캐릭터룸과 풀빌라 스위트 등 다양한 타입의 객실로 구성되어 있습니다. 올데이 다이닝 레스토랑 '더 캔버스', 바&라운지 '더 라운지 앤 바'와 야외에 자리한 '풀 카페', 베이커리 '델리카 한스' 등의 다이닝 공간이 마련되어 있습니다.`;
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
          >1,604개의 확인된 투숙객 후기 참고
          </ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardD;