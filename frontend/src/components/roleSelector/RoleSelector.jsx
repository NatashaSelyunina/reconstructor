import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router-dom";

const RoleSelector = () => {
  const { t } = useTranslation("translation");
  const [role, setRole] = useState("");
  const navigate = useNavigate();

  const handleChange = (event) => {
    const selectedRole = event.target.value;
    setRole(selectedRole);

    switch (selectedRole) {
      case "administrator":
        navigate("administrator");
        break;
      case "moderator":
        navigate("moderator");
        break;
      case "waiter":
        navigate("waiter");
        break;
      case "chef":
        navigate("chef");
        break;
      case "bartender":
        navigate("bartender");
        break;
      default:
        break;
    }
  };
  return (
    <div>
      <select
        class="form-select"
        style={{ width: "250px" }}
        aria-label="Default select example"
        onChange={handleChange}
        value={role}
      >
        <option selected>{t("accessLevel")}:</option>
        <option value="administrator">{t("role.administrator")}</option>
        <option value="moderator">{t("role.moderator")}</option>
        <option value="waiter">{t("role.waiter")}</option>
        <option value="chef">{t("role.chef")}</option>
        <option value="bartender">{t("role.bartender")}</option>
      </select>
    </div>
  );
};

export default RoleSelector;
