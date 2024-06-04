import React, { useEffect, useRef, useState } from "react";
import MenuItem from "../../components/menuItem/MenuItem";
import { useDispatch, useSelector } from "react-redux";
import Loader from "../../features/loader/Loader";
import styles from "./styles/MenuPage.module.css";
import RoleButton from "../../features/button/roleButton/RoleButton";
import { readMenuRestaurant } from "../../redux/actions/restaurantsMenuActions";
import { useTranslation } from "react-i18next";
import { Button } from "react-bootstrap";
import { readRestaurantById } from "../../redux/actions/restaurantsActions";
import AddMenu from "../moderator/AddMenu";
import ReadRestaurantHeader from "../../components/readRestaurantHeader/ReadRestaurantHeader";


const MenuPage = ( ) => {
  const { menu, loading } = useSelector((state) => state.restaurants);
  const user = useSelector((state) => state.user.user);
  const userRole = user && user.role ? user.role.name : undefined;

  const { t } = useTranslation("translation");

  const sectionRefs = useRef({});
  const dispatch = useDispatch();
  const restaurantId = useSelector(state => state.restaurants.restaurantId);

  useEffect(() => {
    if (restaurantId) {
   
      dispatch(readRestaurantById({ restaurantId }));
      dispatch(readMenuRestaurant({ restaurantId }));
    }
  }, [dispatch, restaurantId]);

  const [isFormMenuAdd, setIsFormMenuAdd] = useState(false);
  const [buttonFormMenuAdd, setButtonFormMenuAdd] = useState(t("addMenu"));

  const handleClickAddFormMenu = () => {
    setIsFormMenuAdd(!isFormMenuAdd);
    setButtonFormMenuAdd(
      isFormMenuAdd ? t("addMenu") : t("registration.hideForm")
    );
  };

  const scrollToSection = (index) => {
    const ref = sectionRefs.current[index];
    if (ref) {
      ref.scrollIntoView({
        behavior: "smooth",
        block: "start",
      });
    }
  };

  return (
    <>

      <ReadRestaurantHeader />

      <div key={menu.id} className={styles.bigContainer}>
        {loading && <Loader />}
        {isFormMenuAdd && (
          <div>
            <AddMenu />
          </div>
        )}
        {menu.length === 0 ? (
          <div className={styles.addMenuButtonContainer}>
            <Button onClick={() => handleClickAddFormMenu()}>
              {t(buttonFormMenuAdd)}
            </Button>
          </div>
        ) : (
          menu.map((section, sectionIndex) => {
            const hasDishesInSection = section?.sections?.some(
              (subSection) => subSection?.dishes?.length > 0
            );
            if (!hasDishesInSection) {
              return null;
            }

            return (
              <div className={styles.menu} key={sectionIndex}>
                <h2 onClick={() => scrollToSection(sectionIndex)}>
                  {section?.name}
                </h2>
                {section?.sections?.map((subSection, subSectionIndex) => {
                  const hasDishesInSubSection = subSection?.dishes?.length > 0;
                  if (!hasDishesInSubSection) {
                    return null;
                  }

                  return (
                    <div
                      key={subSectionIndex}
                      ref={(el) => (sectionRefs.current[sectionIndex] = el)}
                    >
                      <h3>{subSection?.name}</h3>
                      <ul>
                        {subSection?.dishes?.map((dish) => (
                          <li key={dish.id}>
                            <MenuItem dish={dish} />
                          </li>
                        ))}
                      </ul>
                    </div>
                  );
                })}
              </div>
            );
          })
        )}
      </div>
      <div className={styles.addMenuButtonContainer}>
        <RoleButton userRole={userRole} />
      </div>
    </>
  );
};
export default MenuPage;
