import React from "react";
import {Link} from "react-router-dom";

const FoodListItem = (props) => {
    return (
        <tr>
            <td><Link to={"/food/" + props.food.id + "/details"}>{props.food.name}</Link></td>
            <td>{props.food.category}</td>
            <td>
                <Link to={"/food/" + props.food.id + "/map"}>
                    <button className="btn btn-sm btn-secondary">
                        <span><strong>Map</strong></span>
                    </button>
                </Link>
            </td>
        </tr>
    )
};

export default FoodListItem;