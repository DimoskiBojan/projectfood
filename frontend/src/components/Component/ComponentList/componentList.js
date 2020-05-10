import React, {useEffect} from "react";
import ComponentListItem from "../ComponentListItem/componentListItem";
import $ from 'jquery';
import 'datatables.net';

const ComponentList = (props) => {

    useEffect(() => {
        $(document).ready( function () {
            // DataTable
            $('#componentTable').DataTable();
        });
    });

    if(props.component.length === 0){
        return (
            <div className="row">
                <h4>There are currently no components available.</h4>
            </div>
        )
    }
    return (
        <div className="row">
            <h4 className="text-upper text-left mb-4">Showing Components</h4>
            <div className="table-responsive">
                <table className="table tr-history table-striped small" id="componentTable">
                    <thead className="h6">
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Formula</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody className="h6">
                    {props.component.map(component => (
                        <ComponentListItem key={component.id} component={component} />
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
};

export default ComponentList;