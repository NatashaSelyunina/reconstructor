import { useState } from "react";
import { useTranslation } from "react-i18next";
import LoginForm from "./LoginForm";
import RegisterForm from "./RegisterForm";
import styles from "./styles/LoginPage.module.css";

const LoginPage = () => {
  const { t } = useTranslation("translation");
  const [isLogin, setIsLogin] = useState(true);

  return (
    <div className={styles.loginPageContainer}>
      <div className="container-fluid mt-3 row justify-content-center">
        <div className="col-md-4 card">
          <div className="card-body">
            <h2 className="text-center mb-4">
              {isLogin
                ? t("registration.login")
                : t("registration.registration")}
            </h2>
            {isLogin ? <LoginForm /> : <RegisterForm />}
            <p className="mt-3 text-center">
              {isLogin ? (
                <span
                  onClick={() => setIsLogin(false)}
                  className="text-muted"
                  style={{ cursor: "pointer" }}
                >
                  {t("text.noAccount")}
                </span>
              ) : (
                <span
                  onClick={() => setIsLogin(true)}
                  className="text-muted"
                  style={{ cursor: "pointer" }}
                >
                  {t("text.account")}
                </span>
              )}
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
