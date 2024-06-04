import React from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { logoutUser } from "../../redux/userSlice";
import { FiLogOut } from "react-icons/fi";
import styles from "./styles/LogoutButton.module.css";

const LogoutButton = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleLogout = () => {
    dispatch(logoutUser());
    navigate("/");
  };

  
  return (
    <div className={styles.logoutButton}>
      <button onClick={handleLogout}>
        <FiLogOut />
      </button>
    </div>
  );
};

export default LogoutButton;
