import React, { useState } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { ToastProvider } from "./context/ToastContext";
import { UserProvider } from "./context/UserContext";

import CategoryList from "./components/category/CategoryList";
import CategoryItem from "./components/category/CategoryItem";
import Header from "./components/base/Header";
import Sidebar from "./components/sidebar/Sidebar";
import Dashboard from "./components/dashboard/Dashboard";
import Login from "./components/auth/Login";
import Register from "./components/auth/Register";
import CreateCategory from "./components/category/CreateCategory";
import ToastList from "./components/toast/ToastList";


function App() {
    const [token, setToken] = useState(localStorage.getItem("token"));
    const [user, setUser] = useState(localStorage.getItem("user"));
    const [toastList, setToastList] = useState([])


    return (
        <div className="App">
            <UserProvider value={{ user, setUser, token, setToken }}>
                <ToastProvider value={{ toastList, setToastList }} >
                    <div className="container p-0">
                        <Router>
                            <Header token={token} handleToken={setToken} />
                            <div className="main row p-0 m-0 d-flex">
                                <Sidebar />
                                <div className="col-9">
                                    <div className="content">
                                        <Switch>
                                            <Route exact path="/" component={Dashboard} />
                                            <Route exact path="/categories" component={CategoryList} />
                                            <Route exact path="/categories/create" component={CreateCategory} />
                                            <Route exact path="/categories/:id" component={CategoryItem} />
                                        </Switch>
                                        <Route exact path="/login" component={Login} />
                                        <Route exact path="/register" component={Register} />
                                        <ToastList toastList={toastList} />
                                    </div>
                                </div>
                            </div>
                        </Router>
                    </div>
                </ToastProvider>
            </UserProvider >
        </div >
    )
}

export default App;
