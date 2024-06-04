import React from "react";
import styles from "./RestaurantHeader.module.css";
import logoRats from "../../assets/logoRats.png";
import restaurant from "../../assets/restaurant.webp";
import { FaEnvelope, FaPhone, FaGlobe } from "react-icons/fa";

const RestaurantHeader = () => {
  const phoneNumberRestaurant = {
    phoneNumber: "+39 055 1234567",
  };

  const websiteRestaurant = {
    website: "https://reconstructor.me/",
  };

  const handleCall = () => {
    window.location.href = `tel:${phoneNumberRestaurant.phoneNumber}`;
  };

  return (
    <div className={styles.bigContainer}>
      <div>
        <div className={styles.containerImg}>
          <img src={restaurant} alt="restaurant"></img>
        </div>
        <div className={styles.logo}>
          <img src={logoRats} alt="logo"></img>
        </div>
      </div>
      <div className={styles.nameAndAddress}>
        <h1>Rats Family</h1>
        <div className={styles.address}>
          <p>123 Via dei Fiori, Florence,</p>
          <p>Tuscany, Italy</p>
          <div>
            <FaPhone onClick={handleCall} style={{ cursor: "pointer" }} />
            <span>{phoneNumberRestaurant.phoneNumber}</span>
          </div>
          <div className={styles.socialLink}>
            <a href="mailto:RatsFamily@reconstructor.me">
              <FaEnvelope />
            </a>
            <a
              href={websiteRestaurant.website}
              target="_blank"
              rel="noreferrer"
            >
              <FaGlobe />
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RestaurantHeader;
