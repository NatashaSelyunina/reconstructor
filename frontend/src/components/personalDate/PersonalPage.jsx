import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { updateModerator } from "../../redux/actions/restaurantsModeratorActions";
import { FaTimes, FaUserAlt } from "react-icons/fa";
import { useTranslation } from "react-i18next";
import { logoutUser } from "../../redux/userSlice";
import { useNavigate } from "react-router-dom";
import ChangePassword from "./ChangePassword";
import UpdateProfile from "./UpdateProfile";
import styles from "./PersonalPage.module.css";
import { useSelector } from "react-redux";
import Loader from "../../features/loader/Loader";
import { toast } from "react-toastify";
import { changePassword } from "../../redux/actions/restaurantsStaffActions";

const PersonalPage = () => {
  const { t } = useTranslation("translation");
  const [isOpen, setIsOpen] = useState(false);
  const [isViewingProfile, setIsViewingProfile] = useState(false);
  const [isUpdatingProfile, setIsUpdatingProfile] = useState(false);
  const [isChangePassword, setIsChangePassword] = useState(false);
  const [isLogout, setIsLogout] = useState(true);

  const { user, loading, error } = useSelector((state) => ({
    user: state.user.user,
    loading: state.user.loading,
    error: state.user.error,
  }));

  const userRole = user?.role?.name;

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [userUpdateData, setUserUpdateData] = useState({
    name: user?.name,
    surname: user?.surname,
    dateOfBirth: user?.dateOfBirth,
  });

  useEffect(() => {
    setUserUpdateData({
      name: user?.name || "",
      surname: user?.surname || "",
      dateOfBirth: user?.dateOfBirth || "",
    });
  }, [user]);

  if (loading) {
    <div>
      <Loader />
    </div>;
  }
  if (error) {
    console.log(error);
  }

  const handleChange = (e) => {
    const { id, value } = e.target;
    setUserUpdateData((prev) => ({ ...prev, [id]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(updateModerator(userUpdateData))
      .unwrap()
      .then((userUpdateData) => {
        console.log(userUpdateData);
        toast.success(t("personal.dataSuccessfullyUpdated"));
      })
      .catch((error) => {
        console.error("Update failed:", error);
        toast.error(error.message);
      });
  };

  const handleLogout = () => {
    dispatch(logoutUser());
    setIsLogout(false);
    navigate("/");
  };

  const toggleMenu = () => {
    setIsOpen(!isOpen);
    setIsViewingProfile(false);
    setIsUpdatingProfile(false);
    setIsChangePassword(false);
  };

  const handleViewProfileClick = () => {
    setIsViewingProfile(true);
    setIsUpdatingProfile(false);
    setIsChangePassword(false);
  };

  const handleUpdateProfileClick = () => {
    setIsUpdatingProfile(true);
    setIsViewingProfile(false);
    setIsChangePassword(false);
  };

  const handleChangePassword = () => {
    setIsChangePassword(true);
    setIsUpdatingProfile(false);
    setIsViewingProfile(false);
  };

  const handleSubmitNewPassword = (newPassword, oldPassword) => {
    dispatch(changePassword({ newPassword, oldPassword }));
  };

  return (
    <>
      {isLogout && (
        <div className={`container-fluid ${styles.personalContainer}`}>
          {user ? (
            <div>
              <button
                onClick={toggleMenu}
                className={` mx-auto`}
                title={
                  isOpen
                    ? t("personal.closeCabinet")
                    : t("personal.openCabinet")
                }
              >
                {isOpen ? <FaTimes /> : <FaUserAlt />}
              </button>
            </div>
          ) : null}

          {isOpen && (
            <div className="text-center">
              <div className={styles.person} key={user?.id}>
                <p>
                  {t("user.welcome")}, {user?.name}!
                </p>
                {isViewingProfile && (
                  <div className={styles.containerPage}>
                    <p>{user?.surname}</p>
                    <p>{user?.dateOfBirth}</p>
                    <p>{user?.email}</p>
                  </div>
                )}
                {isUpdatingProfile && user && userRole === "ROLE_MODERATOR" && (
                  <UpdateProfile
                    handleChange={handleChange}
                    onSubmit={handleSubmit}
                    user={userUpdateData}
                  />
                )}
                {isChangePassword && (
                  <ChangePassword onSubmit={handleSubmitNewPassword} />
                )}

                <div className="row mt-3 justify-content-center">
                  <div className="col-md-4 mx-auto">
                    {!isViewingProfile && (
                      <button
                        onClick={handleViewProfileClick}
                        className="btn btn-outline-primary w-100"
                      >
                        {t("user.viewProfile")}
                      </button>
                    )}
                    {!isUpdatingProfile &&
                      user &&
                      userRole === "ROLE_MODERATOR" && (
                        <button
                          onClick={handleUpdateProfileClick}
                          className="btn btn-outline-primary w-100 mt-2"
                        >
                          {t("user.editProfile")}
                        </button>
                      )}
                    {!isChangePassword && (
                      <button
                        onClick={handleChangePassword}
                        className="btn btn-outline-primary w-100 mt-2"
                      >
                        {t("user.changePassword")}
                      </button>
                    )}
                    <button
                      onClick={handleLogout}
                      className="btn btn-outline-danger w-100 mt-2"
                    >
                      {t("user.logOut")}
                    </button>
                  </div>
                </div>
              </div>
            </div>
          )}
        </div>
      )}
    </>
  );
};

export default PersonalPage;
