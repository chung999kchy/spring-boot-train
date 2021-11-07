import React, { useEffect, useState } from 'react';
import "./toast.scss";

function Toast({ toast }) {
    const { title = '', message = '', type = "info", duration = 3000 } = toast;
    useEffect(() => {
        setShow(true)
    }, [toast])

    const [show, setShow] = useState(true);
    const icons = {
        success: "fas fa-check-circle",
        info: "fas fa-info-circle",
        warning: "fas fa-exclamation-circle",
        error: "fas fa-exclamation-circle"
    };
    const icon = icons[type];
    const delay = (duration / 1000).toFixed(2);
    const animation = `slideInLeft2 ease 0.8s,fadeOut2 linear 1s ${delay}s forwards`;

    const clickRemove = (e) => {
        e.preventDefault();
        setShow(false);
    }

    //autoRemove
    setTimeout(() => {
        setShow(false);
    }, duration + 1000)


    if (!show) return null;
    return (
        <div className={`toast toast--${type}`}
            style={{
                animation: animation
            }}>
            <div className="toast__icon">
                <i className={icon}></i>
            </div>
            <div className="toast__body">
                <h3 className="toast__title">{title}</h3>
                <p className="toast__msg">{message}</p>
            </div>
            <div className="toast__close" onClick={clickRemove}>
                <i className="fas fa-times"></i>
            </div>
        </div>)
}



export default Toast;