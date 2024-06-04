import { useState } from "react";
import { FaMinus, FaPlus, FaSearch, FaTrash} from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import {
  addToCart,
  removeOneItemFromCart,
  deleteItemFromCart,
} from "../../redux/cartSlice";

import { useTranslation } from "react-i18next";
import styles from "./styles/MenuItemContext.module.css";

const MenuItemContent = ({ dish, handleNameClick }) => {
  const { t } = useTranslation("translation");
  const [isNameHover, setIsNameHover] = useState(false);
  const handleNameEnter = () => setIsNameHover(true);
  const handleNameLeave = () => setIsNameHover(false);

  const items = useSelector((state) => state.cart.cartItems);
  const isItemInCart = items.some((item) => item.id === dish.id);

  const dispatch = useDispatch();

  const handleClick = (e) => {
    e.stopPropagation();
    if (isItemInCart) {
      dispatch(removeOneItemFromCart(dish.id));
    } else {
      dispatch(addToCart(dish));
    }
  };

  const removeAllDish = (e) => {
    e.stopPropagation();
    dispatch(deleteItemFromCart(dish.id));
  };

  const addDishToCart = (e) => {
    e.stopPropagation();
    dispatch(addToCart(dish));
  };

  return (
    <div className={styles.menuItem}>
      <div className={styles.dishContainer}>
        <h2
          onClick={handleNameClick}
          onMouseEnter={handleNameEnter}
          onMouseLeave={handleNameLeave}
        >
          {dish.name}
          <p className={styles.description}>{dish?.description}</p>
          {dish?.imageUrl && (
            <img
              id="dish"
              src={dish.imageUrl}
              alt="dish"
              className={styles.dishPhoto}
            />
          )}
        </h2>
        <div className={styles.searchButton}>
          <button onClick={handleNameClick}>
            <FaSearch />
          </button>
        </div>
        {isNameHover && (
          <div className={styles.descriptionModal}>
            <p>{dish?.description}</p>
          </div>
        )}
        <p className={styles.smallDescription}>
          {t("menu.weight")}: {dish?.weight}
        </p>
        <p className={styles.smallDescription}>
          {t("menu.price")}: {dish?.price}
        </p>
        <div className={styles.addDishButton}>
          {isItemInCart ? (
            <>
              <button onClick={addDishToCart}>
                <FaPlus />
              </button>
              <button onClick={handleClick}>
                <FaMinus />
              </button>
              <button onClick={removeAllDish}>
                <FaTrash />
              </button>
            </>
          ) : (
            <button onClick={addDishToCart}>
              <FaPlus />
            </button>
          )}
        </div>
      </div>
    </div>
  );
};

export default MenuItemContent;
