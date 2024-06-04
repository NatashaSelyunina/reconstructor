import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import styles from "./Administrator.module.css";
import { useSelector } from "react-redux";
import StaffRead from "../../components/staff/StaffRead";
import StaffDeactivate from "../../components/staff/StaffDeactivate";
import { useDispatch } from "react-redux";
import { readRestaurantById } from "../../redux/actions/restaurantsActions";
import { readMenuRestaurant } from "../../redux/actions/restaurantsMenuActions";
import ReadRestaurantHeader from "../../components/readRestaurantHeader/ReadRestaurantHeader";
import { Button } from "react-bootstrap";
import StaffOutput from "../../components/staff/StaffOutput";
import TablesRead from "../../components/tables/TablesRead";

const Administrator = () => {
  const { t } = useTranslation("translation");

  const restaurantId = useSelector((state) => state.user.restaurantId);
  const { restaurants } = useSelector((state) => state.restaurants);
  const user = useSelector((state) => state.user.user);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const {id} = useParams();

  const [isStaffOpen, setIsStaffOpen] = useState(false);
  const [buttonTextStaffOpen, setButtonTextStaffOpen] = useState(t("editSta"));

  const [isStaffStatusOpen, setIsStaffStatusOpen] = useState(false);
  const [buttonStatusText, setButtonStatusText] = useState(t("editSta"));

  const [isViewTables, setIsViewTables] = useState(false);
  const [buttonViewTables, setButtonViewTables] = useState(t("tables.viewTable"));

  const [isChangeStaff, setIsChangeStaff] = useState(false);
  const [buttonChangeStaff, setButtonChangeStaff] = useState(t("editSta"));

  useEffect(() => {
    if (restaurantId) dispatch(readRestaurantById({restaurantId}));
    dispatch(readMenuRestaurant({ restaurantId }));
  }, [dispatch, restaurants, restaurantId, user, id]);


  const toggleFormStaffOpen = () => {
    setIsStaffOpen(!isStaffOpen);
    if (!isStaffOpen) {
      setIsStaffStatusOpen(false);
      setIsViewTables(false);
      setIsChangeStaff(false);
    }
    setButtonTextStaffOpen(!isStaffOpen ? t("hideStaff") : t("editSta"));
  };

  const toggleFormEditStatusStaffOpen = () => {
    setIsStaffStatusOpen(!isStaffStatusOpen);
    if (!isStaffStatusOpen) {
      setIsStaffOpen(false);
      setIsViewTables(false);
      setIsChangeStaff(false);
    }
    setButtonStatusText(!isStaffStatusOpen ? t("hideStaff") : t("changeStatus"));
  };

  const toggleFormViewTables = () => {
    setIsViewTables(!isViewTables);
    if (!isViewTables) {
      setIsStaffOpen(false);
      setIsStaffStatusOpen(false);
      setIsChangeStaff(false);
    }
    setButtonViewTables(!isViewTables ? t("hideStaff") : t("tables.viewTable"));
  };

  const toggleFormChangeStaff = () => {
    setIsChangeStaff(!isChangeStaff);
    if (!isChangeStaff) {
      setIsStaffOpen(false);
      setIsStaffStatusOpen(false);
      setIsViewTables(false);
    }
    setButtonChangeStaff(!isChangeStaff ? t("hideStaff") : t("editStaff"));
  };
  const handleClickPath = (path) => {
    navigate(path);
  };

  if (!user) {
    return navigate("/");
  }


  return (
    <>
      <ReadRestaurantHeader restaurantId={restaurantId}/>

      <div className={styles.adminContainer}>
        <div className={styles.adminContainerButton}>
          <Button onClick={() => toggleFormStaffOpen()}>
            {t(buttonTextStaffOpen)}
          </Button>

          <Button onClick={toggleFormEditStatusStaffOpen}>
            {t(buttonStatusText)}
          </Button>
          <Button onClick={() => handleClickPath(`/${restaurantId}/menu`)}>
            {t("createOrder")}
          </Button>
          <Button onClick={toggleFormViewTables}>
            {t(buttonViewTables)}
          </Button>
          <Button onClick={toggleFormChangeStaff}>
            {t(buttonChangeStaff)}
          </Button>
        </div>
        {isStaffOpen && (
          <div className={styles.addStaff}>
            <StaffRead restaurantId={restaurantId} />
          </div>
        )}

        {isStaffStatusOpen && (
          <div className={styles.addStaff}>
            <StaffDeactivate restaurantId={restaurantId} />
          </div>
        )}
        {isViewTables && (
          <div>
            <TablesRead restaurantId={restaurantId}/>
          </div>
        )}
        {isChangeStaff && (
          <div className={styles.addStaff}>
            <StaffOutput restaurantId={restaurantId} />
          </div>
        )}
         
      </div>
    </>
  );
};

export default Administrator;
