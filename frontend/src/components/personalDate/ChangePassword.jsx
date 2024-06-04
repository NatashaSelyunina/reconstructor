import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import { toast } from "react-toastify";
import { useDispatch} from "react-redux";
import { changePassword } from "../../redux/actions/restaurantsStaffActions";

const ChangePassword = ({ onSubmit }) => {
  const { t } = useTranslation("translation");
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmNewPassword, setConfirmNewPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const dispatch = useDispatch();

  const handleTogglePassword = () => {
    setShowPassword(!showPassword);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (newPassword !== confirmNewPassword) {
      toast.error(t("registration.passwordMismatch"));
      return;
    }
  
    try {
      await dispatch(changePassword({ newPassword, oldPassword })).unwrap();
      toast.success(t("personal.dataSuccessfullyUpdated"));
    } catch (error) {
      toast.error(t("registration.passwordNotSecure"));
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="row justify-content-center">
        <div className="col-md-4 mx-auto">
          <div className="mb-3">
            <label htmlFor="newPassword" className="form-label">
              {t("registration.currentPassword")}
            </label>
            <input
              type={showPassword ? "text" : "password"}
              className="form-control"
              id="password"
              value={oldPassword}
              onChange={(e) => setOldPassword(e.target.value)}
              title={t("title.enterCurrentPassword")}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="newPassword" className="form-label">
              {t("registration.newPassword")}
            </label>
            <input
              type={showPassword ? "text" : "password"}
              className="form-control"
              id="newPassword"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              title={t("registration.newPassword")}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="confirmNewPassword" className="form-label">
              {t("addPersonal.repeatPassword")}
            </label>
            <input
              type={showPassword ? "text" : "password"}
              className="form-control"
              id="confirmNewPassword"
              value={confirmNewPassword}
              onChange={(e) => setConfirmNewPassword(e.target.value)}
              title={t("title.enterConfirmPasswordStaff")}
            />
            <div className="form-check">
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
          <button type="submit" className="btn btn-primary d-block w-100">
            {t("user.saveChanges")}
          </button>
        </div>
      </div>
    </form>
  );
};

export default ChangePassword;
