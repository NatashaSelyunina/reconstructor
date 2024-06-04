import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../axios.config";

export const readMenuRestaurant = createAsyncThunk(
  "restaurants/readMenuRestaurant",
  async ({ restaurantId, rejectWithValue }) => {
    try {
      const response = await api.get(`/menu/${restaurantId}`);
      return response.data;
    } catch (error) {
      return rejectWithValue("menu restaurant",error.message);
    }
  }
);

export const readDishAll = createAsyncThunk(
  "restaurants/readDishAll",
  async (rejectWithValue) => {
    try {
      const response = await api.get("/dish");
      return response.data;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

export const readDishById = createAsyncThunk(
  "restaurants/readDishById",
  async ({id}, thunkAPI) => {
    try {
      const response = await fetch(`/dish/${id}`);
      if (!response.ok) {
        throw new Error("Failed to fetch dish");
      }
      const data = await response.json();
      return data;
    } catch (error) {
      return thunkAPI.rejectWithValue(error.message);
    }
  }
);

export const dishSave = createAsyncThunk(
  "restaurants/dishSave",
  async ({ name, description, weight, price, active }, thunkAPI) => {
    try {
      const response = await api.post("/dish/save", {
        name,
        description,
        weight,
        price,
        active,
      });
      return response.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(error.message);
    }
  }
);

export const saveDish = createAsyncThunk(
  "restaurants/saveDish",
  async (formData, { rejectWithValue }) => {
    try {
      const response = await api.post("/dish/save", formData);
      return response.data;
    } catch (error) {
      const errorMessage = error.response?.data?.message || error.message;
      return rejectWithValue(errorMessage);
    }
  }
);

export const saveMenu = createAsyncThunk(
  "restaurants/saveMenu",
  async ({ restaurantId, formData }, { rejectWithValue }) => {
    try {
      const response = await api.post(`/menu/${restaurantId}`, formData);
      return response.data;
    } catch (error) {
      if (error.response) {
        return rejectWithValue(error.response.data);
      } else {
        return rejectWithValue(error.message);
      }
    }
  }
);

export const updateDishStatus = createAsyncThunk(
  "restaurants/updateDishStatus",
  async ({ sectionId, dishId, status }, { rejectWithValue }) => {
    try {
      const response = await fetch(`/menu/${sectionId}/${dishId}`);
      const updatedDish = await response.json();
      return { sectionId, dishId, status: updatedDish.active };
    } catch (error) {
      console.error("Error updating dish status:", error);
      return rejectWithValue(error.message);
    }
  }
);
