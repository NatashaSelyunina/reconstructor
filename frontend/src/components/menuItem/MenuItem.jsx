import { useState } from "react";
import styles from "./styles/MenuItem.module.css";
import { Modal } from "react-bootstrap";
import DishCart from "../cart/DishCart";
import { useSelector } from "react-redux";
import { cartTotalAmount } from "../utils/utils";
import { useTranslation } from "react-i18next";
import MenuItemContent from "./MenuItemContent";
import DishInCart from "../cart/DishInCart";

const MenuItem = ({ dish }) => {
  const { t } = useTranslation("translation");

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleNameClick = () => setShow(true);

  const items = useSelector((state) => state.cart.cartItems);
  const totalBill = cartTotalAmount(items);

  return (
    <div key={dish.id}>
      <MenuItemContent dish={dish} handleNameClick={handleNameClick} />
      <div>
        <Modal
          show={show}
          onHide={handleClose}
          dialogClassName={styles.modalItem}
        >
          <Modal.Header closeButton>
            <Modal.Title> {dish.name}</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <div key={dish.id} className={styles.modalItem}>
              <p>
                {}
                {dish?.description}
              </p>
              <p>
                {t("menu.weight")}: {dish?.weight}
              </p>
              <p>
                {t("menu.price")}: {dish?.price} {t("menu.geld")}
              </p>
              {dish?.imageUrl && (
                <img
                  id="dish"
                  src={dish.imageUrl}
                  alt="dish"
                  className="img-fluid"
                />
              )}
            </div>
          </Modal.Body>
          <Modal.Dialog>
            {<DishInCart dish={dish} quantity={dish.cartQuantity} />}
          </Modal.Dialog>
          <Modal.Dialog>{<DishCart />}</Modal.Dialog>
          <Modal.Footer>
            {t("menu.total")}: {totalBill} {t("menu.geld")}
          </Modal.Footer>
        </Modal>
      </div>
    </div>
  );
};

export default MenuItem;
