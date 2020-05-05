import React from "react";
import {Link} from "react-router-dom";

const NutrientListItem = (props) => {
    return (
        <tr>
            <td><a href={"/nutrients/" + props.nutrient.id + "/details"}>{props.nutrient.name}</a></td>
            <td>
                <Link to={"/nutrients/" + props.nutrient.id + "/edit"}>
                    <button className="btn btn-sm btn-secondary">
                        <span className="fa fa-edit"/>
                        <span><strong>Edit</strong></span>
                    </button>
                </Link>
                <Link to={"/nutrients/" + props.nutrient.id + "/map"}>
                    <button className="btn btn-sm btn-outline-dark">
                        <span><strong>Map</strong></span>
                    </button>
                </Link>
            </td>
        </tr>
    )
};

export default NutrientListItem;