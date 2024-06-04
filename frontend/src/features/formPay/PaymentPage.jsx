import React from "react";
import { useStripe, useElements, CardElement } from "@stripe/react-stripe-js";
import PaymentForm from "./PaymentForm";
import styles from "./styles/PaymentPage.module.css";
import { useTranslation } from "react-i18next";
import Button from "../button/Button";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

const PaymentPage = ({restaurantId}) => {
  const stripe = useStripe();
  const elements = useElements();
  const { t } = useTranslation("translation");
  const navigate = useNavigate();


  const handleSubmit = async (event) => {
    event.preventDefault();

    if (!stripe || !elements) {
      return;
    }

    const { error } = await stripe.createPaymentMethod({
      type: "card",
      card: elements.getElement(CardElement),
    });

    if (error) {
      toast.error(error.message || "Произошла ошибка при обработке вашей карточки");
    } else {
      toast.success("Your order has been sent to the waiter and chef!", {
        onClose: () => navigate(`/${restaurantId}/menu`),
        autoClose: 5000 
      })
    };
  }
  const CARD_OPTIONS = {
    style: {
      base: {
        color: "#32325d",
        fontFamily: 'Arial, sans-serif',
        fontSmoothing: "antialiased",
        fontSize: "16px",
        "::placeholder": {
          color: "#aab7c4"
        },
        margin: '0 auto',
        padding: '10px 12px',
      },
      invalid: {
        color: "#fa755a",
        iconColor: "#fa755a"
      }
    }
  };

  return (
    <div className={styles.bigContainer}>
    <form onSubmit={handleSubmit}>   
      <div className={styles.paymentContainer}>
        <CardElement options={CARD_OPTIONS} className={styles.cardElement} />
        <div>
        <Button type="submit" disabled={!stripe}>
        {t("menu.payOrder")}
        </Button>
        </div>
      </div>
      </form>
      <PaymentForm />
   
    </div>
  );
};

export default PaymentPage;
