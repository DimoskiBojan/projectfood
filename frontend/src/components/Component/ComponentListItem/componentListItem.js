import React from "react";
import {Link} from "react-router-dom";

const ComponentListItem = (props) => {
    return (
        <tr>
            <td><a href={"/component/" + props.component.id + "/details"}>{props.component.name}</a></td>
            <td>{props.component.category_name}</td>
            <td>{props.component.moldb_formula || "Not available"}</td>
            <td>
                <Link to={"/component/" + props.component.id + "/edit"}>
                    <button className="btn btn-sm btn-secondary">
                        <span className="fa fa-edit"/>
                        <span><strong>Edit</strong></span>
                    </button>
                </Link>
                <Link to={"/component/" + props.component.id + "/map"}>
                    <button className="btn btn-sm btn-outline-dark">
                        <span><strong>Map</strong></span>
                    </button>
                </Link>
            </td>
        </tr>
    )
};

export default ComponentListItem;