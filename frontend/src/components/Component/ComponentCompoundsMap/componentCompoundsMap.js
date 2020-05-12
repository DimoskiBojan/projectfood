import React, {useEffect, useState} from "react";
import {useParams, Link} from 'react-router-dom';
import axios from '../../../custom-axios/axios'
import { trackPromise } from 'react-promise-tracker';
import LoadingIndicator from "../../LoadingIndicator/loadingIndicator";
import $ from "jquery"

const ComponentCompoundsMap = (props) => {
    const [component, setComponent] = useState({});
    const [lookup, setLookup] = useState([]);
    const [mapping, setMapping] = useState({});
    const {componentId} = useParams();

    useEffect(() => {
        trackPromise(
            axios.get("/api/component/" + componentId).then((response) => {
                setComponent(response.data);
            }));
    }, []);

    useEffect(() => {
        trackPromise(
            axios.get("/api/component/lookup/compounds?name=" + component.name).then((response) => {
                setLookup(response.data);
            }), "lookup-area");
        if(component.foodb_id != null) {
            setMapping({
                compoundId: component.foodb_id,
                compoundName: component.name,
                compoundDescription: component.description
            })
        }
    }, [component]);

    useEffect(() => {
        $(function () {
            $('[data-toggle="popover"]').popover({
                trigger: "hover"
            });
            $('.toast').toast({
                'autohide': true,
                'delay': 1000
            });
        })
    }, [lookup]);

    const handleAddNewMapping = (e) => {
        e.preventDefault();
        setMapping({
            compoundId: e.currentTarget.getAttribute('data-id'),
            compoundName: e.currentTarget.getAttribute('data-name'),
            compoundDescription: e.currentTarget.getAttribute('data-description')
        });
    };

    const handleRemoveMapping = (e) => {
        e.preventDefault();
        setMapping({});
    };

    const handleSaveMapping = (e) => {
        props.onUpdate(component.id, mapping.compoundId);
        $('.toast').toast('show');
    };

    if(typeof component.name === 'undefined') return null;
    return (
        <div>
            <div className="row">
                <Link to={"/component"} className="btn btn-sm btn-info mr-2">Back to Components</Link>
                <h4 className="text-upper text-left">Mappings for {component.name}</h4>
                <button className="btn btn-success btn-sm align-self-end ml-auto"
                        onClick={handleSaveMapping}>
                    Save
                </button>
            </div>
            <hr/>
            <div className="row">
                <div className="col-12">
                    <div className="table-responsive-sm">
                        <table className="table">
                            <tbody>

                            <tr>
                                <td colSpan="2" className="bg-secondary text-white font-weight-bold">
                                    Information about Component
                                </td>
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
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td className="bg-secondary text-white font-weight-bold">Possible Mappings</td>
                                <td className="bg-secondary text-white font-weight-bold">Mapped Compound</td>
                            </tr>
                            <tr>
                                <td className="bg-light font-weight-bold">
                                    <div className="row">
                                        {lookup.length === 0 &&
                                        <div className="col-12 col-md-6 col-lg-4 mb-3">
                                            The lookup didn't find any possible mappings.
                                        </div>
                                        }
                                    {lookup && lookup.map(compound => (
                                        <div className="col-12 col-md-6 col-lg-4 mb-3">
                                            <a href="#"
                                               className="text-info h6"
                                               data-toggle="popover"
                                               data-trigger="hover"
                                               title={compound.name}
                                               data-content={compound.description || "No description"}>
                                                {compound.name}
                                            </a>
                                            <a href="#"
                                               className="close float-none"
                                               name={"test"}
                                               onClick={handleAddNewMapping}
                                               data-id={compound.id}
                                               data-name={compound.name}
                                               data-description={compound.description || "No description"}>
                                                <span>+</span>
                                            </a>
                                        </div>
                                    ))}
                                    </div>
                                    <LoadingIndicator area={"lookup-area"}/>
                                </td>
                                <td className="font-weight-bold">
                                    {mapping.compoundName &&
                                    (<div>
                                        <a href="#"
                                           className="text-info h6"
                                           data-toggle="popover"
                                           data-trigger="hover"
                                           title={mapping.compoundName}
                                           data-content={mapping.compoundDescription || "No description"}>
                                            {mapping.compoundName}
                                        </a>
                                        <a href="#" className="close float-none" onClick={handleRemoveMapping}>
                                            <span>x</span>
                                        </a>
                                    </div>)
                                    }
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div className="toast bg-success" style={{position: "relative", top: 0, right: 0}}>
                <div className="toast-body">
                    Changes saved.
                </div>
            </div>
        </div>
    )
};

export default ComponentCompoundsMap;