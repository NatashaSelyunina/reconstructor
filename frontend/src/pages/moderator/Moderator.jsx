import React, { useEffect } from "react";
import { useTranslation } from "react-i18next";
import Button from "../../features/button/Button";
import styles from "./styles/Moderator.module.css";
import { useDispatch, useSelector } from "react-redux";
import Loader from "../../features/loader/Loader";
import { useNavigate } from "react-router-dom";
import ReadRestaurant from "../../components/readRestaurant/ReadRestaurant";
import { readRestaurants } from "../../redux/actions/restaurantsActions";
import { FaArrowDown, FaArrowRight } from "react-icons/fa";


const Moderator = () => {

  const { t } = useTranslation("translation");
  const { restaurants, loading } = useSelector(
    (state) => state.restaurants
  );

  const user = useSelector((state) => state.user.user);
  const dispatch = useDispatch();
  const navigate = useNavigate();


  const addRestaurant = () => {
    navigate("/add-restaurant");
  };

  useEffect(() => {
    dispatch(readRestaurants());
  }, [dispatch]);

  if (!user) {
    return navigate("/");
  }

  return (
    <div className={styles.bigContainer}>
      <div className={styles.containerImg}>{t("welcome")}</div>

      <div className={styles.buttonModerator}>
        {restaurants.length === 0 ? (
          <Button onClick={addRestaurant}>{t("addRestaurant")}</Button>
        ) : (
          <>
            <Button onClick={addRestaurant}>{t("addRestaurant")}</Button>
          </>
        )}
      </div>

      <div className={styles.restaurantsContainer}>
        {loading && <Loader />}

        {restaurants.length === 0 ? (
          <div className={styles.welcomeContainer}>
            <div>
              <h1>{t("welcomeText.welcomeText")}</h1>
              <h3>{t("welcomeText.createRestaurant")}</h3>
            </div>

            <div className={styles.headerBodyFooterContainer}>
              <div className={styles.addRestaurantContainer}>
                <h2>{t("welcomeText.textAddRestaurant.header")}</h2>
                <p>
                  <FaArrowRight /> {t("welcomeText.textAddRestaurant.body1")}
                </p>
                <p>
                  <FaArrowRight /> {t("welcomeText.textAddRestaurant.body2")}
                </p>
                <p>
                  <FaArrowDown /> {""}
                  {t("welcomeText.textAddRestaurant.footer")}
                </p>
              </div>

              <div className={styles.addStaffContainer}>
                <h2>{t("welcomeText.textAddStaff.header")}</h2>
                <p>
                  <FaArrowRight /> {t("welcomeText.textAddStaff.body1")}
                </p>
                <p>
                  <FaArrowRight /> {t("welcomeText.textAddStaff.body2")}
                </p>
                <p>
                  <FaArrowDown /> {t("welcomeText.textAddStaff.footer")}
                </p>
              </div>

              <div className={styles.addTableContainer}>
                <h2>{t("welcomeText.textAddTable.header")}</h2>
                <p>
                  <FaArrowRight /> {t("welcomeText.textAddTable.body1")}
                </p>
              </div>

              <div className={styles.addMenuContainer}>
                <h2>{t("welcomeText.textAddMenu.header")}</h2>
                <p>
                  <FaArrowRight /> {t("welcomeText.textAddMenu.body1")}
                </p>
              </div>

              <div className={styles.welcomeTextEnd}>
                {" "}
                <p>
                  <FaArrowDown /> {t("welcomeText.textAddMenu.footer")}
                </p>
                <h3>{t("welcomeText.textEnd")}</h3>
              </div>
            </div>
          </div>
        ) : (
          <ReadRestaurant />
        )}
      </div>
    </div>
  );
};

export default Moderator;
