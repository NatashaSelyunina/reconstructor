import { createSlice } from "@reduxjs/toolkit";
import api from "../axios.config";

export const sendCart = (cartItems) => {
  return async () => {
    try {
      
      await api.post("/saveCart", { cartItems });
      console.log("Cart saved !");
    } catch (error) {
      console.error("Error", error);
    }
  };
};

const cartSlice = createSlice({
  name: "cart",
  initialState: {
    cartItems: localStorage.getItem("cartItems") ? 
    JSON.parse(localStorage.getItem("cartItems"))
    : [],
    cartTotalQuantity: 0,
    cartTotalAmount: 0,
  },
  reducers: {
    addItemInCart: (state, action) => {
      state.cartItems.push(action.payload);
    },
    addToCart: (state, action) => {
      const itemIndex = state.cartItems.findIndex(
        (item) => item.id === action.payload.id
      );
      if (itemIndex >= 0) {
        state.cartItems[itemIndex].cartQuantity += 1;
      } else {
        const tempDish = { ...action.payload, cartQuantity: 1 };
        state.cartItems.push(tempDish);
      };
    },
    deleteItemFromCart: (state, action) => {
      state.cartItems = state.cartItems.filter(
        (item) => item.id !== action.payload
      );
    },
    removeOneItemFromCart: (state, action) => {
      const existingIndex = state.cartItems.findIndex(
        (item) => item.id === action.payload
      );

      if (existingIndex >= 0) {
        if (state.cartItems[existingIndex].cartQuantity > 1) {
          state.cartItems[existingIndex].cartQuantity -= 1;
        } else {
          state.cartItems.splice(existingIndex, 1);
        }
      }
    },
  },
});

export const { addItemInCart, deleteItemFromCart, removeOneItemFromCart, addToCart } =
  cartSlice.actions;
export default cartSlice.reducer;
