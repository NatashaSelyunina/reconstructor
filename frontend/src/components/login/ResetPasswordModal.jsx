import React, { useState, useEffect } from "react";
import { Modal } from "react-bootstrap";
import { useTranslation } from "react-i18next";

import ResetPassword from "./ResetPassword";

const ResetPasswordModal = ({ show, handleClose }) => {
  const { t } = useTranslation("translation");
  const [timer, setTimer] = useState(300);

  useEffect(() => {
    let intervalId;

    if (show) {
      intervalId = setInterval(() => {
        setTimer((prevTimer) => prevTimer - 1);
      }, 1000);
    }

    return () => clearInterval(intervalId);
  }, [show]);

  useEffect(() => {
    if (timer === 0) {
      handleClose();
      setTimer(300);
    }
  }, [timer, handleClose]);

  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{t("registration.resetPassword")}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <ResetPassword />
      </Modal.Body>
      <Modal.Footer></Modal.Footer>
    </Modal>
  );
};

export default ResetPasswordModal;
