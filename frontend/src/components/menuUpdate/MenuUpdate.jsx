import React, { useEffect } from "react";
import styles from "./MenuUpdate.module.css";
import MenuCard from "../menuCard/MenuCard";
import { useDispatch } from "react-redux";
import { readRestaurantById } from "../../redux/actions/restaurantsActions";
import { useParams } from "react-router-dom";

const MenuUpdate = () => {
  const dispatch = useDispatch();
  const { restaurantId } = useParams();

  useEffect(() => {
    dispatch(readRestaurantById({ restaurantId }));
  }, [dispatch, restaurantId]);

  return (
    <div className={styles.bigContainer}>
      <MenuCard restaurantId={restaurantId} />
    </div>
  );
};
