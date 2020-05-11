import React, {useState} from "react";
import axios from "../../custom-axios/axios";
import { trackPromise } from 'react-promise-tracker';

const AutoMap = (props) => {

    const [autoMap, setAutoMap] = useState({});

    const autoMapFoodToExternal = () => {
        trackPromise(
        axios.get("/api/automap/food-external").then((response) => {
            setAutoMap({"status": "Complete"});
        }), "automap-area");
    };

    const autoMapFoodToFoods = () => {
        trackPromise(
        axios.get("/api/automap/food-foods").then((response) => {
            setAutoMap({"status": "Complete"});
        }), "automap-area");
    };

    const autoMapFoodsToDbpedia = () => {
        trackPromise(
        axios.get("/api/automap/foods-dbpedia").then((response) => {
            setAutoMap({"status": "Complete"});
        }), "automap-area");
    };

    const autoMapComponentToCompounds = () => {
        trackPromise(
        axios.get("/api/automap/component-compounds").then((response) => {
            setAutoMap({"status": "Complete"});
        }), "automap-area");
    };

    return (
        <div>
            <div className="row">
                <h4 className="text-upper text-left mb-4">Automatic mapping</h4>
            </div>
            <hr/>
            <div className="row">
                <div className={"col-3"}>
                    <button type="button" className="btn btn-info" onClick={autoMapFoodToExternal}>Food to External</button>
                </div>
                <div className={"col-3"}>
                    <button type="button" className="btn btn-info" onClick={autoMapFoodToFoods}>Food to Foods</button>
                </div>
                <div className={"col-3"}>
                    <button type="button" className="btn btn-info" onClick={autoMapFoodsToDbpedia}>Foods to DBpedia</button>
                </div>
                <div className={"col-3"}>
                    <button type="button" className="btn btn-info" onClick={autoMapComponentToCompounds}>Component to Compounds</button>
                </div>
            </div>
            <hr/>
            <div className="row mt-5">
                <p className="h2 mr-2">Status:</p>
                <p className="h2 text-success">{autoMap.status}</p>
            </div>
        </div>
    )
};

export default AutoMap;