import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import styles from "./ReadRestaurantHeader.module.css";
import { FaEnvelope, FaGlobe, FaPhone } from "react-icons/fa";
import { readRestaurantById } from "../../redux/actions/restaurantsActions";



const ReadRestaurantHeader = () => {
  const { restaurant } = useSelector(
    (state) => state.restaurants
  );
  const restaurantId = useSelector(state => state.restaurants.restaurantId);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(readRestaurantById({ restaurantId }));
  }, [dispatch, restaurantId]);

  const handleCall = () => {
    if (restaurant?.phoneNumber) {
      window.location.href = `tel:${restaurant?.phoneNumber}`;
    }
  };


  return (
    <div className={styles.bigContainer}>
      
      {restaurant && (
        <div>
          <div className={styles.containerImg}>
            <img src={restaurant?.backgroundUrl} alt="restaurant" />
          </div>
          <div className={styles.logo}>
            <img src={restaurant?.logoUrl} alt="logo" />
          </div>
          <div className={styles.nameAndAddress}>
            <h1>{restaurant?.name}</h1>
            <div className={styles.address}>
              <p>{restaurant?.address}</p>
              <div>
                <FaPhone onClick={handleCall} style={{ cursor: "pointer" }} />
                <span>{restaurant?.phoneNumber}</span>
              </div>
              <div className={styles.socialLink}>
                <a href={`mailto:${restaurant?.email}`}>
                  <FaEnvelope />
                </a>
                <a href={restaurant?.website} target="_blank" rel="noreferrer">
                  <FaGlobe />
                </a>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default ReadRestaurantHeader;
