import React, { useEffect, useRef, useState } from "react";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import styles from "./OrderWebSocket.module.css";
import { useTranslation } from "react-i18next";

const OrderWebSocket = () => {
  const [orders, setOrders] = useState([]);
  const stompClient = useRef(null);
  const { t } = useTranslation("translation");

  useEffect(() => {
    const socket = new SockJS("ws://reconstructor.me/api/ws");
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
      console.log("Connect to WebSocket");

      stompClient.subscribe("/orders", (message) => {
        const newOrder = JSON.parse(message.body);
        setOrders((prevOrders) => [...prevOrders, newOrder]);
      });
    });
    return () => {
        if (stompClient.current && stompClient.current.connected) {
          stompClient.current.disconnect();
          console.log("Disconnect WebSocket");
        }
      };
    }, []);

    const sendStatusChange = (role, orderId, newStatus) => {
        const basePath = '/change-status';
        const rolePath = `${basePath}/${role}`;
        try {
          stompClient.current.send(rolePath, {}, JSON.stringify({ orderId, newStatus }));
        } catch (error) {
          console.error(`Error sending status change for ${role}:`, error);
        }
      };

  return (
    <div>
      <ul>
        {orders?.map((order) => (
          <li key={order.id}>
            {order?.table} - {order?.status}
            {order.status === t("order.partiallyReady") && <span className={styles.partiallyReady}></span>}
            {order.status === t("order.fullReady") && <span className={styles.fullReady}></span>}
            {order.status === t("order.orderAccepted") && <span className={styles.orderAccepted}></span>}
            <button onClick={() => sendStatusChange('waiter', order.id, 'newStatus')}>{t("order.fullReady")}</button>
            <button onClick={() => sendStatusChange('chef', order.id, 'newStatus')}>{t("order.partiallyReady")}</button>
            <button onClick={() => sendStatusChange('chef', order.id, 'newStatus')}>{t("order.orderAccepted")}</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default OrderWebSocket;
