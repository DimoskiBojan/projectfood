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

    const splitSameAs = (sameAs) => {
        return (String(sameAs).split(";")).slice(1);
    };

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
                                <td className="bg-info text-white font-weight-bold">Category:</td>
                                <td>{component.category_name}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Description:</td>
                                <td>{component.description || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">CAS Number:</td>
                                <td>{component.cas_number || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Structure:</td>
                                <td>
                                    <img src={`https://foodb.ca/structures/${component.foodb_id}/thumb.svg`} />
                                </td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Chemical Formula:</td>
                                <td>{component.moldb_formula || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">IUPAC name:</td>
                                <td>{component.moldb_iupac || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">InChI Identifier:</td>
                                <td>{component.moldb_inchi || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">InChI Key:</td>
                                <td>{component.moldb_inchikey || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Isomeric SMILES:</td>
                                <td>{component.moldb_smiles || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Average Molecular Weight:</td>
                                <td>{component.moldb_average_mass || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Monoisotopic Molecular Weight:</td>
                                <td>{component.moldb_mono_mass || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Mass Composition:</td>
                                <td>{component.percent_composition || "Not available"}</td>
                            </tr>

                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">Classification</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Kingdom:</td>
                                <td>{component.kingdom || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Super Class:</td>
                                <td>{component.superklass || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Class:</td>
                                <td>{component.klass || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Sub Class:</td>
                                <td>{component.subklass || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Direct Parent:</td>
                                <td>{component.direct_parent || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Molecular Framework:</td>
                                <td>{component.molecular_framework || "Not available"}</td>
                            </tr>

                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">Predicted Properties</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Water Solubility:</td>
                                <td>{component.moldb_alogps_solubility || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">logP (ALOGPS):</td>
                                <td>{component.moldb_alogps_logp || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">logP:</td>
                                <td>{component.moldb_logp || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">logS:</td>
                                <td>{component.moldb_alogps_logs || "Not available"}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">pKa:</td>
                                <td>{component.moldb_pka || "Not available"}</td>
                            </tr>

                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">External sources</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Same As:</td>
                                <td>
                                    <ul>
                                        {component.foodb_id && (
                                            <li>
                                                <a href={`https://foodb.ca/compounds/${component.foodb_id}`} target="_blank" rel="noopener noreferrer">
                                                    {`https://foodb.ca/compounds/${component.foodb_id}`}
                                                </a>
                                            </li>
                                        )}
                                        {splitSameAs(component.sameAs).map((sameAs, index) => (
                                            <li key={index}><a href={sameAs} target="_blank" rel="noopener noreferrer">{sameAs}</a></li>
                                        ))}
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
                                                {component.foodComponents && component.foodComponents.map((foodComponent, index) => (
                                                    <tr key={index}>
                                                        <td><Link to={"/food/" + foodComponent.id.foodId + "/details"}>{foodComponent.foodName}</Link></td>
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