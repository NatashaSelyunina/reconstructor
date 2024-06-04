import { createSlice } from '@reduxjs/toolkit';


export const themeSlice = createSlice({
  name: 'theme',
  initialState:  {
    backgroundColor: "#282c34",
    color: "silver",
    font: "'-apple-system', 'BlinkMacSystemFont', 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif",
  },
  reducers: {
    setBackgroundColor: (state, action) => {
        state.backgroundColor = action.payload;
    },
    setColor: (state, action) =>{
        state.color = action.payload;
    },
    setFont: (state, action) => {
      state.font = action.payload;
    }
  },
});
export const {setBackgroundColor, setColor, setFont} = themeSlice.actions;
export default themeSlice.reducer;
