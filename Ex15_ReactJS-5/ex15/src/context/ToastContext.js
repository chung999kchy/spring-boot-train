import React from 'react';

const ToastContext = React.createContext({});

export const ToastProvider = ToastContext.Provider
export const ToastConsumer = ToastContext.Consumer
export default ToastContext;