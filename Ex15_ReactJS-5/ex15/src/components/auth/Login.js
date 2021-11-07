import React, { useContext, useState } from 'react';
import { useHistory } from 'react-router-dom';
import authApi from "../../api/authApi";
import ToastContext from '../../context/ToastContext';
import UserContext from '../../context/UserContext';
import "./login.scss";

//  Data sample request
//    {
//        "username": "admin",
//        "password": "admin"
//    }

// Data sample response
// {
//     "metadata": {
//         "total": 1,
//         "limit": 1,
//         "page": 1
//     },
//     "data": {
//         "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaHVuZzEyMyIsImlhdCI6MTYzNTI2MjgwOCwiZXhwIjoxNjM1MjYyODk0fQ.NtsSuatEpRi_o6CN5Z7doOQYX0yiIOynCxy1EsoN_hoGjKzYI_l49UkWVwWpBVEVRveCf08cSndtCpBEt8NwWA",
//         "tokenType": "Bearer"
//     }
// }

function Login() {
    const { token, setUser, setToken } = useContext(UserContext)
    const { setToastList } = useContext(ToastContext)
    const history = useHistory();

    let tokenNew = token;
    const [account, setAccount] = useState({
        username: '',
        password: ''
    });
    const [error, setError] = useState('');


    const handleInputUsername = e => {
        setAccount({
            ...account,
            username: e.target.value
        })
    }

    const handleInputPassword = e => {
        setAccount({
            ...account,
            password: e.target.value
        })
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const data = JSON.stringify(account);
            const response = await authApi.login(data);
            tokenNew = response.data;
        } catch (error) {
            console.log(error);
        }
        if (typeof tokenNew === 'undefined' || tokenNew === null) { setError("Username or password is incorrect"); }
        else {
            setError('');
            localStorage.setItem("token", `${tokenNew.accessToken}`);
            localStorage.setItem("user", account.username);
            if (setToken) setToken(tokenNew);
            if (setUser) setUser(account.username);
            if (setToastList) setToastList([{ title: "Success", message: `Welcome ${account.username} to my website!`, type: 'success' }])
            history.push("/");
        }
    }
    return (

        <form onSubmit={handleSubmit} className="login-form">
            <h2>Login</h2>
            <div>
                Username
                <input
                    type="text"
                    onChange={handleInputUsername}
                    value={account.username}
                    required
                />
            </div>
            <div>
                Password
                <input
                    type="password"
                    onChange={handleInputPassword}
                    value={account.password}
                    required
                />
            </div>
            <small>{error}</small>
            <button type="submit">
                Submit
            </button>
        </form>
    )
}

export default Login;