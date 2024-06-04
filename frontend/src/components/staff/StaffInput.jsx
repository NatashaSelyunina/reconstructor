import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useTranslation } from "react-i18next";
import FormInputStaff from "../../features/form/FormInputStaff";
import styles from "./styles/StaffInput.module.css";
import { saveStaff } from "../../redux/actions/restaurantsStaffActions";
import { generatePassword } from "../../redux/actions/restaurantsModeratorActions";
import { readRestaurantById } from "../../redux/actions/restaurantsActions";
import { useSelector } from "react-redux";
import { FaKey } from "react-icons/fa";
import { toast } from "react-toastify";

const StaffInput = () => {
  const { t } = useTranslation("translation");
  const restaurantId = useSelector(state => state.restaurants.restaurantId);
  const dispatch = useDispatch();
  

  useEffect(() => {
    dispatch(readRestaurantById({ restaurantId }));
  }, [dispatch, restaurantId]);


  const handleFormSubmit = async (staffData) => {
    try {
      const resultAction = await dispatch(
        saveStaff({
          formData: staffData,
          restaurantId,
        })
      );
      if (saveStaff.fulfilled.match(resultAction)) {
          toast.success(t("personal.addStaff"));       
      } else {      
        if (resultAction.payload && resultAction.payload.error) {
          toast.error(resultAction.payload.error);
        } else {
          toast.error(t("registration.passwordNotSecure"));
        }
      }
    } catch (error) {    
      toast.error(t("registration.passwordNotSecure"));
    }
  };

  const generateUserPassword = (e) => {
    e.preventDefault();
    dispatch(generatePassword());
  };

  return (
    <div>
      <div className={styles.geNerateContainerText}>
      <button
        className={styles.generateButton}>
          <FaKey />
        </button>
        <span>{t("moderator.generatePassword")}</span>
      </div>
      <div className={styles.addStaff}>
        <FormInputStaff
          personRole={t("role.administrator")}
          buttonText="Add administrator"
          personRoles={t("role.administrators")}
          initialRole="ROLE_ADMINISTRATOR"
          onSubmit={handleFormSubmit}
          generatePassword={generateUserPassword}
        />

        <FormInputStaff
          personRole={t("role.chef")}
          personRoles={t("role.cooks")}
          initialRole="ROLE_CHEF"
          onSubmit={handleFormSubmit}
          generatePassword={generateUserPassword}
        />
        <FormInputStaff
          personRole={t("role.waiter")}
          personRoles={t("role.waiters")}
          initialRole="ROLE_WAITER"
          onSubmit={handleFormSubmit}
          generatePassword={generateUserPassword}
        />
        <FormInputStaff
          personRole={t("role.bartender")}
          personRoles={t("role.bartenders")}
          initialRole="ROLE_BARTENDER"
          onSubmit={handleFormSubmit}
          generatePassword={generateUserPassword}
        />
      </div>
    </div>
  );
};

export default StaffInput;
