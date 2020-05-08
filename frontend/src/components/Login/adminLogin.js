import React from "react";

import 'datatables.net';

const AdminLogin = (props) => {

    return (
        <div className="row text-center justify-content-center mt-5">
            <form className="form-signin w-25">
                    <h1 className="h3 mb-3 text-info font-weight-bold">Admin Login</h1>
                    <label htmlFor="inputEmail" className="sr-only">Email address</label>
                    <input type="email" id="inputEmail" className="form-control" placeholder="Email address" required=""
                           autoFocus=""/>
                        <label htmlFor="inputPassword" className="sr-only">Password</label>
                        <input type="password" id="inputPassword" className="form-control" placeholder="Password"
                               required=""/>
                    <button className="btn btn-lg btn-info btn-block mt-3" type="submit">Sign in</button>
            </form>
        </div>
    )
};

export default AdminLogin;