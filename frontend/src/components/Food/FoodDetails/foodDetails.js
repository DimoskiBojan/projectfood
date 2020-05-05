import React, {useEffect, useState} from "react";
import {useParams} from 'react-router-dom';
import axios from '../../../custom-axios/axios'

const FoodDetails = (props) => {
    const [food, setFood] = useState({});
    const {foodId} = useParams();

    useEffect(() => {
        axios.get("/api/food/" + foodId).then((response) => {
            setFood(response.data);
        });
    }, []);

    const splitSameAs = (sameAs) => {
        return (String(sameAs).split(";")).slice(1);
    };

    return (
        <div>
            <div className="row">
                <h4 className="text-upper text-left">Details about {food.name}</h4>
            </div>
            <hr/>
            {food.picture_file_name &&
            <img src={`https://foodb.ca/system/foods/pictures/${String(food.picture_file_name).split(".")[0]}/full/${String(food.picture_file_name).split(".")[0]}.png`}/>
            }
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
                                <td>{food.name}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Scientific name:</td>
                                <td>{food.name_scientific || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Description:</td>
                                <td>{food.description || "Not available"}</td>
                            </tr>
                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">Classification</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Category:</td>
                                <td>{food.category}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Subcategory:</td>
                                <td>{food.subcategory || "Not available"}</td>
                            </tr>
                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">External sources</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Same As:</td>
                                <td>
                                    <ul>
                                        {splitSameAs(food.sameAs).map(sameAs => (
                                            <li><a href={sameAs} target="_blank">{sameAs}</a></li>
                                        ))}
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">Components and Wastestreams</td>
                            </tr>
                            <tr>
                                <td colSpan="2">
                                    <div>
                                        <div className="table-responsive">
                                            <table className="table tr-history table-striped small table-hover" id="componentWastestreamTable">
                                                <thead>
                                                <tr>
                                                    <th scope="col">Component Name</th>
                                                    <th scope="col">WasteStream Name</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                {food.foodComponents && food.foodComponents.map(foodComponent => (
                                                    <tr>
                                                        <td><a href={"/component/" + foodComponent.component.id + "/details"}>{foodComponent.component.name}</a></td>
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

export default FoodDetails;