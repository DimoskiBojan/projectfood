import React, {useEffect, useState} from "react";
import {useParams, Link} from 'react-router-dom';
import axios from '../../../custom-axios/axios'
import { trackPromise } from 'react-promise-tracker';

const NutrientDetails = (props) => {
    const [nutrient, setNutrient] = useState({});
    const {nutrientId} = useParams();

    useEffect(() => {
        trackPromise(
        axios.get("/api/nutrients/" + nutrientId).then((response) => {
            setNutrient(response.data);
        }));
    }, []);

    return (
        <div>
            <div className="row">
                <Link to={"/nutrients"} className="btn btn-sm btn-info mr-2">Back to Nutrients</Link>
                <h4 className="text-upper text-left">Details about {nutrient.name}</h4>
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
                                <td>{nutrient.name}</td>
                            </tr>
                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">External sources</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Same As:</td>
                                <td>
                                    <ul>
                                        {/*{splitSameAs(nutrient.sameAs).map(sameAs => (
                                            <li><a href={sameAs} target="_blank">{sameAs}</a></li>
                                        ))}*/}
                                    </ul>
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

export default NutrientDetails;