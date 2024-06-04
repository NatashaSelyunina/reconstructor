import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import {
  deleteTable,
  readTableById,
} from "../../redux/actions/restaurantsActions";
import styles from "./styles/TableId.module.css";
import { useTranslation } from "react-i18next";
import RoleButton from "../../features/button/roleButton/RoleButton";
import { Button } from "react-bootstrap";

const TableId = () => {
  const  {table}  = useSelector((state) => state.restaurants);
  const user = useSelector((state) => state.user.user);
  const userRole = user && user.role ? user.role.name : undefined;
  const { t } = useTranslation("translation");

  const { id } = useParams();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(readTableById( {id }));
    console.log("table id: -", id);
  }, [dispatch, id]);

  const handleDeleteTable = () => {
    console.log("delete tableID with Id:", id);
    dispatch(deleteTable({ id }));
  };

  return (
    <div key={table?.id} className={styles.tableIdContainer}>
      <h2>Table Details</h2>
      {t("tables.table")} {table?.number}{" "}
      {table?.active ? "Active" : "Inactive"},{" "}
      {table?.free ? "Free" : "Occupied"}
      <img
        src={table?.qrCodeImageUrl}
        alt={`QR Code for Table ${table?.number}`}
      />
      <div className={styles.removeTable}>
      <Button onClick={() => handleDeleteTable(table?.id)}>
        {t("tables.removeTable")}
      </Button>
      </div>
      
      <div className={styles.addMenuButtonContainer}>
        <RoleButton userRole={userRole} />
      </div>
    </div>
  );
};

export default TableId;
