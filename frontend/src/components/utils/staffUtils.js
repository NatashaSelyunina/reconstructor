export const categoriesRole = (staff) => {
    const roles = {
      "ROLE_ADMINISTRATOR": "administrators",
      "ROLE_CHEF": "chefs",
      "ROLE_WAITER": "waiters",
      "ROLE_BARTENDER": "bartenders"
    };
  
    if (!staff || staff.length === 0) {
      return Object.keys(roles).reduce((acc, role) => {
        acc[roles[role]] = [];
        return acc;
      }, {});
    }
  
    const readStaffByRole = Object.keys(roles).reduce((acc, role) => {
      acc[roles[role]] = staff.filter(person => person.role && person.role.name === role);
      return acc;
    }, {});
  
    return readStaffByRole;
  };
 
