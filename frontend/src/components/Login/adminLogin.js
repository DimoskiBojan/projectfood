import React, {useState} from "react";
import { login } from '../../repository/userRepository';

import 'datatables.net';

const AdminLogin = (props) => {
    const [userInfo, setUserInfo] = useState({username: '', password: ''});

    const handleInputChange = (e) => {
        setUserInfo({
            ...userInfo,
            [e.target.name]: e.target.value
        });
    }

    const submitLogin = (e) => {
        e.preventDefault();
        login(userInfo)
            .then(token => window.location = '/')
            .catch(err => alert(err));
    }

    return (
        <div className="row text-center justify-content-center mt-5">
            <form onSubmit={submitLogin} className="form-signin w-25">
                    <h1 className="h3 mb-3 text-info font-weight-bold">Login</h1>
                    <label htmlFor="inputEmail" className="sr-only">Email address</label>
                    <input name="username" id="inputEmail" className="form-control" placeholder="Email address" required=""
                           autoFocus="" onChange={handleInputChange}/>
                        <label htmlFor="inputPassword" className="sr-only">Password</label>
                        <input name="password" type="password" id="inputPassword" className="form-control" placeholder="Password"
                               required="" onChange={handleInputChange}/>
                    <button className="btn btn-lg btn-info btn-block mt-3" type="submit">Sign in</button>
            </form>
        </div>
    )
};

export default AdminLogin;