import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../axios.config";



export const readModeratorById = createAsyncThunk(
    "restaurants/readModeratorById",
    async ( thunkAPI) => {
      try {
       
        const response = await api.get(`/moderator`);
        return response.data;
      } catch (error) {
        return thunkAPI.rejectWithValue(error.message);
      }
    }
  );

  export const readModerator = createAsyncThunk(
    'restaurants/readModerator',
    async (thunkAPI) => {
      try {
       
        const response = await api.get(`/moderator`);
        return response.data;
      } catch (error) {
        return thunkAPI.rejectWithValue(error.message);
      }
    }
  );

  export const updateModerator = createAsyncThunk(
    "restaurants/updateModerator",
    async ( userUpdateData , {rejectWithValue}) => {
      try {
        
        const response = await api.put("/moderator", userUpdateData);
        console.log("Update Person: ", response)
        return response.data;
      } catch (error) {
        console.log("error update person:", error)
        return rejectWithValue(error.message);
       
      }
    }
  );


  export const generatePassword = createAsyncThunk(
    'restaurants/generatePassword',
    async (thunkAPI) => {
      try {
       
        const response = await api.get(`/generation`);
        return response.data;
      } catch (error) {
        return thunkAPI.rejectWithValue(error.message);
      }
    }
  );
