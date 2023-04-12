import Container from 'react-bootstrap/Container';
import React from 'react'
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import 'bootstrap/dist/css/bootstrap.min.css';


function title() {
  return (
    <Navbar bg="light" variant="light">
        <Container>
          <Navbar.Brand href="/remain">Main</Navbar.Brand>
          <Nav className="mb-3">
            <Nav.Link href="/remain">List</Nav.Link>
            <Nav.Link href="/reservation">Reservation</Nav.Link>
            <NavDropdown
                    title="예약 및 취소"
                  >
                    <NavDropdown.Item href="/Confirmation">
                      Confirmation
                    </NavDropdown.Item>
                    <NavDropdown.Divider />
                    <NavDropdown.Item href="/Cancellation">
                      Cancellation
                    </NavDropdown.Item>
                  </NavDropdown>
          </Nav>
        </Container>
      </Navbar>
  )
}

export default title;