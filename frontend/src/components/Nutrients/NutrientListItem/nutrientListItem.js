import React from "react";
import {Link} from "react-router-dom";

const NutrientListItem = (props) => {
    return (
        <tr>
            <td><Link to={"/nutrients/" + props.nutrient.id + "/details"}>{props.nutrient.name}</Link></td>
        </tr>
    )
};

export default NutrientListItem;