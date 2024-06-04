import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import { FaEdit, FaPlus, FaMinus, FaTrash } from "react-icons/fa";
import styles from "./styles/Form.module.css";
import PhotoUploader from "../photo/PhotoUploader";
import { useDispatch } from "react-redux";
import { saveDish } from "../../redux/actions/restaurantsMenuActions";
import { Button } from "react-bootstrap";


const FormDish = () => {
  const { t } = useTranslation("translation");

  const [formData, setFormData] = useState({
    name: "",
    description: "",
    weight: "",
    price: null,
    active: true,
    imageUrl: "",
  });


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handlePhotoUploaded = (url) => {
    setFormData((prevData) => ({ ...prevData, imageUrl: url }));
    console.log("menu photo upload", url);
  };
  const dispatch = useDispatch();


  const submitForm = (e) => {
    e.preventDefault();
    console.log(
      formData.imageUrl,
      formData.name,
      formData.weight,
      formData.price
    );
    dispatch(saveDish(formData));
    console.log(formData);
  };

  return (
    <div className={styles.bigContainer}>
      <div className={styles.container}>
        <input
          type="text"
          value={formData.name || ""}
          onChange={handleChange}
          name="name"
          placeholder={t("menu.dishName")}
        />
        <textarea
          value={formData.description || ""}
          onChange={handleChange}
          name="description"
          placeholder={t("menu.description")}
          className={styles.description}
        />
        <input
          type="text"
          value={formData.weight || ""}
          onChange={handleChange}
          name="weight"
          placeholder={t("menu.weight")}
        />
        <input
          type="number"
          value={formData.price || 0}
          onChange={handleChange}
          name="price"
          placeholder={t("menu.price")}
        />

        <PhotoUploader
          className={styles.container}
          onPhotoUploaded={handlePhotoUploaded}
          
                urlHost="menu"
                keyName="menu"
          name="menu"
          onChange={handleChange}>
            <Button>
                  {" "}
                  {formData.imageUrl
                    ? t("editPhoto")
                    : t("addPhoto")}{" "}
                </Button>
              </PhotoUploader>
      

        <select value={formData.active} onChange={handleChange} name="active">
          <option value={true}>true</option>
          <option value={false}>false</option>
        </select>

        <button onClick={submitForm}>
          <FaPlus />
        </button>
      </div>

      {formData && (
        <div>
          <h2>{formData?.name}</h2>
          <h3>{formData?.description}</h3>
          <h3>{formData?.weight}</h3>
          <h3>{formData?.price}</h3>

          <img alt="" src={formData?.imageUrl} width="200"></img>

          {formData.name && (
            <>
              <FaMinus />
              <button>
                <FaEdit />
              </button>
              <button>
                <FaTrash />
              </button>
            </>
          )}
        </div>
      )}
    </div>
  );
};
export default FormDish;
