import React from "react";
import {Link} from "react-router-dom";

const FoodListItem = (props) => {
    return (
        <tr>
            <td><a href={"/food/" + props.food.id + "/details"}>{props.food.name}</a></td>
            <td>{props.food.category}</td>
            <td>
                <Link to={"/food/" + props.food.id + "/edit"}>
                    <button className="btn btn-sm btn-secondary">
                        <span className="fa fa-edit"/>
                        <span><strong>Edit</strong></span>
                    </button>
                </Link>
                <Link to={"/food/" + props.food.id + "/map"}>
                    <button className="btn btn-sm btn-outline-dark">
                        <span><strong>Map</strong></span>
                    </button>
                </Link>
            </td>
        </tr>
    )
};

export default FoodListItem;