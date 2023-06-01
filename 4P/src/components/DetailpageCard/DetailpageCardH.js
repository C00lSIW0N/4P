import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleH from '../../images/sampleH.jpg';
import sampleHh from '../../images/sampleHh.jpg';
import sampleHhh from '../../images/sampleHhh.jpg';

function DetailpageCardH() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleH, sampleHh, sampleHhh];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "제주도 서귀포시 성산읍 섭지코지로 107";
  const cardText = `제주도 동쪽에 위치한 휘닉스 제주 섭지코지는 제주 국제공항에서 약 50분 소요되며, 신설되는 제2공항에서는 차로 약 10분 거리에 있습니다. 섭지코지까지는 도보로 약 3분 정도 소요되며, 차로 약 10분 거리에는 성산 일출봉이 있습니다. 아르누보의 유리공예 작품을 감상할 수 있는 유민 미술관, 사계절 온수풀인 실내외 수영장 외에도 어린이들의 놀이터 키즈 플레이 라운지가 갖춰져 있습니다. 객실에서는 파노라마로 제주 자연 절경을 감상하실 수 있습니다. 자연에서 즐길 수 있는 BBQ 외에도 모들 가든, 한식당 해랑이 있습니다. 전망대 식당과 테라스 가든으로 구성된 오감 만족 글라스하우스에는 캐주얼 다이닝 민트 카페와 인생샷을 남길 수 있는 민트 스튜디오, 컨템포러리 다이닝 민트 레스토랑이 있습니다.`;
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
          >731개의 확인된 투숙객 후기 참고
          </ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardH;