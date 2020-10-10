import React, {Component} from 'react';
import './App.css';
import {HashRouter as Router, Redirect, Route, Switch} from 'react-router-dom'
import foodService from "../../repository/foodRepository";
import componentService from "../../repository/componentRepository"
import nutrientsService from "../../repository/nutrientsRepository"
import Header from '../Header/header';
import FoodList from '../Food/FoodList/foodList';
import FoodDetails from "../Food/FoodDetails/foodDetails";
import ComponentList from "../Component/ComponentList/componentList";
import ComponentDetails from "../Component/ComponentDetails/componentDetails";
import NutrientList from "../Nutrients/NutrientList/nutrientList";
import NutrientDetails from "../Nutrients/NutrientDetails/nutrientDetails";
import AutoMap from "../AutoMap/autoMap";
import LoadingIndicator from "../LoadingIndicator/loadingIndicator";
import { trackPromise } from 'react-promise-tracker';
import Home from "../Home/home";
import AdminLogin from "../Login/adminLogin";
import Register from "../Register/register";
import StreamsList from "../Streams/StreamsList/streamsList";
import ComponentCompoundsMap from "../Component/ComponentCompoundsMap/componentCompoundsMap";
import FoodExternalMap from "../Food/FoodExternalMap/foodExternalMap";
import FoodFoodsMap from "../Food/FoodFoodsMap/foodFoodsMap";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      food: [],
      component: [],
      nutrients: [],
      streams: []
    }
  }

  componentDidMount() {
    this.loadFood();
    this.loadComponent();
    this.loadStreams();
    this.loadNutrients();
  };

  loadFood = () => {
    trackPromise(
    foodService.fetchFoods().then(response => {
        this.setState({"food": response.data})
    }), "foods-area");
  };

  loadComponent = () => {
    trackPromise(
    componentService.fetchComponents().then(response => {
        this.setState({"component": response.data})
    }), "components-area");
  };

  loadStreams = () => {
    trackPromise(
    foodService.fetchStreams().then(response => {
      this.setState({"streams": response.data})
    }), "streams-area");
  };

  loadNutrients = () => {
    trackPromise(
    nutrientsService.fetchNutrients().then(response => {
      this.setState({"nutrients": response.data})
    }), "nutrients-area");
  };

  updateFoodSameAs = (foodId, mapping) => {
    console.log(foodId);
    console.log(mapping);
    foodService.updateFoodSameAs(foodId, mapping).then(response => {
      const newFood = response.data;
      this.setState((prevState) => {
        const newFoodRef = prevState.food.map((item)=>{
          if (item.id===newFood.id) {
            return newFood;
          }
          return item;
        });
        return {
          "food": newFoodRef
        }
      });
    })
  };

  updateFoodFooDBId = (id, foodbId) => {
    foodService.updateFoodFooDBId(id, foodbId).then(response => {
      this.loadFood();
    })
  };

  updateComponentCompoundId = (id, compoundId) => {
    componentService.updateComponentCompoundId(id, compoundId).then(response => {
      // component object not available to filter existing state
      this.loadComponent();
    })
  };

  render() {
     return (
         <Router>
            <Header/>
           <main role="main" className="mt-3">
             <div className="container">
               <Switch>
                 <Route path={"/streams/"}>
                   <LoadingIndicator area={"streams-area"}/>
                   <StreamsList streams={this.state.streams}/>
                 </Route>
                 <Route path={"/food/:foodId/details"}>
                   <LoadingIndicator/>
                   <FoodDetails/>
                 </Route>
                 <Route path={"/food/:foodId/map-external"}>
                   <LoadingIndicator/>
                   <FoodExternalMap onUpdate={this.updateFoodSameAs}/>
                 </Route>
                 <Route path={"/food/:foodId/map-foodb"}>
                   <LoadingIndicator/>
                   <FoodFoodsMap onUpdate={this.updateFoodFooDBId}/>
                 </Route>
                 <Route path={"/food/"}>
                   <LoadingIndicator area={"foods-area"}/>
                   <FoodList food={this.state.food}/>
                 </Route>
                 <Route path={"/component/:componentId/details"}>
                   <LoadingIndicator/>
                   <ComponentDetails/>
                 </Route>
                 <Route path={"/component/:componentId/map-foodb"}>
                   <LoadingIndicator/>
                   <ComponentCompoundsMap onUpdate={this.updateComponentCompoundId}/>
                 </Route>
                 <Route path={"/component/"}>
                   <LoadingIndicator area={"components-area"}/>
                   <ComponentList component={this.state.component}/>
                 </Route>
                 <Route path={"/nutrients/:nutrientId/details"}>
                   <LoadingIndicator/>
                   <NutrientDetails/>
                 </Route>
                 <Route path={"/nutrients/"}>
                   <LoadingIndicator area={"nutrients-area"}/>
                   <NutrientList nutrients={this.state.nutrients}/>
                 </Route>
                 <Route path={"/automap/"}>
                   <AutoMap/>
                   <LoadingIndicator area={"automap-area"}/>
                 </Route>
                 <Route path={"/register"}>
                  <Register/>
                </Route>
                 <Route path={"/login"}>
                   <AdminLogin/>
                 </Route>
                 <Route path={"/"}>
                   <Home/>
                 </Route>
                 <Redirect to={"/"} />
               </Switch>
             </div>
           </main>
         </Router>
    );
  }
}

export default App;
