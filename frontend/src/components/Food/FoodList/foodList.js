import React, {useEffect} from "react";
import FoodListItem from "../FoodListItem/foodListItem";
import {isAuthenticated} from "../../../repository/userRepository";
import $ from 'jquery';
import 'datatables.net';

const FoodList = (props) => {

    useEffect(() => {
        $(document).ready( function () {
            // DataTable
            $('#foodTable').DataTable();
        });
    });

    if(props.food.length === 0){
        return (
            <div className="row">
                <h4>There are currently no foods available.</h4>
            </div>
        )
    }
    return (
        <div className="row">
            <h4 className="text-upper text-left mb-4">Showing Foods</h4>
            <div className="table-responsive">
                <table className="table tr-history table-striped small" id="foodTable">
                    <thead className="h6">
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        {isAuthenticated() && (<th scope="col">Actions</th>)}
                    </tr>
                    </thead>
                    <tbody className="h6">
                    {props.food.map(food => (
                        <FoodListItem key={food.id} food={food} />
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
};

export default FoodList;