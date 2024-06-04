import React from "react";
import styles from "./styles/StaffDetails.module.css";
import { useTranslation } from "react-i18next";

const StaffDetails = ({ staff }) => {
  const { t } = useTranslation("translation");

  return (
    <div className={styles.staffDetailsContainer}>
      <p>
        {t("addPersonal.name")}: {staff?.name}
      </p>
      <p>
        {t("addPersonal.surname")}: {staff?.surname}
      </p>
      <p>
        {t("addPersonal.dateOfBirth")}: {staff?.dateOfBirth}
      </p>
      <p>{"----------------"}</p>
      <p>Code: {staff?.code}</p>
      <p>{"-----------------"}</p>
      <p>Role: {staff?.role.name}</p>
      <p>Active: {staff?.active ? "Yes" : "No"}</p>
      <p>Working: {staff?.working ? "Yes" : "No"}</p>
    </div>
  );
};

export default StaffDetails;
