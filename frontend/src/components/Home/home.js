import React, {useEffect, useState} from "react";
import {Link} from 'react-router-dom'
import $ from "jquery";
import axios from "../../custom-axios/axios";

const Home = (props) => {

    const [foods, setFoods] = useState([]);

    useEffect(() => {
        $(document).ready( function () {
            // DataTable
            $('#foodCategoryModal').on('show.bs.modal', function (event) {
                let link = $(event.relatedTarget);
                let category = link.data('category');
                console.log(category);
                axios.get(`/api/food?category=${category}`).then((response) => {
                    setFoods(response.data);
                });
            })
        });
    });

    return (
        <div>
            <div className="modal fade" id="foodCategoryModal" tabIndex="-1" role="dialog"
                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title text-secondary font-weight-bold">Select Food</h5>
                            <button aria-label="Close" className="close" data-dismiss="modal" type="button">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div className="modal-body" id="citationBody">
                            <div>
                                <div className="row">
                                    {foods.map(food => (
                                        <div className="col-12 col-md-6 col-lg-4 mb-3">
                                            <a href={`/food/${food.id}/details`} className="text-info h6">
                                                {food.name}
                                            </a>
                                        </div>
                                    ))}
                                </div>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <div className="row mt-5">
                <div className={"col-12 text-center text-info"}>
                    <h1 className="font-weight-bold">Project Food</h1>
                </div>
            </div>
            <div className="row mb-5 text-">
                <div className={"col-12 text-center text-secondary"}>
                    <h2>Explore data about food collected from various sources</h2>
                </div>
            </div>

            <div className="row">
                <div className={"col-12 text-center text-info"}>
                    <h3><i className="fa fa-search" aria-hidden="true"></i> Search</h3>
                </div>
            </div>
            <div className="row mb-1">
                <div className={"col-12 text-center text-secondary"}>
                    <h5>Waste streams by term</h5>
                </div>
            </div>
            <div className="row justify-content-center">
                <div className="input-group  border border-info rounded-pill px-1 w-50">
                    <input type="search" placeholder="Search term" className="form-control bg-none border-0"/>
                        <div className="input-group-append border-0">
                            <button id="button-search" type="button" className="btn btn-link text-info">
                                <i className="fa fa-search"/>
                            </button>
                        </div>
                </div>
            </div>

            <div className="row mt-5">
                <div className={"col-12 text-center text-info"}>
                    <h2>Browse</h2>
                </div>
            </div>
            <div className="row mb-4">
                <div className={"col-12 text-center text-secondary"}>
                    <h5>Various types of Data</h5>
                </div>
            </div>
            <div className="row">
                <div className={"col-3 text-center"}>
                    <Link to={"/food"} className={"h4 text-success"}>Foods ></Link>
                </div>
                <div className={"col-3 text-center"}>
                    <Link to={"/component"} className={"h4 text-success"}>Components ></Link>
                </div>
                <div className={"col-3 text-center"}>
                    <Link to={"/nutrients"} className={"h4 text-success"}>Nutrients ></Link>
                </div>
                <div className={"col-3 text-center"}>
                    <Link to={"/streams"} className={"h4 text-success"}>Waste Streams ></Link>
                </div>
            </div>

            <div className="row mt-5">
                <div className={"col-12 text-center text-info"}>
                    <h2>Food Categories</h2>
                </div>
            </div>
            <div className="row mb-4">
                <div className={"col-12 text-center text-secondary"}>
                    <h5>See foods belonging to a certain category</h5>
                </div>
            </div>
            <div className="row">
                <div className="col-12">
                    <div className="row justify-content-center text-center">

                        <div className="col-md-2">
                            <a href="#" data-toggle="modal" data-target="#foodCategoryModal" data-category="Cereals">
                                <div className="img-container">
                                    <img src={require("./icons/28_creals.png")}/>
                                </div>
                                <div className="text-success h5">Cereals</div>
                            </a>
                        </div>

                        <div className="col-md-2">
                            <a href="#" data-toggle="modal" data-target="#foodCategoryModal" data-category="Milk and dairy">
                                <div className="img-container">
                                    <img src={require("./icons/29_milk_diary.png")}/>
                                </div>
                                <div className="text-success h5">Milk &amp; Diary</div>
                            </a>
                        </div>

                        <div className="col-md-2">
                            <a href="#" data-toggle="modal" data-target="#foodCategoryModal" data-category="Eggs">
                                <div className="img-container">
                                    <img src={require("./icons/30_eggs.png")}/>
                                </div>
                                <div className="text-success h5">Eggs</div>
                            </a>
                        </div>

                        <div className="col-md-2">
                            <a href="#" data-toggle="modal" data-target="#foodCategoryModal" data-category="Fats and oils, nuts and seeds">
                                <div className="img-container">
                                    <img src={require("./icons/31_fats_oils_nuts_seeds_31.png")}/>
                                </div>
                                <div className="text-success h5">Fats &amp; Oils, Nuts &amp; Seeds</div>
                            </a>
                        </div>

                        <div className="col-md-2">
                            <a href="#" data-toggle="modal" data-target="#foodCategoryModal" data-category="Meat">
                                <div className="img-container">
                                    <img src={require("./icons/32_meat_32.png")}/>
                                </div>
                                <div className="text-success h5">Meat</div>
                            </a>
                        </div>
                    </div>

                    <div className="row justify-content-center text-center">
                        <div className="col-md-2">
                            <a href="#" data-toggle="modal" data-target="#foodCategoryModal" data-category="Fish and seafood">
                                <div className="img-container">
                                    <img src={require("./icons/37_fish_sea_food_37.png")}/>
                                </div>
                                <div className="text-success h5">Fish and Seafood</div>
                            </a>
                        </div>

                        <div className="col-md-2">
                            <a href="#" data-toggle="modal" data-target="#foodCategoryModal" data-category="Fruit and vegetables">
                                <div className="img-container">
                                    <img src={require("./icons/36_fruits_vegetables_36.png")}/>
                                </div>
                                <div className="text-success h5">Fruits &amp; Vegetables</div>
                            </a>
                        </div>

                        <div className="col-md-2">
                            <a href="#" data-toggle="modal" data-target="#foodCategoryModal" data-category="Sugars, preserves and snacks">
                                <div className="img-container">
                                    <img src={require("./icons/35_sugars_preservers_35.png")}/>
                                </div>
                                <div className="text-success h5">Sugars, Preservers &amp; Snacks</div>
                            </a>
                        </div>

                        <div className="col-md-2">
                            <a href="#" data-toggle="modal" data-target="#foodCategoryModal" data-category="Beverages and alcoholic beverages">
                                <div className="img-container">
                                    <img src={require("./icons/34_beverages.png")}/>
                                </div>
                                <div className="text-success h5">Beverages</div>
                            </a>
                        </div>

                        <div className="col-md-2">
                            <a href="#" data-toggle="modal" data-target="#foodCategoryModal" data-category="Other">
                                <div className="img-container">
                                    <img src={require("./icons/33_other_33.png")}/>
                                </div>
                                <div className="text-success h5">Other</div>
                            </a>
                        </div>

                    </div>

                </div>

            </div>

        </div>
    )
};

export default Home;