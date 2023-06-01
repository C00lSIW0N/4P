import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';
import sampleM from '../../images/sampleM.jpg';
import greyheart from '../../images/greyheart.png';
import pinkheart from '../../images/pinkheart.png';
import { firestore } from "../../firebase_config";

function CardM() {
  const [isLiked, setIsLiked] = useState(false);
  const [isMouseOver, setIsMouseOver] = useState(false);

  const handleLike = (e) => {
    const sample1 = firestore.collection("user-cart");
    sample1.doc("user1@user.com").set({숙소13이름: "호텔 프린스 서울", 숙소13예약날짜: "wednesday9", 숙소13체크: "1"});
    sample1.doc("user2@user.com").set({숙소13이름: "호텔 프린스 서울", 숙소13예약날짜: "wednesday9", 숙소13체크: "1"});
    sample1.doc("user3@user.com").set({숙소13이름: "호텔 프린스 서울", 숙소13예약날짜: "wednesday9", 숙소13체크: "1"});
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
    window.location.href = "/detailpagem";
  };

  const cardTitle = "호텔 프린스 서울";
  const priceText = "422,529원";
  const cardText = `${priceText}\n일부 객실 서울N타워 전경을 감상\n러기지 보관, 심카드 대여 서비스를 유료로 제공`;  
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
          <Card.Img variant="top" src={sampleM} style={{ height: '100%', objectFit: 'cover' }} />
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

export default CardM;