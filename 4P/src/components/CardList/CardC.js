import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';
import sampleC from '../../images/sampleC.jpg';
import greyheart from '../../images/greyheart.png';
import pinkheart from '../../images/pinkheart.png';
import { firestore } from "../../firebase_config";

function CardC() {
  const [isLiked, setIsLiked] = useState(false);
  const [isMouseOver, setIsMouseOver] = useState(false);

  const handleLike = (e) => {
    const sample1 = firestore.collection("user-cart");
    sample1.doc("user1@user.com").set({숙소3이름: "메종 글래드 제주", 숙소3예약날짜: "monday14", 숙소3체크: "1"});
    sample1.doc("user2@user.com").set({숙소3이름: "메종 글래드 제주", 숙소3예약날짜: "monday14", 숙소3체크: "1"});
    sample1.doc("user3@user.com").set({숙소3이름: "메종 글래드 제주", 숙소3예약날짜: "monday14", 숙소3체크: "1"});
    e.stopPropagation();
    setIsLiked(!isLiked);
  };

  const handleMouseEnter = (e) => {
    setIsMouseOver(true);
  };

  const handleMouseLeave = (e) => {
    setIsMouseOver(false);
  };

  const handleTitleClick = () => {
    window.location.href = "/detailpagec";
  };

  const cardTitle = "메종 글래드 제주";
  const priceText = "199,800원";
  const cardText = `${priceText}\n2022 올해의 브랜드 대상 '라이프 스타일 호텔 부문' 1위로 4년 연속 선정`;  
  const MAX_TEXT_LINES = 3;
  const textLines = cardText.split("\n").slice(0, MAX_TEXT_LINES);
  const truncatedText = textLines.join("\n");
  const isTruncated = textLines.length < cardText.split("\n").length;

  const heartImage = isLiked ? pinkheart : greyheart;

  return (
    <Card style={{ width: '18rem' }}>
      <div
        onClick={handleTitleClick}
        onMouseEnter={handleMouseEnter}
        onMouseLeave={handleMouseLeave}
        style={{ cursor: isMouseOver ? "pointer" : "default" }}
      >        
      <div style={{ height: '6cm', overflow: 'hidden' }}>
      <Card.Img variant="top" src={sampleC} style={{ height: '100%', objectFit: 'cover' }} />
    </div>
      </div>
      <Card.Body>
        <Card.Title
          style={{
            fontWeight: "bold",
            color: "black",
            textDecoration: "none",
            cursor: isMouseOver ? "pointer" : "default"
          }}
          onClick={handleTitleClick}
          onMouseEnter={handleMouseEnter}
          onMouseLeave={handleMouseLeave}
        >
          {cardTitle}
        </Card.Title>
        <Card.Text
          style={{
            whiteSpace: "pre-wrap",
            display: "-webkit-box",
            WebkitBoxOrient: "vertical",
            WebkitLineClamp: MAX_TEXT_LINES,
            maxHeight: `${MAX_TEXT_LINES * 1.5}em`,
            overflow: "hidden",
            textOverflow: "ellipsis"
          }}
        >
          {truncatedText}
          {isTruncated && "..."}
        </Card.Text>
        <img
          src={heartImage}
          className="heart-icon"
          alt="heart"
          style={{ width: '5mm', cursor: isMouseOver ? "pointer" : "default" }}
          onClick={handleLike}
          onMouseEnter={handleMouseEnter}
          onMouseLeave={handleMouseLeave}
        />
      </Card.Body>
    </Card>
  );
}

export default CardC;