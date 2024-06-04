import React from "react";
import Header from "./header/Header";
import Footer from "./footer/Footer";
import Theme from "../theme/Theme";
import styles from "./Layout.module.css";
import PersonalPage from "./personalDate/PersonalPage";

const Layout = ({ children }) => {
  return (
    <React.Fragment>
      <div className={styles.bigContainer}>
        <Theme />
        <PersonalPage />
        <Header />

        <div className={styles.container}>{children}</div>

        <Footer />
      </div>
    </React.Fragment>
  );
};

export default Layout;
