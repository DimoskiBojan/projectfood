import React, {useEffect} from "react";
import {useLocation} from "react-router-dom";
import $ from 'jquery';
import 'datatables.net';
import StreamsListItem from "../StreamsListItem/streamsListItem";

function useQuery() {
    return new URLSearchParams(useLocation().search);
}

const StreamsList = (props) => {
    let query = useQuery();

    useEffect(() => {
        $(document).ready( function () {
            // DataTable
            let table = $('#streamsTable').DataTable();
            let term = query.get("term");
            if(term != null || term !== "") {
                table.search(term).draw();
            }
            console.log(query.get("term"));
        });
    });

    if(props.streams.length === 0){
        return (
            <div className="row">
                <h4>There are currently no waste streams available.</h4>
            </div>
        )
    }
    return (
        <div className="row">
            <h4 className="text-upper text-left mb-4">Showing Waste Streams</h4>
            <div className="table-responsive">
                <table className="table tr-history table-striped small" id="streamsTable">
                    <thead className="h6">
                    <tr>
                        <th scope="col">Food Name</th>
                        <th scope="col">Component Name</th>
                        <th scope="col">Waste Stream</th>
                    </tr>
                    </thead>
                    <tbody className="h6">
                    {props.streams.map((stream, index) => (
                        <StreamsListItem key={index} stream={stream} />
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
};

export default StreamsList;