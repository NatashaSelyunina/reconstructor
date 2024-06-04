import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { categoriesRole } from "../utils/staffUtils";
import { FaMinus } from "react-icons/fa";
import { useTranslation } from "react-i18next";
import StaffDetails from "./StaffDetails";
import styles from "./styles/StaffRead.module.css";
import { readStaff } from "../../redux/actions/restaurantsStaffActions";

const StaffRead = () => {
  const { staff } = useSelector((state) => state.restaurants);
  const restaurantId = useSelector(state => state.restaurants.restaurantId);

  const { administrators, chefs, waiters, bartenders } = categoriesRole(staff);

  const { t } = useTranslation("translation");
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(readStaff({ restaurantId }));
  }, [dispatch, restaurantId]);

  const staffGroups = [
    { label: t("role.administrators"), data: administrators },
    { label: t("role.cooks"), data: chefs },
    { label: t("role.waiters"), data: waiters },
    { label: t("role.bartenders"), data: bartenders }
  ];

  return (
    <div className={styles.readStaffContainer}>
    {staffGroups?.map((group) => (
      group.data && group.data.length > 0 && (
        <div className={styles.adminContainer} key={group.label}>
          <h2>
            {group.label}
            <FaMinus /> {group.data.length}
          </h2>
          <ul>
            {group?.data?.map((person) => (
              <li key={person?.id}>
                <StaffDetails staff={person} />
              </li>
            ))}
          </ul>
        </div>
      )
    ))}
  </div>
  );
};

export default StaffRead;
