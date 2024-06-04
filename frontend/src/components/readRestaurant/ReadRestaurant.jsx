import React from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { FaEdit, FaEye, FaQrcode } from "react-icons/fa";
import Loader from "../../features/loader/Loader";
import styles from "./ReadRestaurants.module.css";
import { useTranslation } from "react-i18next";
import { AiOutlineMenu } from "react-icons/ai";

const ReadRestaurant = () => {
  const { restaurants, loading } = useSelector((state) => state.restaurants);
  const { t } = useTranslation("translation");

  return (
    <div>
      <div className={styles.restaurantsContainer}>
        {loading && <Loader />}
        {restaurants.length === 0 ? (
          <p>{t("registration.welcome")}</p>
        ) : (
          <ul className={styles.restaurantContainer}>
            {restaurants?.map((restaurant) => (
              <li className={styles.restaurantItem} key={restaurant.id}>
                <span>{restaurant?.code}</span>
                <span>{restaurant?.name}</span>
                <span>{restaurant?.phoneNumber}</span>
                <span>{restaurant?.website}</span>
                <span>{restaurant?.address}</span>

                <div className={styles.editButton}>
                  <Link
                    to={`/restaurants/${restaurant?.id}`}
                    title={t("moderator.viewRestaurant")}
                    className={styles.buttonLink}
                  >
                    <FaEye />
                  </Link>
                  <Link
                    to={"/restaurant-update/" + String(restaurant?.id)}
                    title={t("moderator.editRestaurant")}
                    className={styles.buttonLink}
                  >
                    <FaEdit />
                  </Link>
                  <Link
                    to={`/${restaurant?.id}/tables`}
                    title={t("moderator.viewQRCodes")}
                    className={styles.buttonLink}
                  >
                    <FaQrcode />
                  </Link>


                  <Link
                    to={`/${restaurant?.id}/menu`}
                    title={t("moderator.viewMenu")}
                    className={styles.buttonLink}
                  >
                    <AiOutlineMenu />

                  </Link>
                </div>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default ReadRestaurant;
