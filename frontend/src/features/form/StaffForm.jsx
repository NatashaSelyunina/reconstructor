import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { FaPlus } from "react-icons/fa";
import styles from "./styles/StaffForm.module.css";
import GeneratePassword from "../../components/generatePassword/GeneratePassword";
import { toast } from "react-toastify";


const StaffForm = ({ onSubmit, onCancel, initialValues = {}, initialRole = '' }) => {
  const { t } = useTranslation();

  const [name, setName] = useState(initialValues.name || "");
  const [surname, setSurname] = useState(initialValues.surname || "");
  const [dateOfBirth, setDateOfBirth] = useState(initialValues.dateOfBirth || "");
  const [role, setRole] = useState({ name: initialRole || "" });
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");


  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!name || !surname || !dateOfBirth || !role || !password || !confirmPassword) {
      toast.error(t("alert.fillFields"));
      return;
    }

    if (password !== confirmPassword) {
      toast.error(t("registration.passwordMismatch"));
      return;
    }
    const updatedPerson = {
      name,
      surname,
      dateOfBirth,
      role: role.name,
      password
    };

    onSubmit(updatedPerson);
    onCancel();
  };


  useEffect(() => {
    setName(initialValues.name || "");
    setSurname(initialValues.surname || "");
    setDateOfBirth(initialValues.dateOfBirth || "");
    setRole({ name: initialRole || "" });
  }, [initialValues, initialRole]);

  return (
    <div className={styles.staffContainer}>
      <form onSubmit={handleSubmit}>
        <div>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder={t("Name")}
          />
        </div>
        <div>
          <input
            type="text"
            value={surname}
            onChange={(e) => setSurname(e.target.value)}
            placeholder={t("Surname")}
          />
        </div>
        <div>
          <input
            type="date"
            value={dateOfBirth}
            onChange={(e) => setDateOfBirth(e.target.value)}
            className={styles.dataOfBirth}
          />
        </div>
        <div className="mb-2">
          <input
            type="text"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder={t("registration.password")}
            title={t("title.enterPasswordStaff")}
            
          />
        </div>
        <div className="mb-2">
          <input
            type="text"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            placeholder={t("addPersonal.repeatPassword")}
            title={t("title.enterConfirmPasswordStaff")}
          />
          
        </div>
        <GeneratePassword onPasswordGenerated={setPassword} />
        <div className={styles.selectRole}>
          <select
            value={role.name}
            onChange={(e) => setRole({ name: e.target.value })}
          >
            <option value="ROLE_ADMINISTRATOR">{t("role.administrator")}</option>
            <option value="ROLE_BARTENDER">{t("role.bartender")}</option>
            <option value="ROLE_CHEF">{t("role.chef")}</option>
            <option value="ROLE_WAITER">{t("role.waiter")}</option>
          </select>
        </div>
        <div>
          <button className={styles.button} type="primary" htmlType="submit">
            <FaPlus />
          </button>
        </div>
      </form>
      
    </div>
  );
};

export default StaffForm;
