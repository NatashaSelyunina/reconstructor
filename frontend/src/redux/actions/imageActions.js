import axios from "axios";
import { toast } from "react-toastify";

export const uploadPhoto = (file) => async (dispatch) => {
    const formData = new FormData();
    formData.append("image", file);
  
    try {
      const res = await axios.post("/api/image", formData);
  
      if (res.status === 200) {
        const { url } = res.data;
        dispatch(photoUploaded(url));
      } else {
        console.error("Error photo:", res.status);
      }
    } catch (error) {
      console.error("Error photo:", error);
      toast.error(error);
    }
  };
  
  export const photoUploaded = (url) => {
    return {
      type: "PHOTO_UPLOADED",
      payload: url,
    };
  };
  
