import React from "react";
import { FaMinus, FaPlus,  FaTrash } from "react-icons/fa";
import { useDispatch } from "react-redux";
import { removeOneItemFromCart, deleteItemFromCart, addToCart } from "../../redux/cartSlice";
import styles from "./styles/CartItem.module.css";
import { useTranslation } from "react-i18next";
import { Button } from "react-bootstrap";

const CartItem = ({ cartItem }) => {
  const dispatch = useDispatch();
  const {  t } = useTranslation("translation");

  const handleRemoveOne = () => {
    dispatch(removeOneItemFromCart(cartItem.id));
  };

  const handleDelete = () => {
    dispatch(deleteItemFromCart(cartItem.id));
  };

  const handleAddDish = () => {
    dispatch(addToCart(cartItem))
  }
  if (!cartItem || !cartItem.name || !cartItem.price || !cartItem.cartQuantity) {
    return null; 
  } 

  return (
    <div className={styles.cartItemContainer}>
      <div className={styles.nameDish}>
      <span>{cartItem.name}, </span>
      <p>{t("menu.itemCost")}:{cartItem.price}</p>
      <p>{t("menu.quantity")}: {cartItem.cartQuantity}</p>
      </div>
      <div className={styles.button}>
        <div>
        <Button onClick={handleAddDish}><FaPlus /></Button>
        </div>
      <div>
      <Button onClick={handleRemoveOne}><FaMinus /></Button>
      </div>
      <div>
      <Button onClick={handleDelete}> <FaTrash /></Button>
      </div>
    </div>
    </div>
  );
};

export default CartItem;
