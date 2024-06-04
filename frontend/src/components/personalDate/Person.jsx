import React from "react";
import { useSelector } from "react-redux";

const Person = ({ email, password }) => {
  const { user } = useSelector((state) => state.user);

  return (
    <div>
      {user ? (
        <div>
          <p>ID:{user?.id} </p>
          <p>Email: {user?.email}</p>
          <p>Name: {user?.name}</p>
          <p>Surname: {user?.surname}</p>
          <p>Role: {user?.role?.name}</p>
          <p>Active: {user?.active ? "Yes" : "No"}</p>
        </div>
      ) : (
        <p>No user available</p>
      )}
    </div>
  );
};

export default Person;
