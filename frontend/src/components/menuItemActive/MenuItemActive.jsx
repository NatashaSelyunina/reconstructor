import React, { useEffect, useState } from "react";
import styles from "./styles/MenuItemActive.module.css";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import Loader from "../loader/Loader";
import MenuSection from "../menuSection/MenuSection";
import { useTranslation } from "react-i18next";
import RoleButton from "../roleButton/RoleButton";
import { readMenuRestaurant, updateDishStatus } from "../../redux/actions/restaurantsMenuActions";
import AddMenu from "../../pages/moderator/AddMenu";

const MenuItemActive = ({ userRole }) => {
  const { menu, loading, error } = useSelector((state) => state.restaurants);
  const [dishStatus, setDishStatus] = useState({});
  const dispatch = useDispatch();
  const { t } = useTranslation("translation");

  useEffect(() => {
    dispatch(readMenuRestaurant());
  }, [dispatch]);

  const handleStatusChange = (sectionId, dishId, status) => {
    setDishStatus((prevStatus) => ({
      ...prevStatus,
      [sectionId]: {
        ...prevStatus[sectionId],
        [dishId]: status,
      },
    }));
    dispatch(updateDishStatus(sectionId, dishId, status));
  };

  return (
    <div className={styles.bigContainer}>
      {loading && <Loader />}
      {error && <p>Error: {error}</p>}
      {menu.length === 0
        ? <AddMenu/>
        : menu.map((section, sectionIndex) => (
            <MenuSection
              className={styles.container}
              key={sectionIndex}
              section={section}
              sectionIndex={sectionIndex}
              onStatusChange={handleStatusChange}
              dishStatus={dishStatus}
            />
          ))}
      <button onClick={handleStatusChange}>{t("editMenu")}</button>
      <RoleButton userRole={userRole} />
    </div>
  );
};

export default MenuItemActive;
