import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import styles from "./styles/AddRestaurant.module.css";
import PhotoUploader from "../../features/photo/PhotoUploader";
import { useDispatch } from "react-redux";
import {
  removeRestaurant,
  saveRestaurant,
  updateRestaurant,
} from "../../redux/actions/restaurantsActions";
import { useNavigate, useParams } from "react-router-dom";
import { useSelector } from "react-redux";
import { toast } from "react-toastify";
import { Modal, Button } from "react-bootstrap";

const AddRestaurant = () => {
  const { t } = useTranslation("translation");
  const { restaurant } = useSelector((state) => state.restaurants);
  const { restaurantId } = useParams();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [showModal, setShowModal] = useState(false);
  const [errors, setErrors] = useState({});

  const [restaurantData, setRestaurantData] = useState({
    name: "",
    phoneNumber: "",
    address: "",
    website: "",
    logoUrl: "",
    backgroundUrl: "",
    active: true,
  });

  useEffect(() => {
    if (restaurant && restaurantId) {
      setRestaurantData({
        name: restaurant.name,
        phoneNumber: restaurant.phoneNumber,
        address: restaurant.address,
        website: restaurant.website,
        logoUrl: restaurant.logoUrl,
        backgroundUrl: restaurant.backgroundUrl,
        active: restaurant.active,
      });
    }
  }, [restaurant, restaurantId]);

  const validate = (data) => {
    const errors = {};
    if (!data.name) errors.name = t("validation.nameRequired");
    if (!data.address) errors.address = t("validation.addressRequired");
    return errors;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const validationErrors = validate(restaurantData);

    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }
    const updatedData = { ...restaurantData, id: restaurantId };
    try {
      const action = restaurantId
        ? updateRestaurant(updatedData)
        : saveRestaurant(restaurantData);
      const resultAction = await dispatch(action).unwrap();

      if (resultAction && resultAction.id) {
        navigate(`/restaurant-update/${resultAction.id}`);
        toast.success(
          restaurantId
            ? t("personal.dataSuccessfullyUpdated")
            : t("personal.createdRestaurant")
        );
      }
    } catch (error) {
      const message = error.response?.data?.errors
        ? Object.values(error.response.data.errors).join(", ")
        : error.message || t("restaurant.noSaveRestaurant");
      toast.error(message);
    }
  };

  const handleDeleteRestaurant = () => {
    dispatch(removeRestaurant({ restaurantId }))
      .unwrap()
      .then(() => {
        toast.success(t("restaurant.removeRestaurant"));
        setShowModal(false);
      })
      .catch((error) => {
        toast.error(error.message);
      });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setRestaurantData({ ...restaurantData, [name]: value });
    if (errors[name]) {
      setErrors({ ...errors, [name]: null });
    }
  };

  const handlePhotoUploaded = (url) => {
    setRestaurantData((prevData) => ({ ...prevData, backgroundUrl: url }));
  };

  const handleLogoUploaded = (url) => {
    setRestaurantData((prevData) => ({ ...prevData, logoUrl: url }));
  };

  const handleShowModal = () => setShowModal(true);
  const handleCloseModal = () => setShowModal(false);

  return (
    <div className={styles.addRestaurantContainer}>
      <div className={styles.previewRestaurantDate}>
        {restaurantData.backgroundUrl && (
          <div className={styles.containerImg}>
            <img
              src={restaurantData.backgroundUrl}
              alt="Background"
              className={styles.uploadedImage}
            />
          </div>
        )}
        {restaurantData.logoUrl && (
          <div className={styles.logo}>
            <img
              src={restaurantData.logoUrl}
              alt="Logo"
              className={styles.uploadedImage}
            />
          </div>
        )}
        {restaurantData.name && (
          <div className={styles.nameRestaurant}>
            <h1>{restaurantData.name}</h1>
          </div>
        )}
        <div className={styles.previewTextInfo}>
          {restaurantData.address && <p> {restaurantData.address}</p>}
          {restaurantData.phoneNumber && <p> {restaurantData.phoneNumber}</p>}
          {restaurantData.website && <p> {restaurantData.website}</p>}
        </div>
      </div>
      <div>
        <form onSubmit={handleSubmit}>
          <div className={styles.addButton}>
            <PhotoUploader
              onPhotoUploaded={handlePhotoUploaded}
              name="backgroundUrl"
              urlHost="background"
              keyName="background"
              onChange={handleChange}
              className={styles.hiddenUploader}
            >
              <Button>
                {" "}
                {restaurantData.backgroundUrl
                  ? t("editPhoto")
                  : t("addPhoto")}{" "}
              </Button>
            </PhotoUploader>

            <PhotoUploader
              onPhotoUploaded={handleLogoUploaded}
              name="logoUrl"
              urlHost="logo"
              onChange={handleChange}
              keyName="logo"
              className={styles.logo}
            >
              <Button>
                {" "}
                {restaurantData.logoUrl ? t("editLogo") : t("addLogo")}
              </Button>
            </PhotoUploader>
          </div>
          <div className={styles.inputSocialDateRestaurant}>
            <input
              type="text"
              name="name"
              value={restaurantData.name}
              onChange={handleChange}
              placeholder={t("restaurantName")}
              className={errors.name ? styles.inputError : ""}
            />
            {errors.name && <p className={styles.errorText}>{errors.name}</p>}
            <input
              type="text"
              name="phoneNumber"
              value={restaurantData.phoneNumber}
              onChange={handleChange}
              placeholder={t("social.phone")}
            />
            <input
              type="text"
              name="address"
              value={restaurantData.address}
              onChange={handleChange}
              placeholder={t("social.address")}
              className={errors.address ? styles.inputError : ""}
            />
            {errors.address && (
              <p className={styles.errorText}>{errors.address}</p>
            )}

            <input
              type="text"
              name="website"
              value={restaurantData.website}
              onChange={handleChange}
              placeholder={t("social.website")}
            />
          </div>
          <Button
            type="submit"
            title={t("title.changeRestaurantData")}
            className={styles.deleteAndSaveButton}
          >
            {restaurantId
              ? t("moderator.editRestaurantData")
              : t("addRestaurant")}
          </Button>
        </form>
      </div>

      <div className={styles.deleteAndSaveButton}>
        {restaurantId ? (
          <>
            <Button onClick={handleShowModal}>{t("deleteRestaurant")}</Button>
            <Modal show={showModal} onHide={handleCloseModal}>
              <Modal.Header closeButton>
                <Modal.Title>
                  {" "}
                  <h1>{restaurantData?.name}</h1>
                </Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <p> {restaurantData?.address}</p>
                <p> {restaurantData?.phoneNumber}</p>
                <p> {restaurantData?.website}</p>
              </Modal.Body>
              <Modal.Footer>
                <Button
                  variant="secondary"
                  onClick={handleCloseModal}
                  className={styles.cancelButton}
                >
                  {t("restaurant.cancelDecision")}
                </Button>
                <Button
                  variant="danger"
                  onClick={handleDeleteRestaurant}
                  className={styles.dangerButton}
                >
                  {t("restaurant.confirmRestaurantDeletion")}
                </Button>
              </Modal.Footer>
            </Modal>
          </>
        ) : null}
      </div>
    </div>
  );
};

export default AddRestaurant;
