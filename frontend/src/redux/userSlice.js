import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { toast } from "react-toastify";
import api from "../axios.config";
import { updateModerator } from "./actions/restaurantsModeratorActions";

export const checkUserSession = createAsyncThunk(
  "user/checkUserSession",
  async (_, { rejectWithValue }) => {
    try {
      const response = await api.get("/user");
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response.data);
    }
  }
);

export const loginUser = createAsyncThunk(
  "user/loginUser",
  async ({ login, password }, { rejectWithValue }) => {
    try {
      const response = await api.post("/auth/login", { login, password });

      if (response.status === 202) {
        const userData = await api.get("/user");

        if (userData.status === 200) {
          return { ...userData.data, active: true, isAuthenticate: true };
        } else {
          throw new Error("Failed to fetch user data");
        }
      } else {
        throw new Error("Login failed.");
      }
    } catch (error) {
      console.error("Login or user data fetch failed:", error);
      return rejectWithValue(error.response?.data?.message || "Unknown error");
    }
  }
);

export const registerUser = createAsyncThunk(
  "user/registerUser",
  async (
    { name, surname, dateOfBirth, login, password },
    { rejectWithValue }
  ) => {
    try {
      const response = await api.post("/moderator/registration", {
        name,
        surname,
        dateOfBirth,
        email: login,
        password,
      });
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response?.data?.message);
    }
  }
);

export const logoutUser = createAsyncThunk(
  "user/logoutUser",
  async (_, { rejectWithValue }) => {
    try {
      const response = await api.get("/auth/logout");
      if (response.status === 200) {
        return true;
      }
      return false;
    } catch (error) {
      return rejectWithValue(error.response.data);
    }
  }
);

export const activateAccount = createAsyncThunk(
  "user/activateAccount",
  async ({ validationCode }, { rejectWithValue }) => {
    try {

      const response = await api.put("/moderator/activate", { validationCode });
      return response.data;

    } catch (error) {
      return rejectWithValue(error.response.data);
    }
  }
);

export const validateRestoredPassword = createAsyncThunk(
  "user/validateRestoredPassword",
  async ({ newPassword, oldPassword, validationCode }, { rejectWithValue }) => {
    try {
      const response = await api.put("/moderator/validate-restored-password", {
        newPassword,
        oldPassword: null,
        validationCode,
      });
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response.data);
    }
  }
);


export const resetPassword = createAsyncThunk(
  "user/resetPassword",
  async (email, { rejectWithValue }) => {

    try {
      const response = await api.put("/moderator/reset_password", { email });
      return response.data;
    } catch (error) {
      return rejectWithValue(error.response.data);
    }
  }
);

const userSlice = createSlice({
  name: "user",
  initialState: {
    loading: false,
    user: null,
    role: undefined,
    restaurantId: undefined,
    staffId: undefined,
    isAuthenticated: false,
    isClient: true,
    validationCode: null,
    status: "idle",
    isActive: false,
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(checkUserSession.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(checkUserSession.fulfilled, (state, action) => {
        state.user = action.payload.user;
        state.isAuthenticated = true;
        state.restaurantId = action.payload.restaurantId;
        state.staffId = action.payload.user.id;
        state.isClient = true;
        state.isActive = true;
        state.loading = false;
        state.error = null;
      })
      .addCase(checkUserSession.rejected, (state, action) => {
        state.error = action.payload;
        state.isClient = true;
        state.loading = false;
      })
      .addCase(registerUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(registerUser.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload;
        state.isClient = true;
      })
      .addCase(registerUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        state.user = undefined;
      })
      .addCase(loginUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.loading = false;
        if (action.payload && action.payload.role && action.payload.role.name) {
          state.user = action.payload;
          state.role = action.payload.role.name;
          state.restaurantId = action.payload.restaurantId;
          state.isClient = true;
          state.isAuthenticated = true;
          state.error = null;
        } else {
          state.isAuthenticated = false;
          state.isClient = true;
          state.role = undefined;
        }
        state.error = null;
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.user = undefined;
        state.isAuthenticated = false;
        state.error = action.error.message;
      })
      .addCase(logoutUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(logoutUser.fulfilled, (state) => {
        state.loading = false;
        state.user = undefined;
        state.isAuthenticated = false;
        toast.success("see you");
      })
      .addCase(logoutUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        toast.error(state.error);
      })
      .addCase(activateAccount.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(activateAccount.fulfilled, (state, action) => {
        state.loading = false;
        state.status = action.payload;
        state.error = null;
      })
      .addCase(activateAccount.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        toast.error(state.error);
      })
      .addCase(resetPassword.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(resetPassword.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload;
        toast.success("Password reset successful 👌");
      })
      .addCase(resetPassword.rejected, (state, action) => {
        state.loading = false;
        state.status = "failed";
        state.error = action.payload;
      })
      .addCase(validateRestoredPassword.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(validateRestoredPassword.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload;
        state.error = null;
      })
      .addCase(validateRestoredPassword.rejected, (state, action) => {
        state.loading = false;
        state.user = undefined;
        state.error = action.error.message;
      })

      .addCase(updateModerator.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(updateModerator.fulfilled, (state, action) => {
        state.loading = false;
        state.user = action.payload;
        console.log("moderator update in slice", action.payload);
      })
      .addCase(updateModerator.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        toast.error(state.error);
      });
  },
});
export const { login, logout } = userSlice.actions;
export const selectAuth = (state) => state.user;
export default userSlice.reducer;
