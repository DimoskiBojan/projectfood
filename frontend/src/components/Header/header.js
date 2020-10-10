import React from 'react';
import {Link} from 'react-router-dom'
import {isAuthenticated, logOut} from "../../repository/userRepository";

const header = (props) => {
    let user;
    if(localStorage.getItem("x-access-token") != null){
        let jwt = require("jsonwebtoken");
        let token = localStorage.getItem("x-access-token").replace('Bearer ','');
        let decodedToken = jwt.decode(token);
        user = decodedToken.sub;
    }

    return (
        <header>
            <nav className="navbar navbar-expand-md navbar-dark navbar-fixed bg-info">
                <div className="container">
                    <Link className={"navbar-brand"} to={"/"}>Home</Link>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"/>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarCollapse">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item">
                                <Link className={"nav-link"} to={"/food"}>Food</Link>
                            </li>
                            <li className="nav-item ">
                                <Link className={"nav-link"} to={"/component"}>Component</Link>
                            </li>
                            <li className="nav-item ">
                                <Link className={"nav-link"} to={"/nutrients"}>Nutrients</Link>
                            </li>
                            <li className="nav-item ">
                                <Link className={"nav-link"} to={"/streams"}>Waste Streams</Link>
                            </li>
                            {
                                isAuthenticated() &&
                                (
                                    <li className="nav-item ">
                                        <Link className={"nav-link"} to={"/automap"}>AutoMap</Link>
                                    </li>
                                )
                            }
                        </ul>
                        {
                            isAuthenticated() && (<div className={"text-white"}>Hello {user}</div>)
                        }
                        <form className="form-inline mt-2 mt-md-0 ml-3">
                            {
                                (isAuthenticated()) ?
                                    (<ul className="nav navbar-nav navbar-right">
                                        <li onClick={logOut}><a className={"text-white"} href="/">Log out</a></li>
                                    </ul>) :
                                    (<div>
                                        <Link className={"btn btn-outline-light my-2 my-sm-0"} to={"/login"}>Login</Link>
                                        <Link className={"text-white ml-2"} to={"/register"}>Register</Link>
                                    </div>)
                            }
                        </form>
                    </div>
                </div>
            </nav>
        </header>
    )
};

export default header;
