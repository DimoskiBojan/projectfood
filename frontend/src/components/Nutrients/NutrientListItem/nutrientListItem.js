import React from "react";
import {Link} from "react-router-dom";

const NutrientListItem = (props) => {
    return (
        <tr>
            <td><a href={"/nutrients/" + props.nutrient.id + "/details"}>{props.nutrient.name}</a></td>
        </tr>
    )
};

export default NutrientListItem;