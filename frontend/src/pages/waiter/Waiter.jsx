import React, { useEffect } from "react";
import { useTranslation } from "react-i18next";
import {  useNavigate, useParams } from "react-router-dom";
import styles from "./Waiter.module.css";
import { useSelector } from "react-redux";
import ReadRestaurantHeader from "../../components/readRestaurantHeader/ReadRestaurantHeader";
import { useDispatch } from "react-redux";
import { readRestaurantById } from "../../redux/actions/restaurantsActions";
import { readMenuRestaurant } from "../../redux/actions/restaurantsMenuActions";
import { Button } from "react-bootstrap";

const Waiter = () => {
  const { t } = useTranslation("translation");


  const restaurantId = useSelector((state) => state.user.restaurantId);
  const { restaurant } = useSelector((state) => state.restaurants);
  const user = useSelector((state) => state.user.user);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { id } = useParams();

  useEffect(() => {
    if (restaurantId) dispatch(readRestaurantById({restaurantId}));
    console.log("restaurant Id in Waiter: ", restaurantId);
  }, [dispatch, restaurant, restaurantId]);

  useEffect(() => {
    dispatch(readMenuRestaurant({ restaurantId: id }));
  }, [dispatch, id, restaurantId]);

  const handleClickPath = (path) => {
    navigate(path);
  };

  if (!user) {
    return navigate("/");
  }
  
  return (
    <>
    <ReadRestaurantHeader/>
    
      <div className={styles.waiterContainer}>
        
      </div>
      <div className={styles.container}>
        <div className={styles.button}>
        <Button onClick={() => handleClickPath(`/${restaurantId}/menu`)}>
            {t("createOrder")}
          </Button>
        </div>
       
      </div>
    </>
  );
};

export default Waiter;
