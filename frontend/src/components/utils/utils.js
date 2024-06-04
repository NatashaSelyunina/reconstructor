export const cartTotalAmount = dishes => {
    return dishes.reduce((acc, dish) => acc += (dish.price * dish.cartQuantity), 0).toFixed(2);
  };
