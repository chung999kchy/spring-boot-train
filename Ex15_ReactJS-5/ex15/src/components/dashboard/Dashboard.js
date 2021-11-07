import React, { useContext } from 'react';
import "./dashboard.scss";
import UserContext from "../../context/UserContext";

const Dashboard = () => {
    const { user } = useContext(UserContext);

    return (
        <h3>Welcome {user || "to my website"}</h3>
    )
};

export default Dashboard;