import React from "react";
import styles from "./Button.module.css";

const Button = ({ onClick, children }) => {
  return (
    <div className={styles.buttonContainer}>
      <button onClick={onClick}>
        <span className={styles.actualText}>&nbsp;{children}&nbsp;</span>
      </button>
    </div>
  );
};

export default Button;
