import React from "react";
import { Link } from "react-router-dom";
import { firestore } from "../firebase_config";
import { firebase } from "../firebase_config";

import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import 'bootstrap/dist/css/bootstrap.min.css';


function Title() {
  
  const navbarStyle = {
    backgroundColor: 'white',
    paddingLeft: '2rem',
    paddingRight: '2rem',
    marginLeft: 'auto',
    marginRight: 'auto',
    maxWidth: '1200px', /* App.js의 가로 사이즈와 동일하게 설정 */
  };

  const brandStyle = {
    fontSize: '36pt',
    color: '#00bfa5',
  };

  const linkStyle = {
    color: '#a1887f',
  };

  return (
    <Navbar style={navbarStyle} bg="light" expand="lg">
      <Container fluid>
        <Navbar.Brand href="#" style={brandStyle}>ChaTraveler</Navbar.Brand>
        <Navbar.Toggle aria-controls="main" />
        <Navbar.Collapse id="main">
          <Nav
            className="me-auto my-2 my-lg-0"
            style={{ maxHeight: '200px' }}
            navbarScroll
          >
            <Nav.Link href="mypage" style={linkStyle}>My Page</Nav.Link>
            <Nav.Link href="login" style={linkStyle}>Log In</Nav.Link>
          </Nav>
          <Form className="d-flex">
            <Form.Control
              type="search"
              placeholder="Search"
              className="me-2"
              aria-label="Search"
            />
            <Button variant="outline-success">Search</Button>
          </Form>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Title;





// import React, { useState } from "react";
// import { Link } from "react-router-dom";
// import { firestore } from "../firebase_config";
// import { firebase } from "../firebase_config";
// import DatePicker from "react-datepicker";
// import "react-datepicker/dist/react-datepicker.css";
// import classNames from "classnames";

// import Button from 'react-bootstrap/Button';
// import Container from 'react-bootstrap/Container';
// import Form from 'react-bootstrap/Form';
// import Nav from 'react-bootstrap/Nav';
// import Navbar from 'react-bootstrap/Navbar';
// import 'bootstrap/dist/css/bootstrap.min.css';


// function Title() {
//   const [startDate, setStartDate] = useState(null);
//   const [endDate, setEndDate] = useState(null);
//   const [showCalendar, setShowCalendar] = useState(false);
//   const [selectedDates, setSelectedDates] = useState(null);
//   const [isHovered, setIsHovered] = useState(false);

//   const handleDateChange = (dates) => {
//     const [start, end] = dates;
//     setStartDate(start);
//     setEndDate(end);
//   };

//   const toggleCalendar = () => {
//     setShowCalendar(!showCalendar);
//   };

//   const handleSearch = () => {
//     setShowCalendar(false);
//     const formattedStart = startDate ? startDate.toLocaleDateString() : '';
//     const formattedEnd = endDate ? endDate.toLocaleDateString() : '';
//     const selectedDatesText = `${formattedStart} - ${formattedEnd}`;
//     setSelectedDates(selectedDatesText);
//   };

//   const navbarStyle = {
//     backgroundColor: 'white',
//     paddingLeft: '2rem',
//     paddingRight: '2rem',
//     marginLeft: 'auto',
//     marginRight: 'auto',
//     maxWidth: '1200px', /* App.js의 가로 사이즈와 동일하게 설정 */
//   };

//   const brandStyle = {
//     fontSize: '36pt',
//     color: '#00bfa5',
//   };

//   const linkStyle = {
//     color: '#a1887f',
//   };

//   const searchBoxStyle = {
//     backgroundColor: 'transparent',
//     color: '#a1887f',
//     cursor: 'pointer',
//     padding: '0.5rem 1rem',
//     display: 'inline-block',
//     marginRight: '1rem',
//     transition: 'background-color 0.3s ease',
//   };

//   const dropdownStyle = {
//     position: 'absolute',
//     top: '100%',
//     left: 0,
//     zIndex: 1,
//     marginTop: '0.5rem',
//   };

//   const dropdownCalendarStyle = {
//     backgroundColor: '#fff',
//     border: '2px solid #a1887f',
//     borderRadius: '5px',
//     padding: '1rem',
//     boxShadow: '0 2px 4px rgba(0, 0, 0, 0.2)',
//   };

//   const handleMouseEnter = () => {
//     setIsHovered(true);
//   };

//   const handleMouseLeave = () => {
//     setIsHovered(false);
//   };

//   return (
//     <Navbar style={navbarStyle} expand="lg">
//       <Container>
//         <Navbar.Brand style={brandStyle}>
//           <Link to="/" style={linkStyle}>
//             My App
//           </Link>
//         </Navbar.Brand>
//         <Navbar.Toggle aria-controls="navbar-nav" />
//         <Navbar.Collapse id="navbar-nav">
//           <Form className="d-flex">
//             <div
//               className={classNames("position-relative", { "show-calendar": showCalendar })}
//               onMouseEnter={handleMouseEnter}
//               onMouseLeave={handleMouseLeave}
//             >
//               <div
//                 className={classNames("search-box", { "hovered": isHovered })}
//                 style={searchBoxStyle}
//                 onClick={toggleCalendar}
//               >
//                 {showCalendar ? (
//                   <span>날짜 선택 ▲</span>
//                 ) : (
//                   <span>날짜 선택 ▼</span>
//                 )}
//               </div>
//               {showCalendar && (
//                 <div className="dropdown" style={dropdownStyle}>
//                   <div style={dropdownCalendarStyle}>
//                     <DatePicker
//                       selected={startDate}
//                       startDate={startDate}
//                       endDate={endDate}
//                       onChange={handleDateChange}
//                       selectsRange
//                       inline
//                     />
//                     <Button variant="outline-success" onClick={handleSearch}>
//                       Search
//                     </Button>
//                   </div>
//                 </div>
//               )}
//             </div>
//             {selectedDates && (
//               <div style={searchBoxStyle}>
//                 {selectedDates}
//               </div>
//             )}
//           </Form>
//         </Navbar.Collapse>
//       </Container>
//     </Navbar>
//   );
// }

// export default Title;

