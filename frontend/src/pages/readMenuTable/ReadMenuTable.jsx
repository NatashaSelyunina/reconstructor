import React, { useEffect } from "react";
import styles from "./ReadMenuTable.module.css";
import { useParams } from "react-router-dom";
import {
  readRestaurantById,
  readTableById,
} from "../../redux/actions/restaurantsActions";
import { useDispatch } from "react-redux";
import { readMenuRestaurant } from "../../redux/actions/restaurantsMenuActions";
import MenuPage from "../restaurant/MenuPage";
import {setRestaurantId} from "../../redux/restaurantsSlice";



const ReadMenuTable = () => {
  
  const { restaurantId } = useParams();
  const { id } = useParams();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(setRestaurantId(restaurantId));
    dispatch(readRestaurantById({ restaurantId }));
    dispatch(readMenuRestaurant({ restaurantId }));
    dispatch(readTableById({ id }));
  }, [dispatch, id, restaurantId]);

  return (
    <div className={styles.readMenuTable}>
      <MenuPage />
    </div>
  );
};

export default ReadMenuTable;
