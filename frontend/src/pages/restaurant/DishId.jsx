import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams } from "react-router-dom";
import Loader from "../../features/loader/Loader";
import styles from "./styles/DishId.module.css";
import { readDishById } from "../../redux/actions/restaurantsMenuActions";

const DishId = () => {
  const { dish, loading, error } = useSelector((state) => state.restaurants);
  const dispatch = useDispatch();
  const { id } = useParams();

  useEffect(() => {
    dispatch(readDishById({id}));
  }, [dispatch, id]);

  return (
    <div className={styles.bigContainer}>
      {loading && <Loader />}
      {error && <p>Error: {error}</p>}
      <div className={styles.containerDish}>
        <p>{dish?.name}</p>
        {dish?.imageUrl && <img id="dish" src={dish.imageUrl} alt="dish" />}
        <p>{dish?.description}</p>
        <p>{dish?.weight}</p>
        <p>{dish?.price}</p>
        <Link to={"/"}>
          <button>Home</button>
        </Link>
      </div>
    </div>
  );
};

export default DishId;
