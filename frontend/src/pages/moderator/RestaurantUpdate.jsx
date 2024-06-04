import React, { useEffect, useState } from "react";
import styles from "./styles/RestaurantUpdate.module.css";
import { Link, useParams } from "react-router-dom";
import StaffOutput from "../../components/staff/StaffOutput";
import { useTranslation } from "react-i18next";
import StaffInput from "../../components/staff/StaffInput";
import { useDispatch } from "react-redux";
import { readRestaurantById } from "../../redux/actions/restaurantsActions";
import TableSave from "../../components/tables/TableSave";
import TablesRead from "../../components/tables/TablesRead";
import { Button } from "react-bootstrap";
import AddRestaurant from "./AddRestaurant";
import MenuCard from "../../components/menuCard/MenuCard";
import AddMenu from "./AddMenu";
import {setRestaurantId} from "../../redux/restaurantsSlice";

const RestaurantUpdate = () => {
  const { t } = useTranslation("translation");
  const { restaurantId } = useParams();
  const dispatch = useDispatch();

  const [isTableAdd, setTableAdd] = useState(false);
  const [buttonTableAdd, setButtonTableAdd] = useState(t("tables.addTable"));

  const [isStaffFormOpen, setIsStaffFormOpen] = useState(false);
  const [buttonStaffEditText, setButtonStaffEditText] = useState(t("editSta"));

  const [isMenuUpdate, setIsMenuUpdate] = useState(false);
  const [buttonMenuUpdateText, setButtonMenuUpdateText] = useState(
    t("menu.editMenu")
  );


  useEffect(() => {
    dispatch(setRestaurantId(restaurantId));
    dispatch(readRestaurantById({ restaurantId }));
  }, [dispatch, restaurantId]);


  const toggleTableAdd = () => {
    setTableAdd(!isTableAdd);
    setButtonTableAdd(
      isTableAdd ? t("tables.addTable") : t("tables.closeTableForm")
    );
  };

  const toggleFormStaffInputOpen = () => {
    setIsStaffFormOpen(!isStaffFormOpen);
    setButtonStaffEditText(
      isStaffFormOpen ? t("addStaff") : t("registration.hideForm")
    );
  };

  const toggleIsMenuUpdate = () => {
    setIsMenuUpdate(!isMenuUpdate);
    setButtonMenuUpdateText(
      isMenuUpdate ? t("menu.editMenu") : t("registration.hideForm")
    );
  };



  return (
    <div className={styles.bigContainer}>
      <AddRestaurant />

      <div className={styles.buttonAddStaffMenuTable}>
        <Button onClick={toggleFormStaffInputOpen}>
          {t(buttonStaffEditText)}
        </Button>
        <Button onClick={toggleTableAdd}>{t(buttonTableAdd)}</Button>
        <Button onClick={toggleIsMenuUpdate}>{t(buttonMenuUpdateText)}</Button>
        
         <AddMenu restaurantId={restaurantId} />
       
      </div>

      {isStaffFormOpen && (
        <div className={styles.addStaff}>
          <StaffInput  />
        </div>
      )}

      {isStaffFormOpen && (
        <div className={styles.staffOutput}>
          <StaffOutput />
        </div>
      )}

      {isTableAdd && (
        <div className={styles.addTableContainer}>
          <TableSave  />
          <TablesRead  />
        </div>
      )}

      {isMenuUpdate && (
        <div>
          <MenuCard restaurantId={restaurantId} />
        </div>
      )}

      <Link to={"/moderator"}>
      
          <button>Home</button>
        </Link>
    </div>
  );
};

export default RestaurantUpdate;
