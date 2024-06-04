import { useSelector } from 'react-redux';
import {useNavigate } from 'react-router-dom';


const RoleSelectorUser = () => {
    const { user} = useSelector(state => state.user);
    const navigate= useNavigate();

      switch (user?.role?.name) {
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
        case "ROLE_CHEF":
          navigate("/chef");
          break;
        default:
          navigate("/");
      }
    };
 

export default RoleSelectorUser
