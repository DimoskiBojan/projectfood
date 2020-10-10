import React, {useEffect, useState} from "react";
import {useParams, Link} from 'react-router-dom';
import axios from '../../../custom-axios/axios'
import { trackPromise } from 'react-promise-tracker';
import LoadingIndicator from "../../LoadingIndicator/loadingIndicator";
import $ from "jquery"

const FoodExternalMap = (props) => {
    const [food, setFood] = useState({});
    const [lookup, setLookup] = useState([]);
    const [mapping, setMapping] = useState("");
    const {foodId} = useParams();

    useEffect(() => {
        trackPromise(
            axios.get("/api/food/" + foodId).then((response) => {
                setFood(response.data);
            }));
    }, []);

    useEffect(() => {
        getLookup(food.name);
        if(food.sameAs != null) {
            setMapping(food.sameAs)
        }
    }, [food]);

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
        setMapping(mapping.concat(`;${e.currentTarget.getAttribute('data-id')}`));
        setLookup(lookup.filter(lookup => lookup.uri !== e.currentTarget.getAttribute('data-id')));
    };

    const handleRemoveMapping = (e) => {
        e.preventDefault();
        setMapping(mapping.replace(`;${e.currentTarget.getAttribute('data-id')}`, ""));
    };

    const handleSaveMapping = (e) => {
        props.onUpdate(food.id, mapping);
        $('.toast').toast('show');
    };

    const handleVerifyMapping = (e) => {};

    const performLookup = (e) => {
        e.preventDefault();
        getLookup(e.target.lookupTerm.value);
    }

    const getLookup = (term) => {
        if(term !== undefined) {
            trackPromise(
                axios.get("/api/food/lookup/external?term=" + term).then((response) => {
                    let filteredLookup = [...response.data.dbpedia, ...response.data.snomedct, ...response.data.foodon];
                    if (food.sameAs) {
                        filteredLookup = filteredLookup.filter(lookup => !food.sameAs.includes(lookup.uri));
                    }
                    setLookup(filteredLookup);
                }), "lookup-area");
        }
    }

    const splitSameAs = (sameAs) => {
        return (String(sameAs).split(";")).slice(1);
    };

    if(typeof food.name === 'undefined') return null;
    return (
        <div>
            <div className="row">
                <Link to={"/food"} className="btn btn-sm btn-info mr-2">Back to Foods</Link>
                <h4 className="text-upper text-left">External Mappings for {food.name}</h4>
                <button className="btn btn-success btn-sm align-self-end ml-auto"
                        onClick={handleSaveMapping}>
                    Save
                </button>
                <button className="btn btn-primary btn-sm align-self-end ml-2"
                        onClick={handleVerifyMapping}>
                    Verify
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
                                    Information about Food
                                </td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Name:</td>
                                <td>{food.name}</td>
                            </tr>
                            <tr>
                                <td className="bg-info text-white font-weight-bold">Category:</td>
                                <td>{food.category}</td>
                            </tr>
                            <tr>
                                <td colSpan="2">
                                    <form onSubmit={performLookup} className="form-inline">
                                        <div className="form-group">
                                            <label htmlFor="lookupTerm" className="sr-only">Change lookup term</label>
                                            <input name="lookupTerm" id="lookupTerm" className="form-control" placeholder="Change lookup term" required=""
                                                   autoFocus=""/>
                                        </div>
                                        <button type="submit" className="btn btn-primary ml-2">Lookup</button>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td className="bg-secondary text-white font-weight-bold">Candidate Mappings</td>
                                <td className="bg-secondary text-white font-weight-bold">Confirmed Mappings</td>
                            </tr>
                            <tr>
                                <td className="bg-light font-weight-bold">
                                    <div className="row">
                                        {typeof lookup === 'undefined' &&
                                        <div className="col-12 col-md-6 col-lg-4 mb-3">
                                            The lookup didn't find any candidate mappings.
                                        </div>
                                        }
                                        {lookup && lookup.map((lookup, index) => (
                                            <div className="col-12 col-md-6 col-lg-4 mb-2" key={index}>
                                                <a href={lookup.uri}
                                                   target="_blank"
                                                   rel="noopener noreferrer"
                                                   className="text-info h6"
                                                   data-toggle="popover"
                                                   data-trigger="hover"
                                                   title={lookup.label}
                                                   data-content={lookup.comment || "No description"}>
                                                    {lookup.label}
                                                </a>
                                                <a href="#"
                                                   className="close float-none"
                                                   name={"test"}
                                                   onClick={handleAddNewMapping}
                                                   data-id={lookup.uri}
                                                   data-name={lookup.label}
                                                   data-description={lookup.comment || "No description"}>
                                                    <span>+</span>
                                                </a>
                                            </div>
                                        ))}
                                    </div>
                                    <LoadingIndicator area={"lookup-area"}/>
                                </td>
                                <td className="font-weight-bold w-40">
                                    {splitSameAs(mapping).map(sameAs => (
                                        <div className="col-12" key={sameAs}>
                                            <a href={sameAs}
                                               target="_blank"
                                               rel="noopener noreferrer"
                                               className="text-info h6 word-break-all"
                                               data-toggle="popover"
                                               data-trigger="hover"
                                               title={sameAs}
                                               data-content={sameAs || "No description"}>
                                                {sameAs}
                                            </a>
                                            <a href="#" className="close float-none" onClick={handleRemoveMapping} data-id={sameAs}>
                                                <span>x</span>
                                            </a>
                                        </div>
                                    ))}
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div className="toast bg-success" style={{position: "absolute", top: "7%", left: 0, right: 0, marginLeft: "auto", marginRight: "auto", width: "200px"}}>
                <div className="toast-body text-white">
                    Changes saved.
                </div>
            </div>
        </div>
    )
};

export default FoodExternalMap;