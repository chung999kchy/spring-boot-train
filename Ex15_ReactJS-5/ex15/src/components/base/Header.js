import React, { useContext } from 'react';
import { Link, useHistory } from 'react-router-dom';
import UserContext from '../../context/UserContext';
import "./reset.css";
import "./base.css";
import "./header.scss";




function Header() {
    const { user, setUser, setToken } = useContext(UserContext);
    const history = useHistory()

    return (
        <div className="header row m-0 d-flex">
            <div className="header-logo col">
                <img src="https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img/https://lambanner.com/wp-content/uploads/2020/04/MNT-DESIGN-10-MEO-THIET-KE-LOGO-1130x570.jpg" alt="logo" />
            </div>
            <div className="header-search col">
                <input type="text" placeholder="Tìm kiếm" />
                <button><i className="fas fa-search"></i></button>
            </div>
            <div className="header-auth col">
                {
                    user &&
                    <button className="header-auth__link" onClick={() => {
                        localStorage.removeItem("token");
                        localStorage.removeItem("user");
                        if (setUser) setUser('');
                        if (setToken) setToken(null);
                        history.push("/login")
                    }}>Logout</button>
                }
                {
                    !user &&
                    <Link to="/login" className="header-auth__link">Login</Link>
                }
                {
                    !user &&
                    <Link to="/register" className="header-auth__link">Register</Link>
                }
            </div>
        </div >
    );
}

export default Header;