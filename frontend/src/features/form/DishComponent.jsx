import React from "react";
import { useTranslation } from "react-i18next";
import PhotoUploader from "../photo/PhotoUploader";
import styles from "./styles/DishComponent.module.css";
import { Button } from "react-bootstrap";

const DishComponent = ({
  dish,
  sectionIndex,
  subSectionIndex,
  dishIndex,
  handleChange,
  handlePhotoUploaded,
}) => {
  const { t } = useTranslation("translation");

  return (
    <div className={styles.bigContainer}>
      <div className={styles.container}>
        <input
          type="text"
          name="name"
          value={dish.name || ""}
          onChange={(e) =>
            handleChange(e, sectionIndex, subSectionIndex, dishIndex)
          }
          placeholder={t("menu.name")}
        />

        <PhotoUploader
          onPhotoUploaded={(url) =>
            handlePhotoUploaded(url, sectionIndex, subSectionIndex, dishIndex)
          }
          key={`${sectionIndex}-${subSectionIndex}-${dishIndex}`}
          className={styles.photo}
          name={`imageUrl-${sectionIndex}-${subSectionIndex}-${dishIndex}`}
          onChange={(e) =>
            handleChange(e, sectionIndex, subSectionIndex, dishIndex)
          }
          urlHost="menu"
          keyName="menu"
        >
          <Button>{dish.imageUrl ? t("editPhoto") : t("addPhoto")}</Button>
        </PhotoUploader>

        <textarea
          value={dish.description || ""}
          onChange={(e) =>
            handleChange(e, sectionIndex, subSectionIndex, dishIndex)
          }
          name="description"
          placeholder={t("menu.description")}
        />
        <input
          type="text"
          value={dish.weight || ""}
          onChange={(e) =>
            handleChange(e, sectionIndex, subSectionIndex, dishIndex)
          }
          name="weight"
          placeholder={t("menu.weight")}
        />
        <input
          type="number"
          value={dish.price}
          onChange={(e) =>
            handleChange(e, sectionIndex, subSectionIndex, dishIndex)
          }
          name="price"
          placeholder={t("menu.price")}
        />
      </div>
    </div>
  );
};

export default DishComponent;
