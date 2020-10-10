import React from "react";
import {Link} from "react-router-dom";
import {isAuthenticated} from "../../../repository/userRepository";

const FoodListItem = (props) => {
    return (
        <tr>
            <td><Link to={"/food/" + props.food.id + "/details"}>{props.food.name}</Link></td>
            <td>{props.food.category}</td>
            {
                isAuthenticated() &&
                (
                    <td>
                        <Link to={"/food/" + props.food.id + "/map-external"}>
                            <button className="btn btn-sm btn-secondary mr-2">
                                <span><strong>Map External</strong></span>
                            </button>
                        </Link>
                        <Link to={"/food/" + props.food.id + "/map-foodb"}>
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

export default FoodListItem;