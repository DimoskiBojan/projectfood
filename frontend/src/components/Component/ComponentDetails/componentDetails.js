import React, {useEffect, useState} from "react";
import {useParams, Link} from 'react-router-dom';
import axios from '../../../custom-axios/axios'
import { trackPromise } from 'react-promise-tracker';

const ComponentDetails = (props) => {
    const [component, setComponent] = useState({});
    const {componentId} = useParams();

    useEffect(() => {
        trackPromise(
        axios.get("/api/component/" + componentId).then((response) => {
            setComponent(response.data);
        }));
    }, []);

    if(typeof component.foodComponents === 'undefined') return null;
    return (
        <div>
            <div className="row">
                <Link to={"/component"} className="btn btn-sm btn-info mr-2">Back to Components</Link>
                <h4 className="text-upper text-left">Details about {component.name}</h4>
            </div>
            <hr/>
            <div className="row">
                <div className="col-12">
                    <div className="table-responsive-sm">
                        <table className="table">
                            <tbody>
                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">Information</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Name:</td>
                                <td>{component.name}</td>
                            </tr>
                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">Classification</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Category:</td>
                                <td>{component.category_name}</td>
                            </tr>
                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">External sources</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Same As:</td>
                                <td>
                                    <ul>
                                        {/*{splitSameAs(food.sameAs).map(sameAs => (
                                            <li><a href={sameAs} target="_blank">{sameAs}</a></li>
                                        ))}*/}
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">Associated Foods and Wastestreams</td>
                            </tr>
                            <tr>
                                <td colSpan="2">
                                    <div>
                                        <div className="table-responsive">
                                            <table className="table tr-history table-striped small table-hover" id="componentWastestreamTable">
                                                <thead>
                                                <tr>
                                                    <th scope="col">Food Name</th>
                                                    <th scope="col">WasteStream Name</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                {component.foodComponents && component.foodComponents.map(foodComponent => (
                                                    <tr>
                                                        <td><a href={"/food/" + foodComponent.id.foodId + "/details"}>{foodComponent.foodName}</a></td>
                                                        <td>{String(foodComponent.wasteStreamName)}</td>
                                                    </tr>
                                                ))}
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    )
};

export default ComponentDetails;