import React from "react";
import { cartTotalAmount } from "../utils/utils";
import { useTranslation } from "react-i18next";
import styles from "./styles/CartMenu..module.css";
import CartItem from "./CartItem";


const CartMenu = ({ dishes }) => {
  const { t } = useTranslation("translation");


  return (
    <div className={styles.bigContainer}>
      <div className={styles.cartDish}>
        {dishes.length > 0 ? (
          dishes.map((dish, index) => (
            <div key={index}>
              <CartItem key={index} cartItem={dish} />
            </div>
          ))
        ) : (
          <span>{t("text.cartIsEmpty")}</span>
        )}
      </div>

      {dishes.length > 0 ? (
        <div className={styles.orderContainer}>
          <div className={styles.totalPriceContainer}>
            <span> {t("menu.total")}:</span>
            <span>
              {cartTotalAmount(dishes)} {t("menu.geld")}
            </span>
          </div>   
        </div>
      ) : null}
    </div>
  );
};

export default CartMenu;
