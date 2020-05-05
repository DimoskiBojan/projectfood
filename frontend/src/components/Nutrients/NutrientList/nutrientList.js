import React, {useEffect} from "react";
import NutrientListItem from "../NutrientListItem/nutrientListItem";
import $ from 'jquery';
import 'datatables.net';

const NutrientList = (props) => {

    useEffect(() => {
        $(document).ready( function () {
            // DataTable
            $('#nutrientsTable').DataTable();
        });
    });

    if(props.nutrients.length === 0){
        return (
            <div className="row">
                <h4>There are currently no nutrients available.</h4>
            </div>
        )
    }
    return (
        <div className="row">
            <h4 className="text-upper text-left mb-4">Showing Nutrients</h4>
            <div className="table-responsive">
                <table className="table tr-history table-striped small" id="nutrientsTable">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {props.nutrients.map(nutrient => (
                        <NutrientListItem key={nutrient.id} nutrient={nutrient} />
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
};

export default NutrientList;