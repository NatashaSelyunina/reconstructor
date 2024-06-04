import React from "react";
import { useDispatch } from "react-redux";
import { saveTable } from "../../redux/actions/restaurantsActions";
import { useTranslation } from "react-i18next";
import { Button } from "react-bootstrap";
import styles from "./styles/TableSave.module.css";
import { useParams } from "react-router-dom";
import { toast } from "react-toastify";

const TableSave = () => {
  const { t } = useTranslation("translation");

  const {restaurantId} = useParams();

  const dispatch = useDispatch();

  const handleSaveTable = (e) => {
    e.preventDefault();

    const tableData = {
      id: Math.floor(Math.random() * 100) + 1,
      number: Math.floor(Math.random() * 100) + 1,
      active: true,
      free: true,
    };
    console.log(tableData);
    dispatch(saveTable({ restaurantId, tableData }))
      .then(() => {
        toast.success(t("tables.saveTable"));
      })
      .catch((error) => {
        console.error(error.message);
        toast.error("tables.saveTablePleaseLater");
      });
  };

  return (
    <div className={styles.addTablesButton}>
      <Button onClick={handleSaveTable}>{t("tables.addTable")}</Button>
    </div>
  );
};

export default TableSave;
