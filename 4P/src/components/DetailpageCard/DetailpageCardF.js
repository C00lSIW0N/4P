import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import sampleF from '../../images/sampleF.jpg';
import sampleFf from '../../images/sampleFf.jpg';
import sampleFff from '../../images/sampleFff.jpg';

function DetailpageCardF() {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [sampleF, sampleFf, sampleFff];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 2000);

    return () => {
      clearInterval(interval);
    };
  }, [images.length]);

  const cardTitle = "제주도 제주시 노연로 12";
  const cardText = `그랜드 하얏트 제주는 제주국제공항에서 차로 약 10분가량 떨어져 있습니다. 주변 관광지로는 산책하기 좋은 한라 수목원이 차로 약 7분, 멋진 경관의 이호테우 해수욕장이 차로 약 12분, 구경거리가 가득한 동문수산시장이 차로 약 20분가량 소요됩니다. 호텔에는 아름다운 뷰를 감상하며 수영을 즐길 수 있는 인피니티 풀과 프리미엄 스파 뿐만 아니라, 키즈 아케이드, 피트니스 센터 등이 마련되어 있습니다. 또한, 컨시어지 서비스와 룸서비스, 와이파이 등 여러 서비스도 이용하실 수 있습니다. 지하 5층부터 지상 38층으로 이루어진 호텔은 오션뷰와 마우틴뷰, 시티뷰를 즐길 수 있는 객실 총 1,600개를 보유하고 있습니다. 아늑하고 세련된 인테리어의 각 객실에는 스마트 TV, 개별 냉난방 시스템, 네스프레소 커피 머신 등이 준비되어 있으며, 욕실에는 고급 욕실용품과 헤어드라이어 등 편안한 투숙을 위한 시설이 완비되어 있습니다. 호텔 내 14곳의 레스토랑과 바에서는 한식, 중식, 일식 등 다채로운 요리를 맛보실 수 있습니다.`;
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
          >2,062개의 확인된 투숙객 후기 참고
          </ListGroup.Item>
      </ListGroup>
    </Card>
  );
}

export default DetailpageCardF;