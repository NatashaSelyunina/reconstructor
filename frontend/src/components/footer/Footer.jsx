import styles from "./Footer.module.css";

const Footer = () => {
  return (
    <div className={styles.footer}>
      <div className={styles.container}>
        <span>ReConstructor</span>
         <span>Copyright © {new Date().getFullYear()}</span>
      </div>
    </div>
  );
};

export default Footer;
