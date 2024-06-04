import React from "react";
import { useTranslation } from "react-i18next";

const UpdateProfile = ({ onSubmit, user, handleChange }) => {
  const { t } = useTranslation("translation");
  const today = new Date().toISOString().split('T')[0];

  
  return (
    <form onSubmit={onSubmit}>
      <div className="row justify-content-center">
        <div className="col-md-4 mx-auto">
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              {t("addPersonal.name")}
            </label>
            <input
              type="text"
              className="form-control"
              id="name"
              value={user.name}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="surname" className="form-label">
              {t("addPersonal.surname")}
            </label>
            <input
              type="text"
              className="form-control"
              id="surname"
              value={user.surname}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="dateOfBirth" className="form-label">
              {t("addPersonal.dateOfBirth")}
            </label>
            <input
              type="date"
              className="form-control"
              id="dateOfBirth"
              value={user.dateOfBirth}
              onChange={handleChange}
              max={today}
            />
          </div>

          <button type="submit" className="btn btn-primary d-block w-100">
            {t("user.saveChanges")}
          </button>
        </div>
      </div>
    </form>
  );
};

export default UpdateProfile;
