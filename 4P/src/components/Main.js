import React from "react";
import { useNavigate } from "react-router-dom";


const Main = () => {
  const navigate = useNavigate();
 
  const navigateToNavPage = () => {
    navigate("/navpage");
  };
 
  return (
          <button><Link to= '/' className="links">NavPage</Link></button>
  );
};
 
export default Main;