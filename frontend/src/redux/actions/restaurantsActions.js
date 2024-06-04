import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../axios.config";


export const readRestaurants = createAsyncThunk(
  "restaurants/readRestaurant",
  async (_, { rejectWithValue }) => {
    try {
      const response = await api.get("/restaurant");
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response.data);
    }
  }
);

export const saveRestaurant = createAsyncThunk(
  "restaurants/saveRestaurant",
  async (restaurantData, { rejectWithValue }) => {
    try {
      const response = await api.post("/restaurant", restaurantData);
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response.data);
    }
  }
);

export const readRestaurantById = createAsyncThunk(
  "restaurants/readRestaurantById",
  async ({restaurantId}, { rejectWithValue }) => {
    try {
     
      const response = await api.get(`/restaurant/${restaurantId}`);
      return response.data;
    } catch (error) {
      return rejectWithValue(error.message ? error.message : 'Unknown error');
    }
  }
);

export const updateRestaurant = createAsyncThunk(
  'restaurants/updateRestaurant',
  async (updatedData, { rejectWithValue }) => {
    try {
      const response = await api.put("/restaurant", updatedData);
      return response.data; 
    } catch (error) {
      return rejectWithValue(error.message); 
    }
  }
);

export const removeRestaurant = createAsyncThunk(
  'restaurants/removeRestaurant',
  async ({restaurantId}, { rejectWithValue }) => {
    try {
      const response = await api.delete(`/restaurant/${restaurantId}`);
      return response.data; 
    } catch (error) {
      return rejectWithValue(error.message); 
    }
  }
);
  export const saveTable = createAsyncThunk(
    'restaurants/saveTable',
    async ({ restaurantId, tableData }, { rejectWithValue }) => {
      try {
        const response = await api.post(`/table/${restaurantId}`, tableData);
        return response.data;
      } catch (error) {
        return rejectWithValue(error.message);
      }
    }
  );

  export const readTables = createAsyncThunk(
    'restaurants/readTables',
    async ({ restaurantId}, { rejectWithValue }) => {
      try {
       
        const response = await api.get(`/table/all/${restaurantId}`);
        return response.data;
      } catch (error) {
        return rejectWithValue(error.message);
      }
    }
  );

  export const readTableById = createAsyncThunk(
    "restaurants/readTableById",
    async ({ id}, { rejectWithValue }) => {
      try {    
        const response = await api.get(`/table/${id}`);
        console.log("table", response)
        return response.data;
      } catch (error) {
        return rejectWithValue(error.message);
      }
    }
  );
  
  export const deleteTable = createAsyncThunk(
    'restaurants/deleteTable',
    async ({ id, rejectWithValue }) => {
      try {
       
        const response = await api.delete(`/table/${id}`);
        console.log("table remove", response)
        return id;
      } catch (error) {
        return rejectWithValue(error.message);
      }
    }
  );

  
