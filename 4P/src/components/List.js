import React from "react";
import { Link } from "react-router-dom";


const List = () => {
    return (
      <>
        <h1>에약 장바구니</h1>
        <img src="img/logo192.png" />
        <h1><Link to='/payment'>결제 페이지로 이동</Link></h1>
      </>
    );
  }
export default List;