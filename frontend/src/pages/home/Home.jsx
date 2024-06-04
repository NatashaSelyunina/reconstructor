import React from "react";
import styles from "./Home.module.css";
import LoginPage from "../../components/login/LoginPage";

const Home = () => {


  return (
    <>
      <div className={styles.app}>
        <header className={styles.appHeader}>
          <h1>ReConstructor</h1>
          <h2>Rats Family</h2>
        </header>
        <div className={styles.login}>
          <LoginPage />
        </div>
      </div>
    </>
  );
};

export default Home;
