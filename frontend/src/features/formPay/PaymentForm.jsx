import styles from "./styles/PaymentForm.module.css";
import React from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const PaymentForm = ({restaurantId}) => {

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    toast.success("Your order has been sent to the waiter and chef!", {
      onClose: () => navigate(`/${restaurantId}/menu`),
      autoClose: 5000 
    })
  };


  return (
    <div>
      <div className={styles.paymentForm}>
        <div className={styles.container}>
          <div className="row justify-content-center">
            <div className="col-md-8">
              <div className="card mt-6">
                <div className="card-body">
                  <h5 className="card-title">Payment Details</h5>
                  <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                      <label htmlFor="cardName" className="form-label">
                        Full Name
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="cardName"
                        placeholder="Full Name"
                      />
                    </div>
                    <div className="mb-3">
                      <label htmlFor="cardNumber" className="form-label">
                        Card Number
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        id="cardNumber"
                        placeholder="0000 0000 0000 0000"
                      />
                    </div>
                    <div className="row mb-3">
                      <div className="col">
                        <label htmlFor="expiryDate" className="form-label">
                          Expiry Date
                        </label>
                        <input
                          type="text"
                          className="form-control"
                          id="expiryDate"
                          placeholder="MM/YY"
                        />
                      </div>
                      <div className="col">
                        <label htmlFor="cvv" className="form-label">
                          CVV
                        </label>
                        <input
                          type="number"
                          className="form-control"
                          id="cvv"
                          placeholder="CVV"
                        />
                      </div>
                    </div>
                    <button type="submit" className="btn btn-primary">
                      Submit Payment
                    </button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PaymentForm;
