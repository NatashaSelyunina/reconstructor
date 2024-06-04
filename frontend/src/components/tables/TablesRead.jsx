import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import {
  deleteTable,
  readRestaurantById,
  readTables,
} from "../../redux/actions/restaurantsActions";
import { Link } from "react-router-dom";
import Loader from "../../features/loader/Loader";
import { useTranslation } from "react-i18next";
import styles from "./styles/TablesRead.module.css";
import TableSave from "./TableSave";
import { toast } from "react-toastify";

const TablesRead = () => {
  const { tables, loading } = useSelector((state) => state.restaurants);
  const restaurantId = useSelector(state => state.restaurants.restaurantId);
  const { t } = useTranslation("translation");
  const dispatch = useDispatch();


  const { restaurants } = useSelector((state) => state.restaurants);

  useEffect(() => {
    dispatch(readRestaurantById({ restaurantId }));
    console.log("restaurant Id in Header: ", restaurantId);
  }, [dispatch, restaurants, restaurantId]);

  useEffect(() => {
    dispatch(readTables({ restaurantId }));
  }, [dispatch, restaurantId]);

  const handleDeleteTable = (id) => {
    console.log("delete table Id:", id);
    dispatch(deleteTable({ id }))
      .then(() => {
        toast.success(t("tables.deletedTable"));
      })
      .catch((error) => {
        console.error(error.message);
        toast.error("tables.noDeletedTable");
      });
  };

  return (
    <div className={styles.readTablesContainer}>
      {loading && (
        <div>
          <Loader />
        </div>
      )}
      {tables?.length > 0 ? (
        <div>
          <h2>{t("tables.tables")}</h2>
          <ul>
            {tables?.map((table) => (
              <li key={table?.id} className={styles.codContainer}>
                {t("tables.table")} {table?.number}{" "}
                <img
                  src={table?.qrCodeImageUrl}
                  alt={`QR Code for Table ${table?.number}`}
                />
                {table?.active ? "Active" : "Inactive"},{" "}
                {table?.free ? "Free" : "Occupied"}
                <Link to={`/${table?.id}/table`}>details</Link>
                <a
                  href={table?.qrCodeImageUrl}
                  download={`Table-${table?.number}-QRCode.png`}
                  target="_blank"
                  rel="noreferrer"
                >
                  {t("tables.downloadQR")}
                </a>
                <button onClick={() => handleDeleteTable(table?.id)}>
                  {t("tables.removeTable")}
                </button>
              </li>
            ))}
          </ul>
        </div>
      ) : (
        <TableSave restaurantId={restaurantId} />
      )}
    </div>
  );
};

export default TablesRead;
