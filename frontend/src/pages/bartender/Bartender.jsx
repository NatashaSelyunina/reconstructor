import React, { useEffect } from "react";
import { useTranslation } from "react-i18next";
import { useNavigate, useParams } from "react-router-dom";
import styles from "./Bartender.module.css";
import { useSelector } from "react-redux";
import MenuSelectButton from "../../components/menuSelectButton/MenuSelectButton.1";
import { useDispatch } from "react-redux";
import { readRestaurantById } from "../../redux/actions/restaurantsActions";
import { readMenuRestaurant } from "../../redux/actions/restaurantsMenuActions";
import ReadRestaurantHeader from "../../components/readRestaurantHeader/ReadRestaurantHeader";
import { Button } from "react-bootstrap";

const Bartender = () => {
  const { t } = useTranslation("translation");

  const restaurantId = useSelector((state) => state.user.restaurantId);
  const { restaurants } = useSelector((state) => state.restaurants);
  const user = useSelector((state) => state.user.user);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { id } = useParams();

  useEffect(() => {
    if (restaurantId) dispatch(readRestaurantById({restaurantId}));
    console.log("restaurant Id in Administrator: ", restaurantId);
  }, [dispatch, restaurants, restaurantId]);

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
     <ReadRestaurantHeader />
      <div className="bartenderContainer">
  
        
      </div>
      <div className={styles.container}>
        <div className={styles.button}>
          <MenuSelectButton />
        </div>
        <Button onClick={() => handleClickPath(`/${restaurantId}/menu`)}>
            {t("createOrder")}
          </Button>
      </div>
    </>
  );
};

export default Bartender;
