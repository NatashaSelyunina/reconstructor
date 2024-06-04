import React from "react";
import { useNavigate } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { Button } from "react-bootstrap";

const RoleButton = ({ userRole }) => {
  const navigate = useNavigate();
  const { t } = useTranslation("translation");

  const handleClickRole = (path) => {
    navigate(path);
  };

  return (
    <div>
      {userRole === "ROLE_WAITER" ||
      userRole === "ROLE_ADMINISTRATOR" ||
      userRole === "ROLE_COOK" ||
      userRole === "ROLE_MODERATOR" ||
      userRole === "ROLE_BARTENDER" ? (
        <div>
          {userRole === "ROLE_ADMINISTRATOR" && (
            <Button onClick={() => handleClickRole("/administrator")}>
              {t("button")}
            </Button>
          )}
          {userRole === "ROLE_WAITER" && (
            <Button onClick={() => handleClickRole("/waiter")}>
              {t("button")}
            </Button>
          )}
          {userRole === "ROLE_BARTENDER" && (
            <Button onClick={() => handleClickRole("/bartender")}>
              {t("button")}
            </Button>
          )}
          {userRole === "ROLE_MODERATOR" && (
            <Button onClick={() => handleClickRole("/moderator")}>
              {t("button")}
            </Button>
          )}
          {userRole === "ROLE_COOK" && (
            <Button onClick={() => handleClickRole("/chef")}>
              {t("button")}
            </Button>
          )}
        </div>
      ) : null}
    </div>
  );
};

export default RoleButton;
