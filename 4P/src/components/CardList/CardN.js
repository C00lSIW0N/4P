import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';
import sampleN from '../../images/sampleN.jpg';
import greyheart from '../../images/greyheart.png';
import pinkheart from '../../images/pinkheart.png';
import { firestore } from "../../firebase_config";

function CardN() {
  const [isLiked, setIsLiked] = useState(false);
  const [isMouseOver, setIsMouseOver] = useState(false);

  const handleLike = (e) => {
    const sample1 = firestore.collection("user-cart");
    sample1.doc("user1@user.com").set({숙소1이름: "호텔1", 숙소1예약날짜: "friday11", 숙소1체크: "1"});
    sample1.doc("user2@user.com").set({숙소2이름: "펜션1", 숙소2예약날짜: "monday14", 숙소2체크: "1"});
    sample1.doc("user3@user.com").set({숙소3이름: "게하1", 숙소3예약날짜: "thursday18", 숙소3체크: "1"});
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
    window.location.href = "/detailpagen";
  };

  const cardTitle = "호텔 토스카나";
  const priceText = "239,400원";
  const cardText = `${priceText}\n엉또 폭포가 차로 약 5분, 여미지 식물원, 중문 색달해변, 천제연 폭포 등이 차로 약 15분 거리`;  
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
          <Card.Img variant="top" src={sampleN} style={{ height: '100%', objectFit: 'cover' }} />
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

export default CardN;