import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import styles from "./styles/AddMenu.module.css";
import MenuForm from "../../features/form/MenuForm";
import { Button } from "react-bootstrap";

const AddMenu = () => {
  const { t } = useTranslation("translation");
  const [sectionsCount, setSectionsCount] = useState(1);

  const handleAddSection = () => {
    setSectionsCount(sectionsCount + 1);
  };

  return (
    <div className={styles.addMenu}>
      <div className={styles.formMenu}>
        {Array.from({ length: sectionsCount }, (_, index) => (
          <MenuForm key={index} />
        ))}
        <Button className={styles.buttonAddSection} onClick={handleAddSection}>
          {t("menu.addSection")}
        </Button>
      </div>
    </div>
  );
};

export default AddMenu;
