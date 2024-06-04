import ActivationPage from "./components/login/ActivationPage";
import LoginPage from "./components/login/LoginPage";
import ResetPasswordPage from "./components/login/ResetPasswordPage";
import MenuUpdate from "./components/menuUpdate/MenuUpdate";
import PersonalPage from "./components/personalDate/PersonalPage";
import StaffItem from "./components/staff/StaffItem";
import StaffOutput from "./components/staff/StaffOutput";
import StaffRead from "./components/staff/StaffRead";
import TableId from "./components/tables/TableId";
import TablesRead from "./components/tables/TablesRead";
import Administrator from "./pages/administrator/Administrator";
import Bartender from "./pages/bartender/Bartender";
import Chef from "./pages/chef/Chef";
import ErrorPage from "./pages/errorPage/ErrorPage";
import Home from "./pages/home/Home";
import AddMenu from "./pages/moderator/AddMenu";
import AddRestaurant from "./pages/moderator/AddRestaurant";
import Moderator from "./pages/moderator/Moderator";
import RestaurantItem from "./pages/moderator/RestaurantItem";
import RestaurantUpdate from "./pages/moderator/RestaurantUpdate";
import Payment from "./pages/payment/Payment";
import DishId from "./pages/restaurant/DishId";
import MenuPage from "./pages/restaurant/MenuPage";
import Waiter from "./pages/waiter/Waiter";
import ReadMenuTable from "./pages/readMenuTable/ReadMenuTable.jsx";


const routes = [
  { path: "/", element: <Home /> },
  { path: "/login", element: <LoginPage /> },
  { path: "/moderator", element: <Moderator /> },
  { path: "/restaurants/:restaurantId", element: <RestaurantItem /> },
  { path: "/restaurant-update/:restaurantId", element: <RestaurantUpdate /> },
  { path: "/menu/:restaurantId/:id", element: <ReadMenuTable /> },
  { path: "/add-restaurant", element: <AddRestaurant /> },
  { path: "/administrator", element: <Administrator /> },
  { path: "/waiter", element: <Waiter /> },
  { path: "/bartender", element: <Bartender /> },
  { path: "chef", element: <Chef /> },
  { path: "/:restaurantId/add-menu", element: <AddMenu /> },
  { path: "/:restaurantId/menu-update", element: <MenuUpdate /> },
  { path: "/:restaurantId/menu", element: <MenuPage /> },
  { path: "/:id/staff-item", element: <StaffItem /> },
  { path: "/:id/staff", element: <StaffOutput /> },
  { path: "/:restaurantId/staff-list", element: <StaffRead /> },
  { path: "/activate", element: <ActivationPage /> },
  { path: "/reset-password", element: <ResetPasswordPage /> },
  { path: "/dishes/:id", element: <DishId /> },
  { path: "/personal", element: <PersonalPage /> },
  { path: "/:restaurantId/payment", element: <Payment /> },
  { path: "/:restaurantId/tables", element: <TablesRead /> },
  { path: "/:id/table", element: <TableId /> },
  { path: "*", element: <ErrorPage /> },
];

export default routes;
