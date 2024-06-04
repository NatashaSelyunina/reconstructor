import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams } from "react-router-dom";
import Loader from "../../features/loader/Loader";
import styles from "./styles/RestaurantItem.module.css";
import { readRestaurantById } from "../../redux/actions/restaurantsActions";
import TablesRead from "../../components/tables/TablesRead";
import { useTranslation } from "react-i18next";
import { Button } from "react-bootstrap";
import StaffOutput from "../../components/staff/StaffOutput";
import StaffInput from "../../components/staff/StaffInput";
import StaffRead from "../../components/staff/StaffRead";
import MenuPage from "../../pages/restaurant/MenuPage";
import {setRestaurantId} from "../../redux/restaurantsSlice";


const RestaurantItem = () => {
  const { t } = useTranslation("translation");
  const { restaurant, loading } = useSelector((state) => state.restaurants);

  const { restaurantId } = useParams();
  const dispatch = useDispatch();

  const [activeView, setActiveView] = useState(null);

  const handleToggleView = (viewName) => {
    setActiveView(prevView => prevView === viewName ? null : viewName);
  };

  useEffect(() => {
    if (restaurantId) {
    dispatch(setRestaurantId(restaurantId));
    dispatch(readRestaurantById({ restaurantId }));
  }
}, [dispatch, restaurantId]);

  return (
    <div className={styles.bigContainer}>
      <div className={styles.restaurantItem}>
        {loading && <Loader />}

        <p>{restaurant?.code}</p>
        <p>{restaurant?.phoneNumber}</p>
        <p>{restaurant?.website}</p>
        <p>{restaurant?.address}</p>
        {restaurant?.logoUrl && (
          <img
            id="restaurant"
            src={restaurant.logoUrl}
            alt="restaurant"
            className={styles.logo}
          />
        )}
      </div>

      <div className={styles.buttonAddStaffMenuTable}>
        <Button onClick={() => handleToggleView("staff")}>
          {t("moderator.viewEmployees")}
        </Button>
        <Button onClick={() => handleToggleView("addStaff")}>
          {t("addStaff")}
        </Button>
        <Button onClick={() => handleToggleView("menu")}>
          {t("moderator.viewMenu")}
        </Button>
        <Button onClick={() => handleToggleView("staffInfo")}>
          {t("moderator.employeeInformation")}
        </Button>
        <Button onClick={() => handleToggleView("tables")}>
          {t("moderator.viewTableList")}
        </Button>
      </div>

      {activeView === "addStaff" && <StaffInput />}
      {activeView === "staff" && <StaffOutput  />}
      {activeView === "staffInfo" && <StaffRead  />}
      {activeView === "tables" && <TablesRead  />}
      {activeView === "menu" && <MenuPage  />}

      <div className={styles.buttonHome}>
        <Link to={"/moderator"}>
          <Button>{t("button")}</Button>
        </Link>
      </div>
    </div>
  );
};

export default RestaurantItem;
