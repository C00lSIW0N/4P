import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Card from 'react-bootstrap/Card';
import sampleP from '../../images/sampleP.jpg';
import greyheart from '../../images/greyheart.png';
import pinkheart from '../../images/pinkheart.png';
import { firestore } from "../../firebase_config";

function CardP() {
  const [isLiked, setIsLiked] = useState(false);
  const [isMouseOver, setIsMouseOver] = useState(false);

  const handleLike = (e) => {
    const sample1 = firestore.collection("user-cart");
    sample1.doc("user1@user.com").set({숙소16이름: "파라다이스 호텔 부산", 숙소16예약날짜: "wednesday12", 숙소16체크: "1"});
    sample1.doc("user2@user.com").set({숙소16이름: "파라다이스 호텔 부산", 숙소16예약날짜: "wednesday12", 숙소16체크: "1"});
    sample1.doc("user3@user.com").set({숙소16이름: "파라다이스 호텔 부산", 숙소16예약날짜: "wednesday12", 숙소16체크: "1"});
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
    window.location.href = "/detailpagep";
  };

  const cardTitle = "파라다이스 호텔 부산";
  const priceText = "392,040원";
  const cardText = `${priceText}\n'<5성급:인증>\n바다 및 가든 뷰의 레스토랑 '온더플레이트'와 '닉스 그릴&와인', 음료를 즐길 수 있는 로비 라운지 '크리스탈 가든'`;  
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
          <Card.Img variant="top" src={sampleP} style={{ height: '100%', objectFit: 'cover' }} />
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

export default CardP;