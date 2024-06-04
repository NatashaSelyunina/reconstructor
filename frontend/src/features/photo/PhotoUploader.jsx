import React, { useRef, useState } from "react";
import styles from "./PhotoUploader.module.css";
import { toast } from "react-toastify";
import api from "../../axios.config";

const PhotoUploader = ({ onPhotoUploaded, name, urlHost, keyName, children }) => {
  const hostUrl = `/image/${urlHost}`;
  const [selectedFile, setSelectedFile] = useState(null);
  const uploadInputRef = useRef(null);

  const handleButtonClick = () => {
    uploadInputRef.current.click();
  };


  const handlePhotoChange = async (e) => {
    const file = e.target.files[0];
    if (!file) return;
    setSelectedFile(file);
    e.target.value = '';
    
    const formData = new FormData();
    formData.append(keyName, file);


    try {
      const res = await api.post(hostUrl, formData);

      if (res.status === 200) {
        onPhotoUploaded(res.data.url);
      } else {
        toast.error(`Error photo: ${res.message}`);
      }
    } catch (error) {
      toast.error(error.message);
    }
  };

  return (
    <div>
      <input
        className={styles.fileInput}
        ref={uploadInputRef}
        type="file"
        id={name}
        name={name}
        onChange={handlePhotoChange}
        accept="image/*,.png,.jpg,.gif,.web,.jpeg"
     
      />
      <label htmlFor="photo" className={styles.faCamera} onClick={handleButtonClick}>
      {children}
</label>
      {selectedFile && (
        <div>
          <img
            className={styles.photo}
            src={URL.createObjectURL(selectedFile)}
            alt="Selected"
          />
        </div>
      )}
    </div>
  );
};

export default PhotoUploader;
