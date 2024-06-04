import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { FaEdit, FaPlus, FaTrash } from "react-icons/fa";
import styles from "./styles/StaffItem.module.css";
import {
  removeStaff,
  updateStaff,
} from "../../redux/actions/restaurantsStaffActions";
import { useTranslation } from "react-i18next";
import { toast } from "react-toastify";
import { useSelector } from "react-redux";
import { readRestaurantById } from "../../redux/actions/restaurantsActions";
import { useParams } from "react-router-dom";

const StaffItem = ({ person}) => {
  // const restaurantId = useSelector((state) => state.user.restaurantId);
  const { staff } = useSelector((state) => state.restaurants);
  const { t } = useTranslation();
  const [isEdit, setIsEdit] = useState(false);
  

  const {restaurantId} = useParams();
  const dispatch = useDispatch();

  const [localName, setLocalName] = useState(person.name);
  const [localSurname, setLocalSurname] = useState(person.surname);
  const [localDateOfBirth, setLocalDateOfBirth] = useState(person.dateOfBirth);
  const [localRole, setLocalRole] = useState(person.role.name);
  const today = new Date().toISOString().split("T")[0];

  useEffect(() => {
    if (restaurantId) dispatch(readRestaurantById({restaurantId}));
  }, [dispatch, staff, restaurantId, person.id]);


  // useEffect(() => {
  //   if (person?.id) {
  //     dispatch(readStaffById(person.id));
  //     console.log("staff ID:", person.id);
  //   }
  // }, [dispatch, person?.id]);

  
  const handleEditStaff = () => {
    setIsEdit(true);
  };

  const staffDelete = () => {
    dispatch(removeStaff(person.id))
      .then(() => {
        toast.success(t("personal.staffDeleted"));
      })
      .catch((error) => {
        toast.error(error.message);
      });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!localName || !localSurname) {
      toast.error(t("alert.fillFields"));
      return;
    }

    const updatedPerson = {
      id: person.id,
      name: localName,
      surname: localSurname,
      dateOfBirth: localDateOfBirth,
      role: { name: localRole },
    };
    console.log("state update:", updatedPerson);
    try {
      await dispatch(updateStaff(updatedPerson)).unwrap();
      toast.success("Staff updated successfully!");
      setIsEdit(false);
    } catch (error) {
      if (error.payload) {
        toast.error(error.payload.message);
      } else {
        toast.error(t("personal.staffUpdateError"));
      }
    }
  };

  return (
    <div className={styles.containerStaff}>
      {isEdit ? (
        <div className={styles.staffContainer}>
          <form onSubmit={handleSubmit}>
            <div className="mb-2">
              <input
                type="text"
                value={localName}
                onChange={(e) => setLocalName(e.target.value)}
                placeholder={t("Name")}
              />
            </div>
            <div className="mb-2">
              <input
                type="text"
                value={localSurname}
                onChange={(e) => setLocalSurname(e.target.value)}
                placeholder={t("Surname")}
              />
            </div>
            <div className="mb-2">
              <input
                type="date"
                value={localDateOfBirth}
                onChange={(e) => setLocalDateOfBirth(e.target.value)}
                className={styles.dataOfBirth}
                max={today}
              />
            </div>

            <div className={styles.selectRole}>
              <select
                className="mb-2"
                value={localRole}
                onChange={(e) => setLocalRole(e.target.value)}
              >
                <option value="ROLE_ADMINISTRATOR">{t("role.administrator")}</option>
                <option value="ROLE_BARTENDER">{t("role.bartender")}</option>
                <option value="ROLE_CHEF">{t("role.chef")}</option>
                <option value="ROLE_WAITER">{t("role.waiter")}</option>
              </select>
            </div>
            <div className={styles.button}>
              <button
                className={styles.button}
                type="submit"  
              >
                <FaPlus />
              </button>
            </div>
          </form>
        </div>
      ) : (
        <>
          <p>
            {person.name} {person.surname}
          </p>
          <div className={styles.button}>
            <button
              title={t("title.modifyEmployeeData")}
              onClick={handleEditStaff}
            >
              <FaEdit />
            </button>
            <button title={t("title.deleteEmployeeData")} onClick={staffDelete}>
              <FaTrash />
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default StaffItem;
