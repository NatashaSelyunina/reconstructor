const roles = new Set([
    "ROLE_MODERATOR",
    "ROLE_WAITER",
    "ROLE_ADMINISTRATOR",
    "ROLE_CHEF",
    "ROLE_BARTENDER"
  ]);

  export const isUserRole = (roleName) => {
    return roles.has(roleName);
  };
