import React from "react";
import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import PaymentPage from "../../features/formPay/PaymentPage";
import styles from "./styles/Payment.module.css";

import { cartTotalAmount } from "../../components/utils/utils";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

const stripePromise = loadStripe("pk_test_f3duw0VsAEM2TJFMtWQ90QAT");

const Payment = () => {
  const items = useSelector((state) => state.cart.cartItems);
  const totalBill = cartTotalAmount(items);
  return (
    <div className={styles.paymentContainer}>
      {totalBill > 0 ? <span>Price: {totalBill}</span> : null}
      <Elements stripe={stripePromise}>
        <PaymentPage />
      </Elements>
      <div className={styles.button}>
        <Link to={"/menu"}>
          <button> Menu </button>
        </Link>
      </div>
    </div>
  );
};

export default Payment;
