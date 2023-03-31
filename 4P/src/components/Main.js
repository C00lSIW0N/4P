import React from "react";
import { Link } from "react-router-dom";


export const Main = () => {
  return (
    <div className="mainpage">
      <h1><Link to="/navpage">NavPage</Link></h1>
    </div>
  );
}
 
export default Main;