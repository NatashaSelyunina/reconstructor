import { createSlice } from "@reduxjs/toolkit";
import { toast } from "react-toastify";

import {
  deleteTable,
  readRestaurantById,
  readRestaurants,
  readTableById,
  readTables,
  removeRestaurant,
  saveRestaurant,
  saveTable,
  updateRestaurant,
} from "./actions/restaurantsActions";

import {
  readDishAll,
  readDishById,
  readMenuRestaurant,
  saveDish,
  saveMenu,
  updateDishStatus,
} from "./actions/restaurantsMenuActions";
import {
  activateStaff,
  changePassword,
  deactivateStaff,
  readStaff,
  readStaffById,
  removeStaff,
  saveStaff,
  updateStaff,
} from "./actions/restaurantsStaffActions";
import {
  generatePassword,
  readModerator,
  readModeratorById,
} from "./actions/restaurantsModeratorActions";

export const restaurantsSlice = createSlice({
  name: "restaurants",

  initialState: {
    restaurants: [],
    restaurant: undefined,
    restaurantCode: null,
    restaurantId: null,
    selectedRestaurantId: null,
    dish: undefined,
    table: undefined,
    moderator: undefined,
    generatePassword: undefined,
    moderators: [],
    savedDish: [],
    tables: [],
    dishes: [],
    menu: [],
    staff: [],
    currentStaff: undefined,
    loading: false,
    error: null,
  },
  reducers: {
    setRestaurantCode(state, action) {
      state.restaurantCode = action.payload;
    },
    setRestaurantId(state, action) {
      state.restaurantId = action.payload;
    },
    setSelectedRestaurantId(state, action) {
      state.selectedRestaurantId = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(readRestaurants.pending, (state) => {
        state.loading = true;
      })
      .addCase(readRestaurants.fulfilled, (state, action) => {
        state.loading = false;
        state.restaurants = action.payload;
      })
      .addCase(readRestaurants.rejected, (state, action) => {
        state.loading = false;
        state.restaurants = [];
        state.error = action.payload;
      })
      .addCase(saveRestaurant.pending, (state) => {
        state.loading = true;
      })
      .addCase(saveRestaurant.fulfilled, (state, action) => {
        state.loading = false;
        state.restaurant = action.payload;
        toast.success("yahooo! restaurant save  🦄 ");
      })
      .addCase(saveRestaurant.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(readRestaurantById.pending, (state) => {
        state.loading = true;
      })
      .addCase(readRestaurantById.fulfilled, (state, action) => {
        state.loading = false;
        state.restaurant = action.payload;
        state.restaurantId = action.payload.id;
      })
      .addCase(readRestaurantById.rejected, (state, action) => {
        state.loading = false;
        state.restaurant = undefined;
        state.error = action.payload;
      })
      .addCase(removeRestaurant.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(removeRestaurant.fulfilled, (state) => {
        state.loading = false;
        state.restaurant = null;
      })
      .addCase(removeRestaurant.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload ? action.payload : "Unknown error";
      })
      .addCase(updateRestaurant.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(updateRestaurant.fulfilled, (state, action) => {
        state.loading = false;
        state.restaurant = action.payload;
        state.error = null;
      })
      .addCase(updateRestaurant.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(readDishAll.pending, (state) => {
        state.loading = true;
      })
      .addCase(readDishAll.fulfilled, (state, action) => {
        state.loading = false;
        state.dishes = action.payload;
        toast.success("dishhh!!   🦄 ");
      })

      .addCase(readDishAll.rejected, (state, action) => {
        state.loading = false;
        state.dishes = [];
        state.error = action.payload;
      })
      .addCase(readDishById.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(readDishById.fulfilled, (state, action) => {
        state.loading = false;
        state.dish = action.payload;
        toast.success("dishhh by id!!   🦄 ");
      })
      .addCase(readDishById.rejected, (state, action) => {
        state.loading = false;
        state.dish = undefined;
        state.error = action.payload;
        toast.error(state.error);
      })
      .addCase(saveDish.pending, (state) => {
        state.loading = true;
      })
      .addCase(saveDish.fulfilled, (state, action) => {
        state.loading = false;
        state.dishes = [...state.dishes, action.payload];
        toast.success("Dish added successfully! 🍽️");
      })
      .addCase(saveDish.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(saveMenu.pending, (state) => {
        state.loading = true;
      })
      .addCase(saveMenu.fulfilled, (state, action) => {
        state.loading = false;
        state.menu = [...state.menu, action.payload];
        toast.success("Menu added successfully! 🍽️");
      })
      .addCase(saveMenu.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        toast.error(state.error);
      })
      .addCase(readMenuRestaurant.pending, (state) => {
        state.loading = true;
      })

      .addCase(readMenuRestaurant.fulfilled, (state, action) => {
        state.loading = false;
        state.menu = action.payload;
      })
      .addCase(readMenuRestaurant.rejected, (state, action) => {
        state.loading = false;
        state.menu = [];
        state.error = action.payload;
        toast.error(state.error);
      })
      .addCase(saveStaff.pending, (state) => {
        state.loading = true;
      })
      .addCase(saveStaff.fulfilled, (state, action) => {
        state.loading = false;
        state.staff = [...state.staff, action.payload];
     
      })
      .addCase(saveStaff.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(saveTable.pending, (state) => {
        state.loading = true;
      })
      .addCase(saveTable.fulfilled, (state, action) => {
        state.loading = false;
        state.tables = [...state.tables, action.payload];
      })
      .addCase(saveTable.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        toast.error(state.error);
      })
      .addCase(readTables.pending, (state) => {
        state.loading = true;
      })
      .addCase(readTables.fulfilled, (state, action) => {
        state.tables = action.payload;
        state.loading = false;
      })
      .addCase(readTables.rejected, (state, action) => {
        state.error = action.payload;
        state.tables = [];
        state.loading = false;
      })
      .addCase(readTableById.pending, (state) => {
        state.loading = true;
      })
      .addCase(readTableById.fulfilled, (state, action) => {
        state.table = action.payload;
        state.loading = false;
      })
      .addCase(readTableById.rejected, (state, action) => {
        state.error = action.payload;
        state.loading = false;
      })
      .addCase(deleteTable.pending, (state) => {
        state.loading = true;
      })
      .addCase(deleteTable.fulfilled, (state, action) => {
        state.loading = false;
        state.tables = state.tables.filter(table => table.id !== action.payload.id);
    })
      .addCase(deleteTable.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(updateDishStatus.pending, (state) => {
        state.loading = true;
      })
      .addCase(updateDishStatus.fulfilled, (state, action) => {
        state.loading = false;
        const index = state.dishes.findIndex(
          (d) =>
            d.sectionId === action.payload.sectionId &&
            d.dishId === action.payload.dishId
        );
        if (index !== -1) {
          state.dishes[index].status = action.payload.status;
        }
        toast.success("Dish status updated successfully!");
      })
      .addCase(updateDishStatus.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(readStaff.pending, (state) => {
        state.loading = true;
      })
      .addCase(readStaff.fulfilled, (state, action) => {
        state.loading = false;
        state.staff = action.payload;
      })
      .addCase(readStaff.rejected, (state, action) => {
        state.loading = false;
        state.staff = [];
        state.error = action.payload;
        toast.error(state.error + "error slice");
      })
      .addCase(readStaffById.pending, (state) => {
        state.loading = true;
      })
      .addCase(readStaffById.fulfilled, (state, action) => {
        state.loading = false;
        state.currentStaff = action.payload;
      })
      .addCase(readStaffById.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(removeStaff.pending, (state) => {
        state.loading = true;
      })
      .addCase(removeStaff.fulfilled, (state, action) => {
        state.loading = false;
        state.staff = state.staff.filter(
          (person) => person.id !== action.payload
        );
      })
      .addCase(removeStaff.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(updateStaff.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(updateStaff.fulfilled, (state, action) => {
        state.loading = false;
        const index = state.staff.findIndex(
          (staff) => staff.id === action.payload.id
        );
        if (index !== -1) {
          state.staff[index] = { ...state.staff[index], ...action.payload };
        } else {
        }
      })
      .addCase(updateStaff.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(deactivateStaff.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(deactivateStaff.fulfilled, (state, action) => {
        state.loading = false;
        state.currentStaff = action.payload;
        state.error = null;
      })
      .addCase(deactivateStaff.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(activateStaff.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(activateStaff.fulfilled, (state, action) => {
        state.loading = false;
        state.currentStaff = action.payload;
      })
      .addCase(activateStaff.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      })
      .addCase(readModeratorById.pending, (state) => {
        state.loading = true;
      })
      .addCase(readModeratorById.fulfilled, (state, action) => {
        state.loading = false;
        state.moderator = action.payload;
      })
      .addCase(readModeratorById.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        toast.error(state.error);
      })
      .addCase(readModerator.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(readModerator.fulfilled, (state, action) => {
        state.loading = false;
        state.moderators = action.payload;
      })
      .addCase(readModerator.rejected, (state, action) => {
        state.loading = false;
        state.moderator = action.payload;
        state.error = action.payload;
        toast.error(state.error);
      })
      .addCase(generatePassword.pending, (state) => {
        state.loading = true;
      })
      .addCase(generatePassword.fulfilled, (state, action) => {
        state.loading = false;
        state.generatePassword = action.payload;
      })
      .addCase(generatePassword.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        toast.error(state.error);
      })
      .addCase(changePassword.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(changePassword.fulfilled, (state, action) => {
        state.loading = false;
        state.currentStaff = action.payload;
        console.log("change password in slice: ", action.payload);
        toast.success(" 👌");
      })
      .addCase(changePassword.rejected, (state, action) => {
        state.loading = false;
        state.status = "failed";
        state.error = action.payload;
      });
  },
});

export const { setRestaurantCode, setRestaurantId, setSelectedRestaurantId } =
  restaurantsSlice.actions;

export default restaurantsSlice.reducer;
