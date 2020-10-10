import React, {useEffect, useState} from "react";
import {useParams, Link} from 'react-router-dom';
import axios from '../../../custom-axios/axios'
import { trackPromise } from 'react-promise-tracker';
import LoadingIndicator from "../../LoadingIndicator/loadingIndicator";
import $ from "jquery"
import {getFooDBCandidate} from "../../../repository/fwo-candidate-foods";

const FoodFoodsMap = (props) => {
    const [food, setFood] = useState({});
    const [lookup, setLookup] = useState([]);
    const [mapping, setMapping] = useState({});
    const {foodId} = useParams();

    useEffect(() => {
        trackPromise(
            axios.get("/api/food/" + foodId).then((response) => {
                setFood(response.data);
            }));
    }, []);

    useEffect(() => {
        var candidates = getFooDBCandidate(food.id);
        if(candidates !== undefined) {
            console.log();
            setLookup(candidates.candidates);
        }
        if(food.foodb_id != null) {
            setMapping({
                foodbId: food.foodb_id,
                foodbName: food.name,
                foodbDescription: food.description
            })
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
        setMapping({
            foodbId: e.currentTarget.getAttribute('data-id'),
            foodbName: e.currentTarget.getAttribute('data-name'),
            foodbDescription: e.currentTarget.getAttribute('data-description')
        });
    };

    const handleRemoveMapping = (e) => {
        e.preventDefault();
        setMapping({});
    };

    const handleSaveMapping = (e) => {
        props.onUpdate(food.id, mapping.foodbId);
        $('.toast').toast('show');
    };

    const handleVerifyMapping = (e) => {};

    if(typeof food.name === 'undefined') return null;
    return (
        <div>
            <div className="row">
                <Link to={"/food"} className="btn btn-sm btn-info mr-2">Back to Foods</Link>
                <h4 className="text-upper text-left">FooDB Mappings for {food.name}</h4>
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
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td className="bg-secondary text-white font-weight-bold">Candidate Mappings</td>
                                <td className="bg-secondary text-white font-weight-bold">Mapped Food</td>
                            </tr>
                            <tr>
                                <td className="bg-light font-weight-bold">
                                    <div className="row">
                                        {lookup.length === 0 &&
                                        <div className="col-12 col-md-6 col-lg-4 mb-3">
                                            The lookup didn't find any candidate mappings.
                                        </div>
                                        }
                                        {lookup && lookup.map((food, index) => (
                                            <div className="col-12 col-md-6 col-lg-4 mb-3" key={index}>
                                                <a href={`https://foodb.ca/foods/${food[1].replace("foodb:", "")}`}
                                                   target="_blank"
                                                   rel="noopener noreferrer"
                                                   className="text-info h6"
                                                   data-toggle="popover"
                                                   data-trigger="hover"
                                                   title={food[0]}
                                                   data-content={food.description || "No description"}>
                                                    {food[0]}
                                                </a>
                                                <a href="#"
                                                   className="close float-none"
                                                   name={"test"}
                                                   onClick={handleAddNewMapping}
                                                   data-id={food[1].replace("foodb:", "")}
                                                   data-name={food[0]}
                                                   data-description={food.description || "No description"}>
                                                    <span>+</span>
                                                </a>
                                            </div>
                                        ))}
                                    </div>
                                    <LoadingIndicator area={"lookup-area"}/>
                                </td>
                                <td className="font-weight-bold">
                                    {mapping.foodbName &&
                                    (<div>
                                        <a href={`https://foodb.ca/foods/${mapping.foodbId}`}
                                           target="_blank"
                                           rel="noopener noreferrer"
                                           className="text-info h6"
                                           data-toggle="popover"
                                           data-trigger="hover"
                                           title={mapping.foodbName}
                                           data-content={mapping.foodbDescription || "No description"}>
                                            {mapping.foodbName}
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
            <div className="toast bg-success" style={{position: "absolute", top: "7%", left: 0, right: 0, marginLeft: "auto", marginRight: "auto", width: "200px"}}>
                <div className="toast-body text-white">
                    Changes saved.
                </div>
            </div>
        </div>
    )
};

export default FoodFoodsMap;