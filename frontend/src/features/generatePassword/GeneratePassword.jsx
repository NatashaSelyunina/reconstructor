import React from "react";
import { FaKey } from "react-icons/fa";
import { useTranslation } from "react-i18next";
import styles from "./GeneratePassword.module.css";
import { useDispatch } from "react-redux";
import { generatePassword } from "../../redux/actions/restaurantsModeratorActions";
import { toast } from "react-toastify";

const GeneratePassword = ({ onPasswordGenerated }) => {
  const { t } = useTranslation("translation");
  const dispatch = useDispatch();

  const handleGenerateClick = async () => {
    try {
      const resultAction = await dispatch(generatePassword());
      if (generatePassword.fulfilled.match(resultAction)) {
        onPasswordGenerated(resultAction.payload);
        toast.success("generate password kommt  🦄 ");
      } else {
        throw new Error(t("restaurant.noGenerationPassword"));
      }
    } catch (error) {
      alert(t("restaurant.errorGenerationPassword") + error.message);
    }
  };


  return (
    <div>
      <button
        className={styles.generateButton}
        onClick={handleGenerateClick}
        title={t("title.generatePassword")}
      >
        <FaKey />
      </button>
    </div>
  );
};

export default GeneratePassword;
