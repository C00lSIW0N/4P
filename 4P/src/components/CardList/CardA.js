import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';
import sampleA from '../../images/sampleA.jpg';
import greyheart from '../../images/greyheart.png';
import pinkheart from '../../images/pinkheart.png';
import { firestore } from "../../firebase_config";

function CardA() {
  const [isLiked, setIsLiked] = useState(false);
  const [isMouseOver, setIsMouseOver] = useState(false);

  const handleLike = (e) => {
    e.stopPropagation();
    setIsLiked(!isLiked);
    const sample1 = firestore.collection("user-cart");
    sample1.doc("user1@user.com").update({숙소1이름: "제주 신라호텔", 숙소1예약날짜: "monday9", 숙소1체크: "1"});
    sample1.doc("user2@user.com").update({숙소1이름: "제주 신라호텔", 숙소1예약날짜: "monday9", 숙소1체크: "1"});
    sample1.doc("user3@user.com").update({숙소1이름: "제주 신라호텔", 숙소1예약날짜: "monday9", 숙소1체크: "1"});
  };

  const handleMouseEnter = (e) => {
    setIsMouseOver(true);
  };

  const handleMouseLeave = (e) => {
    setIsMouseOver(false);
  };

  const handleTitleClick = () => {
    window.location.href = "/detailpagea";
  };

  const cardTitle = "제주 신라호텔";
  const priceText = "457,000원";
  const cardText = `${priceText}\n화사한 아침에 #모닝밀\n5월~6월 전객실 혜택 #풀장 #힐링`;  
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
          <Card.Img variant="top" src={sampleA} style={{ height: '100%', objectFit: 'cover' }} />
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

export default CardA;