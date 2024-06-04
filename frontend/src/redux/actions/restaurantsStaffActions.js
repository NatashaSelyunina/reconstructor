import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../axios.config";

export const removeStaff = createAsyncThunk(
  "restaurants/removeStaff",
  async (id, { rejectWithValue }) => {
    try {
      const response = await api.delete(`/staff/${id}`);
      if (response.status !== 200) {
        throw new Error("Failed to delete the staff member.");
      }
      return id;
    } catch (error) {
      return rejectWithValue(error.message || "Error deleting staff member.");
    }
  }
);

export const updateStaff = createAsyncThunk(
  "restaurants/updateStaff",
  async (updatedPerson, { rejectWithValue }) => {
    try {
      console.log("update :", updatedPerson);
      const response = await api.put(`/staff`, updatedPerson);
      console.log(" response:", response);
      return response.data;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

export const deactivateStaff = createAsyncThunk(
  "restaurants/deactivateStaff",
  async ({ id }, { rejectWithValue }) => {
    try {
      const response = await api.put(`/staff/deactivate/${id}`, {
        active: false,
      });
      return response.data;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

export const activateStaff = createAsyncThunk(
  "restaurants/activateStaff",
  async ({ id }, { rejectWithValue }) => {
    try {
      const response = await api.put(`/staff/activate/${id}`);
      return response.data;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

export const saveStaff = createAsyncThunk(
  "restaurants/saveStaff",
  async ({ formData, restaurantId }, { rejectWithValue }) => {
    try {
      const response = await api.post(`/staff/${restaurantId}`, formData);
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

export const readStaff = createAsyncThunk(
  "restaurants/readStaff",
  async ({ restaurantId }, { rejectWithValue }) => {
    try {
      const response = await api.get(`/staff/all/${restaurantId}`);
      return response.data;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

export const readStaffById = createAsyncThunk(
  "restaurants/readStaffById",
  async ({ id }, thunkAPI) => {
    try {
      const response = await api.get(`/staff/${id}`);
      return response.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(error.message);
    }
  }
);

export const changePassword = createAsyncThunk(
  "user/changePassword",
  async ({ newPassword, oldPassword }, { rejectWithValue }) => {
    try {
      const response = await api.put("/moderator/password", {
        newPassword,
        oldPassword,
      });
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response.data);
    }
  }
);
