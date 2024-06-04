import { useState } from "react";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { toast } from "react-toastify";
import { registerUser } from "../../redux/userSlice";
import { Modal } from "react-bootstrap";
import useResetForm from "../../features/helper/resetForm";

const RegisterForm = () => {
  const { loading, error } = useSelector((state) => state.user);
  const { t } = useTranslation("translation");

  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [showModal, setShowModal] = useState(false);

  const dispatch = useDispatch();

  const resetForm = useResetForm([
    setName,
    setSurname,
    setDateOfBirth,
    setDateOfBirth,
    setPassword,
    setConfirmPassword,
  ]);
  const today = new Date().toISOString().split("T")[0];

  const handleTogglePassword = () => {
    setShowPassword(!showPassword);
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      alert(t("registration.passwordMismatch"));
      return;
    }

    if (!name || !surname || !login || !password || !confirmPassword) {
      toast.error(t("text.registrationDetails"));
      return;
    }
    try {
      const response = await dispatch(
        registerUser({ name, surname, dateOfBirth, login, password })
      );

      if (response.error) {
        if (error.message === "Validation error") {
          toast.error(t("invalidEmailFormat"));
        } else {
          toast.error(t("registration.invalidEmailFormat"));
        }
      } else {
        if (response) {
          toast.success(t("registration.instructionsForActivating"));
          setShowModal(true);
          resetForm();
        }
      }
    } catch (error) {
      toast.error(t("registration.passwordNotSecure"));
    }
  };

  const handleClose = () => {
    setShowModal(false);
  };

  return (
    <>
      <form onSubmit={handleRegister}>
        <div className="mb-3">{t("registration.requiredFields")}</div>

        <div className={`mb-3 ${!name && "has-error"}`}>
          <input
            type="text"
            className={`form-control ${!name && "is-invalid"}`}
            value={name}
            placeholder={t("addPersonal.name") + " *"}
            onChange={(e) => setName(e.target.value)}
            title={t("registration.placeholderEnterName")}
          />
        </div>
        <div className={`mb-3 ${!surname && "has-error"}`}>
          <input
            type="text"
            className={`form-control ${!surname && "is-invalid"}`}
            value={surname}
            placeholder={t("addPersonal.surname") + " *"}
            onChange={(e) => setSurname(e.target.value)}
            title={t("registration.placeholderEnterSurname")}
          />
        </div>
        <div className="mb-3">
          <input
            type="date"
            className="form-control"
            value={dateOfBirth}
            onChange={(e) => setDateOfBirth(e.target.value)}
            title={t("registration.placeholderEnterDateOfBirth")}
            max={today}
          />
        </div>
        <div className={`mb-3 ${"has-error"}`}>
          <input
            type="text"
            className={`form-control ${!login && "is-invalid"}`}
            value={login}
            placeholder={t("registration.email") + " *"}
            onChange={(e) => setLogin(e.target.value)}
            title={t("registration.placeholderEnterEmail")}
          />
        </div>
        <div
          className={`mb-3 ${(!password || !confirmPassword) && "has-error"}`}
        >
          <input
            type={showPassword ? "text" : "password"}
            className={`form-control ${
              (!password || !confirmPassword) && "is-invalid"
            }`}
            value={password}
            placeholder={t("registration.password") + " *"}
            onChange={(e) => setPassword(e.target.value)}
            title={t("registration.ensureTheSecurity")}
          />
          <input
            type="checkbox"
            onChange={handleTogglePassword}
            checked={showPassword}
          />{" "}
          {t("registration.showPassword")}
        </div>
        <div className={`mb-3 ${!confirmPassword && "has-error"}`}>
          <input
            type={showPassword ? "text" : "password"}
            className={`form-control ${!confirmPassword && "is-invalid"}`}
            value={confirmPassword}
            placeholder={t("addPersonal.repeatPassword") + " *"}
            onChange={(e) => setConfirmPassword(e.target.value)}
            title={t("registration.placeholderRepeatPassword")}
          />
        </div>
        <button
          type="submit"
          className="btn btn-primary w-100"
          disabled={loading}
        >
          {loading ? t("register.loading") : t("registration.registration")}
        </button>
        {error && (
          <div className="alert alert-danger mt-3" role="alert">
            {error}
          </div>
        )}
      </form>
      <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>
            {t("registration.welcomeTextActivationHeader")}
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>{t("registration.welcomeTextActivationBody")}</p>
        </Modal.Body>
        <Modal.Footer>
          {t("registration.welcomeTextActivationFooter")}
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default RegisterForm;
