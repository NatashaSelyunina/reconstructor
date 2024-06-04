import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import { useTranslation } from "react-i18next";
import styles from "./MenuSelectButton.module.css";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { readRestaurantById } from "../../redux/actions/restaurantsActions";

export const MenuSelectButton = () => {
  const navigate = useNavigate();
  const { t } = useTranslation("translation");
  const dispatch = useDispatch();
  const { restaurants } = useSelector((state) => state.restaurants);
  const restaurantId = useSelector((state) => state.user.restaurantId);
  const restaurant = restaurants.find((res) => res.id === restaurantId);

  console.log("restaurants:", restaurants);
  console.log("restaurant ID:", restaurantId);

  const handleClickPath = (path) => {
    navigate(path);
  };

  useEffect(() => {
    dispatch(readRestaurantById(restaurantId));
  }, [dispatch, restaurants, restaurantId]);


  return (
    <div className={styles.container}>
      <div className={styles.button}>
        <div className={styles.restaurantItem}>
          <div>
            <Button onClick={() => handleClickPath(`/${restaurant.id}/menu`)}>
              {t("createOrder")}
            </Button>
          </div>
          <div>
            <Button onClick={() => handleClickPath("/menu-active")}>
              {t("itemActivity")}
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};
export default MenuSelectButton;
