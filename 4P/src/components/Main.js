import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import CardA from './CardList/CardA';
import CardB from './CardList/CardB';
import CardC from './CardList/CardC';
import CardD from './CardList/CardD';
import CardE from './CardList/CardE';
import CardF from './CardList/CardF';
import CardG from './CardList/CardG';
import CardH from './CardList/CardH';
import CardI from './CardList/CardI';
import CardJ from './CardList/CardJ';
import CardK from './CardList/CardK';
import CardL from './CardList/CardL';
import CardM from './CardList/CardM';
import CardN from './CardList/CardN';
import CardO from './CardList/CardO';
import CardP from './CardList/CardP';

function Main() {
  const cards = [
    <CardA />,
    <CardB />,
    <CardC />,
    <CardD />,
    <CardE />,
    <CardF />,
    <CardG />,
    <CardH />,
    <CardI />,
    <CardJ />,
    <CardK />,
    <CardL />,
    <CardM />,
    <CardN />,
    <CardO />,
    <CardP />,
  ];

  return (
    <Container fluid>
      <Row xs={1} md={2} lg={4} className="card-row">
        {cards.map((card, index) => (
          <Col key={index} className="card-column">
            {card}
          </Col>
        ))}
      </Row>
    </Container>
  );
}

export default Main;
