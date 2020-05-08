import React from "react";
import {Link} from "react-router-dom";

const StreamsListItem = (props) => {
    return (
        <tr>
            <td><Link to={`/food/${props.stream.id.foodId}/details`}>{props.stream.foodName}</Link></td>
            <td><Link to={`/component/${props.stream.id.componentId}/details`}>{props.stream.componentName}</Link></td>
            <td>{props.stream.wasteStreamName}</td>
        </tr>
    )
};

export default StreamsListItem;