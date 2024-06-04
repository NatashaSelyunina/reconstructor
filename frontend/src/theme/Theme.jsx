import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setColor, setBackgroundColor, setFont } from "../redux/themeSlice";
import styles from "./Theme.module.css";
import { FaPencilAlt } from "react-icons/fa";
import { useTranslation } from "react-i18next";

const Theme = () => {
  const dispatch = useDispatch();
  const { t } = useTranslation("translation");
  const backgroundColor = useSelector((state) => state.theme.backgroundColor);
  const color = useSelector((state) => state.theme.color);
  const font = useSelector((state) => state.theme.font);
  const [isOpen, setIsOpen] = useState(false);

  const handleBackgroundColor = (color) => {
    dispatch(setBackgroundColor(color));
  };

  const handleColorToggle = (e) => {
    dispatch(setColor(e.target.value));
  };

  const handleFont = (font) => {
    dispatch(setFont(font));
  };

  const toggleStyles = () => {
    setIsOpen(!isOpen);
  };
  return (
    <div className={styles.toggleStyles}>
      <button onClick={toggleStyles} title={t("theme.styles")}>
        <FaPencilAlt />
      </button>
      {isOpen && (
        <div>
          <label>
            <input
              type="color"
              value={backgroundColor}
              onChange={(e) => handleBackgroundColor(e.target.value)}
            />
          </label>

          <label htmlFor="font">{t("theme.textFont")}</label>
          <select
            id="font"
            value={font}
            onChange={(e) => handleFont(e.target.value)}
            className={styles.fontFamily}
          >
            <option value="-apple-system">Apple System</option>
            <option value="BlinkMacSystemFont">BlinkMac System Font</option>
            <option value="Segoe UI">Segoe UI</option>
            <option value="Montserrat">Montserrat</option>
            <option value="Madimi One">Madimi One</option>
            <option value="Oswald">Oswald</option>
            <option value="Playfair Display">Playfair Display</option>
            <option value="Whisper">Whisper</option>
            <option value="Nanum Gothic Coding">Nanum Gothic Coding</option>
            <option value="Rowdies">Rowdies</option>
            <option value="Kalam">Kalam</option>
            <option value="Protest Riot">Protest Riot</option>
            <option value="Merienda">Merienda</option>
            <option value="Nothing You Could Do">Nothing You Could Do</option>
            <option value="Pinyon Script">Pinyon Script</option>

            <option value="Bellota">Bellota</option>
            <option value="Bad Script">Bad Script</option>
            <option value="Rubik Mono One">Rubik Mono One</option>
            <option value="Unbounded">Unbounded</option>
            <option value="Caveat">Caveat</option>
            <option value="Amatic SC">Amatic SC</option>
            <option value="Lobster">Lobster</option>
            <option value="Comfortaa">Comfortaa</option>
            <option value="Pacifico">Pacifico</option>
          </select>
          <label>{t("theme.textColor")}</label>
          <input type="color" value={color} onChange={handleColorToggle} />
        </div>
      )}
    </div>
  );
};

export default Theme;
