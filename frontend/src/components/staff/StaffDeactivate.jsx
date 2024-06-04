import React, { useEffect } from "react";
import StaffDetails from "./StaffDetails";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import styles from "./styles/StaffDeactivate.module.css";
import { useTranslation } from "react-i18next";
import {
  activateStaff,
  deactivateStaff,
  readStaff,
} from "../../redux/actions/restaurantsStaffActions";
import { Button } from "react-bootstrap";
import { toast } from "react-toastify";

const StaffDeactivate = ({ restaurantId }) => {
  const { t } = useTranslation("translation");
  const dispatch = useDispatch();
  const currentStaff = useSelector((state) => state.restaurants.staff);

  useEffect(() => {
    dispatch(readStaff({ restaurantId }));
  }, [dispatch, restaurantId]);
  

  const handleStatusStaff = (id, active) => {
    if (active) {
      dispatch(deactivateStaff({ id }))
        .unwrap()
        .then(() => {
          console.log("Staff deactivated: ID ", id);
          toast.success(t("personal.staffDeactivate"));
        })
        .catch((error) => {
          console.error("Failed to deactivate staff: ", error);
          toast.error(`Failed to deactivate staff with ID ${id}: ${error.message}`);
        });
    } else {
      dispatch(activateStaff({ id }))
        .unwrap()
        .then(() => {
          console.log("Staff activated: ID ", id);
          toast.success(t("personal.staffActivate"));
        })
        .catch((error) => {
          console.error("Failed to activate staff: ", error);
          toast.error(`Failed to activate staff with ID ${id}: ${error.message}`);
        });
    }
  };

  return (
    <div className={styles.deactivateContainerStaff}>
      {currentStaff.map((staffStatus) => (
        <div key={staffStatus.id}>
          {console.log("id status deactivate", staffStatus.id)}
          <StaffDetails staff={staffStatus} />
          {staffStatus.active ? (
            <Button
              onClick={() =>
                handleStatusStaff(staffStatus.id, staffStatus.active)
              }
            >
              {t("inactive")}
            </Button>
          ) : (
            <Button
              onClick={() =>
                handleStatusStaff(staffStatus.id, staffStatus.active)
              }
            >
              {t("active")}
            </Button>
          )}
        </div>
      ))}
    </div>
  );
};

export default StaffDeactivate;
