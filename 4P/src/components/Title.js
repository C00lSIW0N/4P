import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { Navbar, Container, Nav, Form, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

function Title() {
  const navigate = useNavigate();

  const handleTitleClick = () => {
    navigate('/');
  };

  const navbarStyle = {
    backgroundColor: 'white',
    paddingLeft: '2rem',
    paddingRight: '2rem',
    marginLeft: 'auto',
    marginRight: 'auto',
    maxWidth: '1200px',
  };

  const brandStyle = {
    fontSize: '36pt',
    color: '#00bfa5',
    cursor: 'pointer',
  };

  const linkStyle = {
    color: '#a1887f',
  };

  return (
    <Navbar style={navbarStyle} bg="light" expand="lg">
      <Container fluid>
        <Navbar.Brand style={brandStyle} onClick={handleTitleClick}>
          ChaTraveler
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="main" />
        <Navbar.Collapse id="main">
          <Nav
            className="me-auto my-2 my-lg-0"
            style={{ maxHeight: '200px' }}
            navbarScroll
          >
            <Nav.Link href="mypage" style={linkStyle}>My Page</Nav.Link>
            <Nav.Link href="login" style={linkStyle}>Log Out</Nav.Link>
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
