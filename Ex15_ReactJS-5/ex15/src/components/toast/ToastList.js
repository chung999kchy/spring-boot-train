import React from 'react';
import { Fragment } from 'react';
import Toast from './Toast';

import "./toast.scss";

function ToastList({ toastList }) {
    if (toastList != null)
        return (
            <div id="toast">
                {toastList.map((toast, index) =>
                    <Fragment key={index}>
                        <Toast toast={toast} />
                    </Fragment>
                )}
            </div>
        );
    else return null;
}

export default ToastList;