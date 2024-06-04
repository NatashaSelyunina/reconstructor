import React, { useEffect } from 'react';
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
// import { useNavigate, useLocation } from 'react-router-dom';
import { checkUserSession } from "../../redux/userSlice";
// import navigateUserUtils from './navigateUserUtils';

function PrivateRoute({ children }) {
  const dispatch = useDispatch();
  // const navigate = useNavigate();
  // const location = useLocation();

  const isAuthenticated = useSelector((state) => state.user.isAuthenticated);
  // const user = useSelector((state) => state.user.user);
  // const restaurantId = useSelector(state => state.user.restaurantId);
  // const isClient = useSelector(state => state.user.isClient);


  useEffect(() => {
    dispatch(checkUserSession());
  }, [dispatch, isAuthenticated]);

  // useEffect(() => {
    // if (location.pathname.includes('menu')) {
    //   navigate(`${restaurantId}/menu`)
    // }
  
  //   if (isAuthenticated || user) {
  //     navigateUserUtils(navigate,  isClient, restaurantId);
  //   }
  // }, [isAuthenticated,  navigate, user, isClient, restaurantId, location.pathname]);

  return (
    <React.Fragment>
      {children}
    </React.Fragment>
  );
};

export default PrivateRoute;
