const navigateUserUtils = (navigate, userRole, isAuthenticated, isClient, restaurantId) => {
  

//   if (isClient) {
//     navigate(`/${restaurantId}/menu`);
//     return;
// }

// if ( !userRole ) {
//     console.error("User role is undefined");
//     navigate("/");
//     return;
// }

if ( isClient && restaurantId) {
  navigate(`/${restaurantId}/menu`);
  return;
}

if (!userRole) {
  console.error("User role is undefined");
  navigate(`/${restaurantId}/menu`);
  return;
}


    switch (userRole) {
      case "ROLE_ADMINISTRATOR":
        navigate("/administrator");
        break;
      case "ROLE_MODERATOR":
        navigate("/moderator");
        break;
      case "ROLE_WAITER":
        navigate("/waiter");
        break;
      case "ROLE_BARTENDER":
        navigate("/bartender");
        break;
      case "ROLE_COOK":
        navigate("/chef");
        break;
        // case isClient:
        //   navigate(`/${restaurantId}/menu`);
        //   break;
      default:
        navigate("/");
    }
  };

  export default navigateUserUtils;
