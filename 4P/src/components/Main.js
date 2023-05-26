// import React from "react";
// import 'bootstrap/dist/css/bootstrap.min.css';
// import Card from 'react-bootstrap/Card';
// import Row from 'react-bootstrap/Row';
// import Col from 'react-bootstrap/Col';

// function Main() {
//   const cardsData = [
//     { id: 1, title: 'Card 1', text: 'Some quick example text for Card 1' },
//     { id: 2, title: 'Card 2', text: 'Some quick example text for Card 2' },
//     { id: 3, title: 'Card 3', text: 'Some quick example text for Card 3' },
//     { id: 4, title: 'Card 4', text: 'Some quick example text for Card 4' },
//     { id: 5, title: 'Card 5', text: 'Some quick example text for Card 5' },
//     { id: 6, title: 'Card 6', text: 'Some quick example text for Card 6' },
//     { id: 7, title: 'Card 7', text: 'Some quick example text for Card 7' },
//     { id: 8, title: 'Card 8', text: 'Some quick example text for Card 8' },
//     { id: 9, title: 'Card 9', text: 'Some quick example text for Card 9' },
//     { id: 10, title: 'Card 10', text: 'Some quick example text for Card 10' },
//     { id: 11, title: 'Card 11', text: 'Some quick example text for Card 11' },
//     { id: 12, title: 'Card 12', text: 'Some quick example text for Card 12' },
//     { id: 13, title: 'Card 13', text: 'Some quick example text for Card 13' },
//     { id: 14, title: 'Card 14', text: 'Some quick example text for Card 14' },
//     { id: 15, title: 'Card 15', text: 'Some quick example text for Card 15' },
//     { id: 16, title: 'Card 16', text: 'Some quick example text for Card 16' },
//     { id: 17, title: 'Card 17', text: 'Some quick example text for Card 17' },
//     { id: 18, title: 'Card 18', text: 'Some quick example text for Card 18' },
//     { id: 19, title: 'Card 19', text: 'Some quick example text for Card 19' },
//     { id: 20, title: 'Card 20', text: 'Some quick example text for Card 20' },
//   ];

//   return (
//     <div>
//     <Row xs={1} md={2} lg={4} className="g-4">
//       {cardsData.map((card) => (
//         <Col key={card.id}>
//           <Card>
//             <Card.Img variant="top" src="holder.js/100px180" />
//             <Card.Body>
//               <Card.Title>{card.title}</Card.Title>
//               <Card.Text>{card.text}</Card.Text>
//               <Card.Link href="detailpage">상세페이지</Card.Link>
//             </Card.Body>
//           </Card>
//         </Col>
//       ))}
//     </Row>
//      <div style={{ height: '100px' }}></div> {/* 하단 여백 */}
//     </div>
//   );
// }

// export default Main;






// import React from "react";
// import 'bootstrap/dist/css/bootstrap.min.css';
// import Container from 'react-bootstrap/Container';
// import Row from 'react-bootstrap/Row';
// import CardA from './CardList/CardA';
// import CardB from './CardList/CardB';
// import CardC from './CardList/CardC';
// import CardD from './CardList/CardD';
// import CardE from './CardList/CardE';
// import CardF from './CardList/CardF';
// import CardG from './CardList/CardG';
// import CardH from './CardList/CardH';
// import CardI from './CardList/CardI';
// import CardJ from './CardList/CardJ';
// import CardK from './CardList/CardK';
// import CardL from './CardList/CardL';
// import CardM from './CardList/CardM';
// import CardN from './CardList/CardN';
// import CardO from './CardList/CardO';
// import CardP from './CardList/CardP';

// function Main() {
//   return (
//     <Container fluid>
//       <Row xs={1} md={2} lg={4}>
//         <CardA/>
//         <CardB/>
//         <CardC/>
//         <CardD/>
//       </Row>

//       <Row xs={1} md={2} lg={4}>
//           <CardE/>
//           <CardF/>
//           <CardG/>
//           <CardH/>
//       </Row>
      
//       <Row xs={1} md={2} lg={4}>
//           <CardI/>
//           <CardJ/>
//           <CardK/>
//           <CardL/>
//         </Row>

//         <Row xs={1} md={2} lg={4}>
//           <CardM/>
//           <CardN/>
//           <CardO/>
//           <CardP/>
//         </Row>

//     </Container>
//   );
// }

// export default Main;


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
