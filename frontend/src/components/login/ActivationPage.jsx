import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate} from 'react-router-dom';
import { toast } from 'react-toastify';
import { activateAccount } from '../../redux/userSlice';
import { useTranslation } from "react-i18next";
import styles from "./styles/ActivationPage.module.css";

const ActivationPage = () => {
  const { t } = useTranslation("translation");
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const validationCode = urlParams.get('validation-code');
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
      if (validationCode) {
     
        dispatch(activateAccount({validationCode}))
          .then(() => { 
            toast.success(t("registration.registeredSuccessfully"));
            setTimeout(() => {
              navigate('/');
            }, 8000);
          })
          .catch(error => {          
            toast.error('Activation failed:', error);
          });
      }
    }, [dispatch, validationCode, navigate, t]);


    useEffect(() =>{
      console.log("code" + validationCode)
    }, [validationCode])

  return (
    <div className={styles.activationPageContainer}>
      <p>{t("registration.welcomeTextActivationBody")}</p>
      
    </div>
  )
}

export default ActivationPage;
