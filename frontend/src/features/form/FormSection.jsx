import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import { FaEdit, FaPlus } from "react-icons/fa";

const FormSection = () => {
  const { t } = useTranslation("translation");

  const [formData, setFormData] = useState({
    mainSections: '',
    subSections: '',
    nestedSections: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
    console.log(setFormData);
  };

  const isFormFilled =
    formData.mainSections.trim() !== '' ||
    formData.subSections.trim() !== '' ||
    formData.nestedSections.trim() !== '';
  console.log(isFormFilled);
  console.log(formData);

  return (
    <div>
      <div>
        <h2>{t("menu.section")}</h2>
        <input
          type="text"
          value={formData.mainSections || ''}
          onChange={handleChange}
          name="mainSections"
          placeholder={t("menu.section")}
        />
        <h2>{t("menu.subSection")}</h2>
        <input
          type="text"
          value={formData.subSections || ''}
          onChange={handleChange}
          name="subSections"
          placeholder={t("menu.subSections")}
        />
        <h2>{t("menu.nestedSections")}</h2>
        <input
          type="text"
          value={formData.nestedSections || ''}
          onChange={handleChange}
          name="nestedSections"
          placeholder={t("menu.nestedSections")}
        />
        {isFormFilled ? (
          <>
            <h2>Main Sections: {formData.mainSections}</h2>
            <h2>Sub Sections: {formData?.subSections}</h2>
            <h2>Nested Sections: {formData?.nestedSections}</h2>
            <button type="button">
              <FaEdit />
            </button>
          </>
        ) : (
          <button type="button">
            <FaPlus />
          </button>
        )}
      </div>
    </div>
  );
};

export default FormSection;
