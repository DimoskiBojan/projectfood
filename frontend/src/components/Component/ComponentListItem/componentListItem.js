import React from "react";
import {Link} from "react-router-dom";
import {isAuthenticated} from "../../../repository/userRepository";

const ComponentListItem = (props) => {
    return (
        <tr>
            <td><Link to={"/component/" + props.component.id + "/details"}>{props.component.name}</Link></td>
            <td>{props.component.category_name}</td>
            <td>{props.component.moldb_formula || "Not available"}</td>
            {
                isAuthenticated() &&
                (
                    <td>
                        <Link to={"/component/" + props.component.id + "/map-external"}>
                            <button className="btn btn-sm btn-secondary mr-2">
                                <span><strong>Map External</strong></span>
                            </button>
                        </Link>
                        <Link to={"/component/" + props.component.id + "/map-foodb"}>
                            <button className="btn btn-sm btn-secondary">
                                <span><strong>Map FooDB</strong></span>
                            </button>
                        </Link>
                    </td>
                )
            }
        </tr>
    )
};

export default ComponentListItem;