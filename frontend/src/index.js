import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { store } from "./redux/store";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter } from "react-router-dom";
import { Provider } from "react-redux";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "bootstrap/dist/css/bootstrap.min.css";
import AppContainer from "./theme/AppContainer";
import Modal from "react-modal";
import { I18nextProvider } from "react-i18next";
import i18next from "./features/i18next/i18next";

Modal.setAppElement("#root");
const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <Provider store={store}>
    <BrowserRouter>
      <AppContainer>
        <I18nextProvider i18n={i18next}>
          <ToastContainer
            position="top-center"
            autoClose={4000}
            className="toastContainer"
            theme="light"
          />
          <App />
        </I18nextProvider>
      </AppContainer>
    </BrowserRouter>
  </Provider>
);
reportWebVitals();
