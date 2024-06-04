import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { validateRestoredPassword } from "../../redux/userSlice";
import styles from "./styles/ResetPasswordPage.module.css";

const ResetPasswordPage = () => {
  const { t } = useTranslation("translation");
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const validationCode = urlParams.get("validation-code");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleTogglePassword = () => {
    setShowPassword(!showPassword);
  };

  // useEffect(() => {
  //   if (validationCode || newPassword) {

  //     dispatch(validateRestoredPassword(validationCode, newPassword))
  //       .then(() => {
  //         toast.success(t("registration.registeredSuccessfully"));
  //         setTimeout(() => {
  //           navigate('/');
  //         }, 5000);
  //       })
  //       .catch(error => {
  //         toast.error('Activation failed:', error);
  //       });
  //   }
  // }, [dispatch, validationCode, newPassword, navigate, t]);

  const handleResetPassword = (e) => {
    e.preventDefault();

    if (newPassword !== confirmPassword) {
      toast.error(t("registration.passwordMismatch"));
      return;
    }

    if (!newPassword || !setNewPassword) {
      toast.error(t("text.registrationDetails"));
      return;
    }
    try {
      const response = dispatch(
        validateRestoredPassword({
          newPassword,
          oldPassword: null,
          validationCode,
        })
      );

      if (response.error) {
        if (response.error === "userAlreadyExists") {
          toast.error(t("registration.userAlreadyExists"));
        } else if (response.error === "unknownError") {
          toast.error(t("registration.unknownError"));
        } else {
          toast.error(t("registration.unknownError"));
        }
      } else {
        if (response) {
          toast.success(t("registration.registeredSuccessfully"));
          navigate("/");
        }
      }
    } catch (error) {
      toast.error(t("registration.registrationFailed"));
    }
  };

  return (
    <div className={styles.resetPasswordForm}>
      <form onSubmit={handleResetPassword} className={styles.form}>
        <div className="mb-3">
          <input
            type={showPassword ? "text" : "password"}
            className={`form-control ${!newPassword && "is-invalid"}`}
            value={newPassword}
            placeholder={t("registration.password") + " *"}
            onChange={(e) => setNewPassword(e.target.value)}
            title={t("registration.ensureTheSecurity")}
          />
          <div className="form-check mt-2">
            <input
              type="checkbox"
              className="form-check-input"
              id="showPassword"
              onChange={handleTogglePassword}
              checked={showPassword}
            />
            <label className="form-check-label" htmlFor="showPassword">
              {t("registration.showPassword")}
            </label>
          </div>
        </div>
        <div className="mb-3">
          <input
            type={showPassword ? "text" : "password"}
            className={`form-control ${!confirmPassword && "is-invalid"}`}
            value={confirmPassword}
            placeholder={t("addPersonal.repeatPassword") + " *"}
            onChange={(e) => setConfirmPassword(e.target.value)}
            title={t("registration.placeholderRepeatPassword")}
          />
        </div>
        <div className={styles.submitButtonContainer}>
          <button type="submit" className="btn btn-primary">
            {t("registration.saveChanges")}
          </button>
        </div>
      </form>
    </div>
  );
};

export default ResetPasswordPage;
