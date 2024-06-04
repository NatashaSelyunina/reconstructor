import React from "react";
import img from "../../assets/error.webp";
import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";
import styles from "./ErrorPage.module.css";

const ErrorPage = () => {
  const { t } = useTranslation("translation");
  return (
    <div className={styles.container}>
      <div className={styles.errorImg}>
        <img src={img} alt="Error" />
      </div>
      <div className={styles.errorText}>
        <p>{t("text.errorText")}</p>
      </div>

      <Link to="/">
        <button className="btn btn-primary mb-3">{t("button")}</button>
      </Link>
    </div>
  );
};

export default ErrorPage;
