import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import CartMenu from "./CartMenu";
import { useTranslation } from "react-i18next";
import { sendCart } from "../../redux/cartSlice";
import styles from "./styles/DishCart.module.css";
import { toast } from "react-toastify";
import { useNavigate, useParams } from "react-router-dom";

const DishCart = () => {
  const items = useSelector((state) => state.cart.cartItems);
  const { t } = useTranslation("translation");
  const dispatch = useDispatch();
  const [isCartSend, setIsCartSend] = useState(false);
  const [isVisible, setIsVisible] = useState(true);
  const navigate = useNavigate();
  const {restaurantId} = useParams();


  const saveCartToDatabase = () => {
    if (items.length === 0) {
      // eslint-disable-next-line no-lone-blocks
      {
        <span>{t("text.cartIsEmpty")}</span>;
      }
      return;
    }
    dispatch(sendCart(items))
      .then(() => {
        setIsCartSend(true);
       
      })
      .catch((error) => {
        toast.error(error);
      });
  };

  const goToPayment = () => {
    navigate(`/${restaurantId}/payment`);
    setIsVisible(false);
  };

  if (!isVisible) return null;

  return (
    <div>
      <CartMenu dishes={items} onClick={() => null} />
      {items.length > 0 && (
        <div className={styles.buttonOrder}>
          {isCartSend ? (
            <button onClick={goToPayment}>{t("menu.payOrder")}</button>
          ) : (
            <button onClick={saveCartToDatabase} >
              {t("menu.confirmOrder")}
            </button>
          )}
        </div>
      )}
    </div>
  );
};

export default DishCart;
