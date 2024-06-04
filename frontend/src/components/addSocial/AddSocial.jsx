import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import { IconContext } from "react-icons";
import { FaCheck, FaEdit, FaMinus, FaPlus, FaTrash } from "react-icons/fa";
import styles from "./AddSocial.module.css";

const AddSocial = ({ socials, setSocials }) => {
  
  const [address, setAddress] = useState("");
  const [name, setName] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [email, setEmail] = useState("");
  const [website, setWebsite] = useState("");
  const [editIndex, setEditIndex] = useState(null);

  const { t } = useTranslation("translation");

  const addSocial = () => {
    if (name && phoneNumber && address && website && email) {
      const newSocial = {
        id: socials.length + 1,
        name,
        phoneNumber,
        address,
        website,
        email,
      };
      setSocials([...socials, newSocial]);
      clearFields();
    }
  };

  const editSocial = (index) => {
    setName(socials[index].name);
    setPhoneNumber(socials[index].phoneNumber);
    setAddress(socials[index].address);
    setWebsite(socials[index].website);
    setEmail(socials[index].email);

    setEditIndex(index);
  };

  const deleteSocial = (index) => {
    const filteredSocials = socials.filter((_, i) => i !== index);
    setSocials(filteredSocials);
  };

  const saveChanges = () => {
    if (editIndex !== null) {
      const updatedSocials = [...socials];
      updatedSocials[editIndex] = {
        ...updatedSocials[editIndex],
        name,
        phoneNumber,
        address,
        website,
        email,
      };
      setSocials(updatedSocials);
      clearFields();
      setEditIndex(null);
    }
  };

  const clearFields = () => {
    setName("");
    setPhoneNumber("");
    setAddress("");
    setWebsite("");
    setEmail("");
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <div className={styles.bigContainer}>
      <div className={styles.container}>
        <div className={styles.iconProvider}>
          <IconContext.Provider value={{ className: "global-class-name" }}>
            <div>
              <form onSubmit={handleSubmit}>
                <div className="mb-2">
                  <input
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder={t("restaurantName")}
                  />
                </div>
                <div className="mb-2">
                  <input
                    type="text"
                    value={phoneNumber}
                    onChange={(e) => setPhoneNumber(e.target.value)}
                    placeholder={t("social.phone")}
                  />
                </div>
                <div className="mb-2">
                  <input
                    type="textarea"
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                    placeholder={t("social.address")}
                  />
                </div>
                <div className="mb-2">
                  <input
                    type="text"
                    value={website}
                    onChange={(e) => setWebsite(e.target.value)}
                    placeholder={t("social.website")}
                  />
                </div>
                <div className="mb-2">
                  <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder={t("social.email")}
                  />
                </div>
                {editIndex !== null ? (
                  <button onClick={saveChanges}>
                    <FaCheck />
                  </button>
                ) : (
                  <button onClick={addSocial}>
                    <FaPlus />
                  </button>
                )}

                {socials.length > 0 && (
                  <div className={styles.socialContainer}>
                    <h2>{t("restaurantData")}</h2>
                    <ul>
                      {socials.map((social, index) => (
                        <li key={social.id}>
                          {social.name} {social.address} {social.phoneNumber} {social.email} {social.website}
                          <FaMinus />
                          <button onClick={() => editSocial(index)}>
                            <FaEdit />
                          </button>
                          <button onClick={() => deleteSocial(index)}>
                            <FaTrash />
                          </button>
                        </li>
                      ))}
                    </ul>
                  </div>
                )}
              </form>
            </div>
          </IconContext.Provider>
        </div>
      </div>
    </div>
  );
};

export default AddSocial;
