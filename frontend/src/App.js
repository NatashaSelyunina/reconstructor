import React, { useEffect } from "react";
import Layout from "./components/Layout";
import AppRoutes from "./AppRoutes";
import { useDispatch } from "react-redux";
import { checkUserSession } from "./redux/userSlice";
import { useSelector } from "react-redux";

function App() {
  const isAuthenticated = useSelector((state) => state.user.isAuthenticated);
  const dispatch = useDispatch();


  useEffect(() => {
    dispatch(checkUserSession());
  }, [dispatch, isAuthenticated]);
  
  
  return (
    <Layout>
      <AppRoutes />
    </Layout>
  );
}

export default App;
