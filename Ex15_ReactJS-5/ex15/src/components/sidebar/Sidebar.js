import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import UserContext from '../../context/UserContext';
import "./sidebar.scss";

function Sidebar() {
    const { user } = useContext(UserContext);
    return (
        <div className="sidebar col-3">
            <div className="sidebar-avatar">
                <img src="https://static2.yan.vn/YanNews/202005/202005220338189768-647d9973-107b-40e3-b2bb-647ae67bf99b.png" alt="avatar" />
                <h3>{user || 'Welcome'}</h3>
            </div>
            <ul className="sidebar-menu">
                <li>
                    <Link className="sidebar-menu__item" to="/">
                        <i className="fas fa-home"></i>Dashboard
                    </Link>
                </li>
                <li>
                    <Link className="sidebar-menu__item" to="/categories">
                        <i className="fab fa-app-store-ios"></i>Category
                    </Link>
                </li>
                <li>
                    <Link className="sidebar-menu__item" to="/products">
                        <i className="fab fa-apple"></i>Product
                    </Link>
                </li>
                <li>
                    <Link className="sidebar-menu__item" to="/inventories">
                        <i className="fas fa-warehouse"></i>Inventory
                    </Link>
                </li>
            </ul>
        </div>
    );
}

export default Sidebar;