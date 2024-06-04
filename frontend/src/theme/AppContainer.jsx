import React from 'react';
import { useSelector } from 'react-redux';
import styles from './Theme.module.css';

const AppContainer = ({ children }) => {
  
  const backgroundColor = useSelector(state => state.theme.backgroundColor);
  const color = useSelector(state => state.theme.color);
  const font = useSelector(state => state.theme.font);

  const appStyles = {
    backgroundColor: backgroundColor,
    color: color,
    fontFamily: font,
  };

  return (
    <div className={styles.appStyles} style={appStyles}>
      {children}
    </div>
  );
};

export default AppContainer;
