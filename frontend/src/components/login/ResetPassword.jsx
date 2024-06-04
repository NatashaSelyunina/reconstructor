import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { resetPassword } from "../../redux/userSlice";
import { useTranslation } from "react-i18next";
import { toast } from "react-toastify";
import styles from "./styles/ResetPassword.module.css";

const ResetPassword = () => {
  const { t } = useTranslation("translation");
  const [email, setEmail] = useState("");
  const dispatch = useDispatch();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = dispatch(resetPassword(email));
      if (response === 200) {
        toast.success(t("registration.instructionsForActivating"));
      }
    } catch (error) {
      toast.error(t("registration.userNotExist"));
    }
  };

  return (
    <div className={styles.resetPasswordContainer}>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder={t("registration.email")}
          title={t("registration.placeholderEnterRegistrationEmail")}
          required
        />
        <button className="btn btn-primary custom-margin-left" type="submit">
          {t("registration.resetPassword")}
        </button>
      </form>
    </div>
  );
};

export default ResetPassword;
