import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { Modal } from "react-bootstrap";
import { useSelector } from "react-redux";
import { FaShoppingCart } from "react-icons/fa";
import DishCart from "../cart/DishCart";
import { cartTotalAmount } from "../utils/utils";
import styles from "./Header.module.css";

const Header = () => {
  const { i18n, t } = useTranslation("translation");
  const [selectedLanguage, setSelectedLanguage] = useState(i18n.language);
  const {restaurant} = useSelector((state) => state.restaurants)

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const items = useSelector((state) => state.cart.cartItems);
  const totalBill = cartTotalAmount(items);

  const languages = [
    { code: "en", name: "EN", flag: "🇬🇧" },
    { code: "de", name: "DE", flag: "🇩🇪" },
    { code: "ru", name: "RU", flag: "🇷🇺" },
    { code: "uk", name: "UA", flag: "🇺🇦" },
  ];

  useEffect(() => {
    setSelectedLanguage(i18n.language);
  }, [i18n.language]);

  const changeLanguage = (language) => {
    i18n.changeLanguage(language);
    localStorage.setItem("userLanguage", language);
    setSelectedLanguage(language);
  };

  return (
    <div className={styles.bigContainer}>
      <select
        className="form-select"
        aria-label="Default select example"
        onChange={(e) => changeLanguage(e.target.value)}
        value={selectedLanguage}
      >
        {languages.map((language) => (
          <option key={language.code} value={language.code}>
            {language.flag} {language.name}
          </option>
        ))}
      </select>

      <button className={styles.showCart} onClick={handleShow}>
        {" "}
        <div
          className={` ${items.length > 0 ? styles.itemsLength : styles.total}`}
        >
          {totalBill > 0 ? <span>{items.length}</span> : null}
        </div>
        {totalBill > 0 ? <FaShoppingCart className={styles.cartIcon} /> : null}
        {totalBill > 0 ? <span>{totalBill}</span> : null}
      </button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title><p>{restaurant?.name}</p></Modal.Title>
        </Modal.Header>
        <Modal.Body>
         
          <DishCart />
        </Modal.Body>

        <Modal.Footer>
          <span>
            {t("menu.total")}: {totalBill} {t("menu.geld")}
          </span>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default Header;
