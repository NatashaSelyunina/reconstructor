import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useTranslation } from "react-i18next";
import styles from "./styles/MenuForm.module.css";
import { useNavigate, useParams } from "react-router-dom";
import DishComponent from "./DishComponent.jsx";
import { saveMenu } from "../../redux/actions/restaurantsMenuActions.js";
import { Button } from "react-bootstrap";
import { readRestaurantById } from "../../redux/actions/restaurantsActions.js";

const MenuForm = () => {
  const dispatch = useDispatch();
  const { t } = useTranslation("translation");
  const [showMenuForm, setShowMenuForm] = useState(false);
  const [dishForm, setDishForm] = useState(false);
  const navigate = useNavigate();
  const { restaurantId } = useParams();

  useEffect(() => {
    if (restaurantId) {
      dispatch(readRestaurantById({ restaurantId }));
    }
  }, [dispatch, restaurantId]);

  const [sections, setSections] = useState([
    {
      parentSectionId: 0,
      name: "",
      sections: [
        {
          name: "",
          dishes: [
            {
              name: "",
              description: "",
              weight: "",
              price: "",
              active: true,
              imageUrl: "",
            },
          ],
        },
      ],
      active: true,
    },
  ]);

  const handleChange = (e, sectionIndex, subSectionIndex, dishIndex) => {
    const { name, value, type, checked } = e.target;
    setSections((prevSections) =>
      prevSections.map((section, sIndex) => {
        if (sIndex !== sectionIndex) return section;
        const updatedSection = { ...section };
        if (subSectionIndex == null) {
          updatedSection[name] = type === "checkbox" ? checked : value;
          return updatedSection;
        }
        const updatedSubSections = updatedSection.sections.map(
          (subSection, ssIndex) => {
            if (ssIndex !== subSectionIndex) return subSection;
            if (dishIndex == null) {
              return {
                ...subSection,
                [name]: type === "checkbox" ? checked : value,
              };
            }
            const updatedDishes = subSection.dishes.map((dish, dIndex) => {
              if (dIndex !== dishIndex) return dish;
              return {
                ...dish,
                [name]: type === "checkbox" ? checked : value,
                active: true,
              };
            });
            return { ...subSection, dishes: updatedDishes };
          }
        );
        return { ...updatedSection, sections: updatedSubSections };
      })
    );
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("restaurant id add menu", restaurantId);
    console.log("save menu", sections);
    dispatch(saveMenu({ restaurantId: restaurantId, formData: sections }));
  };

  const handlePhotoUploaded = (
    url,
    sectionIndex,
    subSectionIndex,
    dishIndex
  ) => {
    setSections((currentSections) => {
      const updatedSections = currentSections.map((section, sIndex) => {
        if (sIndex !== sectionIndex) return section;

        const updatedSection = { ...section };
        const updatedSubSections = updatedSection.sections?.map(
          (subSection, ssIndex) => {
            if (ssIndex !== subSectionIndex) return subSection;

            const updatedDishes = subSection.dishes?.map((dish, dIndex) => {
              if (dIndex !== dishIndex) return dish;

              return { ...dish, imageUrl: url };
            });

            return { ...subSection, dishes: updatedDishes };
          }
        );

        updatedSection.sections = updatedSubSections;
        return updatedSection;
      });

      return updatedSections;
    });
  };

  // useEffect(() => {
  //   console.log(sections);
  // }, [sections]);

  const handleClick = () => {
    if (showMenuForm) {
      navigate(`/${restaurantId}/menu-update`);
    }
    setShowMenuForm(!showMenuForm);
  };

  const handleAddNewDish = (sectionIndex, subSectionIndex, dishIndex) => {
    const newDish = {
      name: "",
      description: "",
      weight: "",
      price: "",
      imageUrl: "",
      active: true,
    };

    const newSections = [...sections];
    newSections[sectionIndex].sections[subSectionIndex].dishes.push(newDish);
    setSections(newSections);
    setDishForm(true);
  };

  const handleAddNewSubSection = (sectionIndex) => {
    const newSubSection = {
      name: "",
      dishes: [],
    };

    const newSections = [...sections];
    newSections[sectionIndex].sections.push(newSubSection);
    setSections(newSections);
  };

  return (
    <div className={styles.bigContainer}>
      <div className={styles.addMenuButton}>
        <Button onClick={handleClick}>{t("menu.addMenu")}</Button>
      </div>

      {showMenuForm && (
        <div>
          {sections?.map((section, sectionIndex) => (
            <div className={styles.container} key={sectionIndex}>
              <input
                className={styles.sections}
                type="text"
                name="name"
                onChange={(e) => handleChange(e, sectionIndex)}
                placeholder={t("menu.section")}
              />

              {section?.sections?.map((subSection, subSectionIndex) => (
                <div key={subSectionIndex}>
                  <input
                    type="text"
                    name="name"
                    onChange={(e) =>
                      handleChange(e, sectionIndex, subSectionIndex)
                    }
                    placeholder={t("menu.subsection")}
                  />
                  <div />
                  <div className={styles.dishesContainer}>
                    {subSection?.dishes?.map((dish, dishIndex) => (
                      <DishComponent
                        key={dishIndex}
                        dish={dish}
                        sectionIndex={sectionIndex}
                        subSectionIndex={subSectionIndex}
                        dishIndex={dishIndex}
                        handleChange={handleChange}
                        handlePhotoUploaded={handlePhotoUploaded}
                      />
                    ))}
                  </div>

                  <div>
                    <Button
                      className={styles.button}
                      onClick={() =>
                        handleAddNewDish(sectionIndex, subSectionIndex)
                      }
                    >
                      {" "}
                      {t("menu.dishToSection")}
                    </Button>
                  </div>
                  <Button
                    className={styles.button}
                    onClick={() => handleAddNewSubSection(sectionIndex)}
                  >
                    {t("menu.addSubSection")}
                  </Button>
                </div>
              ))}
            </div>
          ))}
          {dishForm && <div></div>}
          <div className={styles.buttonSave}>
            <Button onClick={handleSubmit}>{t("menu.saveMenu")}</Button>
          </div>
        </div>
      )}
    </div>
  );
};

export default MenuForm;
