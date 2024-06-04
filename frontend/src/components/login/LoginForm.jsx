import { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useDispatch, useSelector } from "react-redux";
import { loginUser } from "../../redux/userSlice";
import { Link, useNavigate } from "react-router-dom";
import navigateUserUtils from "../utils/navigateUserUtils";
import ResetPasswordModal from "./ResetPasswordModal";
import { toast } from "react-toastify";

const LoginForm = () => {
  const { loading } = useSelector((state) => state.user);
  const user = useSelector((state) => state.user.user);

  const userRole = user && user.role ? user.role.name : undefined;
  const { t } = useTranslation("translation");
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");

  const [showPassword, setShowPassword] = useState(false);
  const [showModal, setShowModal] = useState(false);

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleOpenModal = () => {
    setShowModal(true);
  };

  const handleTogglePassword = () => {
    setShowPassword(!showPassword);
  };

const handleLogin = async (e) => {
    e.preventDefault();

    try {

      const result = await dispatch(loginUser({ login, password }));
      const user = result.payload;
      if (loginUser.fulfilled.match(result)) {
        toast.success(t("registration.welcome"));
        navigateUserUtils(navigate, user);
      setLogin("");
      setPassword("");
      window.location.reload();
    } else {
      throw new Error("Authentication failed");
    }
    } catch (error) {
      if (error.response) {
        toast.error(t("registration.checkPassword"));
      } else {
        toast.error(t("registration.invalidCredentials"));
      }
    }
  };

  useEffect(() => {
    console.log("Role in component:", userRole);
    if (userRole ) {
      navigateUserUtils(navigate, userRole);
    }
  }, [ navigate, userRole]);

  return (
    <form onSubmit={handleLogin}>
      <div className="mb-3">
        <input
          type="text"
          className="form-control"
          value={login}
          placeholder={t("registration.login") + " / " + t("registration.email")}
          onChange={(e) => setLogin(e.target.value)}
          title={t("registration.placeholderEnterEmail")}
        />
      </div>
      <div className="mb-3">
        <input
          type={showPassword ? "text" : "password"}
          className="form-control"
          value={password}
          placeholder={t("registration.password")}
          onChange={(e) => setPassword(e.target.value)}
          title={t("registration.placeholderEnterPassword")}
        />
        <input
          type="checkbox"
          onChange={handleTogglePassword}
          checked={showPassword}
        />{" "}
        {t("registration.showPassword")}
      </div>
      <button
        type="submit"
        className="btn btn-primary w-100"
        disabled={loading}
      >
        {loading ? t("registration.loading") : t("registration.signIn")}
      </button>
      <div className="mb-4"></div>

      <Link onClick={handleOpenModal}>{t("registration.resetPassword")}</Link>
      <div>
        <ResetPasswordModal show={showModal} handleClose={handleCloseModal} />
      </div>
    </form>
  );
};

export default LoginForm;
