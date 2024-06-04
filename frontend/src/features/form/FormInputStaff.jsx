import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import { FaCheck, FaPlus } from "react-icons/fa";
import styles from "./styles/FormInputStaff.module.css";
import GeneratePassword from "../generatePassword/GeneratePassword";
import { generatePassword } from "../../redux/actions/restaurantsModeratorActions";
import { toast } from "react-toastify";
import useResetForm from "../helper/resetForm";

const FormInputStaff = ({ personRole, personRoles, onSubmit, initialRole }) => {
  const { t } = useTranslation("translation");

  const [roles, setRoles] = useState([]);
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [editIndex, setEditIndex] = useState(null);
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const today = new Date().toISOString().split('T')[0];

  const resetForm = useResetForm([
    setName,
    setSurname,
    setDateOfBirth,
    setPassword,
    setConfirmPassword,
  ]);

  const saveChanges = () => {
    if (editIndex !== null) {
      const updatedRoles = [...roles];
      updatedRoles[editIndex] = {
        ...updatedRoles[editIndex],
        name,
        surname,
        dateOfBirth,
        password,
      };
      setRoles(updatedRoles);
      resetForm();
      setEditIndex(null);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!name || !surname || !initialRole || !password || !confirmPassword) {
      toast.error(t("alert.fillFields"));
      return;
    }

    if (password !== confirmPassword) {
      toast.error(t("registration.passwordMismatch"));
      return;
    }

    const staffData = {
      name,
      surname,
      dateOfBirth,
      password,
      role: { name: initialRole },
    };
    onSubmit(staffData);
    resetForm();
  };

  return (
    <div className={styles.container}>
      <div className="card-body flex-wrap">
        <div className={styles.staffContainer}>
          <form onSubmit={handleSubmit}>
            <h2>{personRole}</h2>
            <div className="mb-2">
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder={t("addPersonal.name")}
                title={t("title.enterNameStaff")}
              />
            </div>
            <div className="mb-2">
              <input
                type="text"
                value={surname}
                onChange={(e) => setSurname(e.target.value)}
                placeholder={t("addPersonal.surname")}
                title={t("title.enterSurnameStaff")}
              />
            </div>
            <div className="mb-2">
              <input
                type="date"
                value={dateOfBirth}
                onChange={(e) => setDateOfBirth(e.target.value)}
                className={styles.inputData}
                title={t("title.enterDateOfBirthStaff")}
                max={today}
              />
            </div>
            <div className="mb-2">
              <input
                type="text"
                value={password || generatePassword}
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
            <div className={styles.buttonGenerateAndAdd}>
              <GeneratePassword onPasswordGenerated={setPassword} />
              {editIndex !== null ? (
                <button onClick={saveChanges}>
                  <FaCheck />
                </button>
              ) : (
                <button
                  className={styles.button}
                  type="submit"
                  title={t("title.addStaff")}
                >
                  <FaPlus />
                </button>
              )}
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default FormInputStaff;
