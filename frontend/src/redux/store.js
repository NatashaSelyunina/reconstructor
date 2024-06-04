import { configureStore } from '@reduxjs/toolkit';
import userSlice from './userSlice';
import restaurantsSlice from './restaurantsSlice';
import themeSlice from './themeSlice';
import cartSlice from './cartSlice';


export const store = configureStore({
    reducer: {
      user: userSlice,
      restaurants: restaurantsSlice,
      theme: themeSlice,
      cart: cartSlice
    },
  });
