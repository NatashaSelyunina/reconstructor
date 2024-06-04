import React from "react";
import styles from "./styles/DishInCart.module.css";
import { useDispatch, useSelector } from "react-redux";
import {
  addToCart,
  deleteItemFromCart,
  removeOneItemFromCart,
} from "../../redux/cartSlice";
import { FaMinus, FaPlus, FaTrash } from "react-icons/fa";

const DishInCart = ({ dish }) => {
  const items = useSelector((state) => state.cart.cartItems);
  const isItemInCart = items.some((item) => item.id === dish.id);

  const dispatch = useDispatch();

  const handleAddToCart = () => {
    dispatch(addToCart(dish));
  };

  const handleRemoveOne = () => {
    dispatch(removeOneItemFromCart(dish.id));
  };

  const handleDelete = () => {
    dispatch(deleteItemFromCart(dish.id));
  };

  return (
    <div className={styles.dishItem}>
      {isItemInCart ? (
        <>
          <button onClick={handleAddToCart}>
            <FaPlus />
          </button>

          <button onClick={handleRemoveOne}>
            <FaMinus />
          </button>

          <button onClick={handleDelete}>
            <FaTrash />
          </button>
        </>
      ) : (
        <button onClick={handleAddToCart}>
          <FaPlus />
        </button>
      )}
    </div>
  );
};

export default DishInCart;
