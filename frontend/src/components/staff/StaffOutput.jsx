import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { useTranslation } from "react-i18next";
import { FaEdit, FaMinus, FaPlus, FaTrash } from "react-icons/fa";
import styles from "./styles/StaffOutput.module.css";
import StaffItem from "./StaffItem";
import { categoriesRole } from "../utils/staffUtils";
import {
  readStaff,
  readStaffById,
} from "../../redux/actions/restaurantsStaffActions";

const StaffOutput = () => {
  const { staff } = useSelector((state) => state.restaurants);
  const restaurantId = useSelector((state) => state.restaurants.restaurantId);
  const { t } = useTranslation("translation");
  const dispatch = useDispatch();

  const { administrators, chefs, waiters, bartenders } = categoriesRole(staff);
  const staffId = useSelector((state) => state.user.id);
  const hasStaff = administrators.length > 0 || chefs.length > 0 || waiters.length > 0 || bartenders.length > 0;


  useEffect(() => {
    dispatch(readStaff({ restaurantId }));
  }, [dispatch, restaurantId]);

  useEffect(() => {
    dispatch(readStaffById({ staffId }));
  }, [dispatch, staffId]);

  return (
    <>
      {hasStaff && (
        <div className={styles.button}>
          <div>
            <button>
              <FaEdit />
            </button>
            <span>{t("moderator.buttonModifyEmployee")}</span>
          </div>
          <div>
            <button>
              <FaTrash />
            </button>
            <span>{t("moderator.buttonDeleteEmployee")}</span>
          </div>
          <div>
            <button>
              <FaPlus />
            </button>
            <span>{t("moderator.buttonSaveEmployee")}</span>
          </div>
        </div>
      )}
      <div className={styles.outputStaffContainer}>
        {administrators.length > 0 && (
          <div className={styles.adminContainer}>
            <h2>
              {t("role.administrators")}
              <FaMinus /> {administrators.length}
            </h2>
            <ul>
              {administrators.map((person) => (
                <li key={person.id}>
                  <StaffItem person={person} staffId={staffId} />
                </li>
              ))}
            </ul>
          </div>
        )}
        {chefs.length > 0 && (
          <div className={styles.cooksContainer}>
            <h2>
              {t("role.cooks")}
              <FaMinus /> {chefs.length}
            </h2>
            <ul>
              {chefs.map((person) => (
                <li key={person.id}>
                  <StaffItem person={person} staffId={staffId} />
                </li>
              ))}
            </ul>
          </div>
        )}
        {waiters.length > 0 && (
          <div className={styles.waitersContainer}>
            <h2>
              {t("role.waiters")}
              <FaMinus /> {waiters.length}
            </h2>
            <ul>
              {waiters.map((person) => (
                <li key={person.id}>
                  <StaffItem person={person} staffId={staffId} />
                </li>
              ))}
            </ul>
          </div>
        )}
        {bartenders.length > 0 && (
          <div className={styles.bartenderContainer}>
            <h2>
              {t("role.bartenders")}
              <FaMinus /> {bartenders.length}
            </h2>
            <ul>
              {bartenders.map((person) => (
                <li key={person.id}>
                  <StaffItem person={person} staffId={staffId} />
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>
    </>
  );
};

export default StaffOutput;
