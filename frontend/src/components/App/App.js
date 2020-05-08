import React, {Component} from 'react';
import './App.css';
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom'
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
import StreamsList from "../Streams/StreamsList/streamsList";

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
    }));
  };

  loadComponent = () => {
    trackPromise(
    componentService.fetchComponents().then(response => {
        this.setState({"component": response.data})
    }));
  };

  loadStreams = () => {
    trackPromise(
    foodService.fetchStreams().then(response => {
      this.setState({"streams": response.data})
    }));
  };

  loadNutrients = () => {
    trackPromise(
    nutrientsService.fetchNutrients().then(response => {
      this.setState({"nutrients": response.data})
    }));
  };

  updateFoodSameAs = (food) => {
    foodService.updateFoodSameAs(food).then(response => {
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

  render() {
     return (
         <Router>
            <Header/>
           <main role="main" className="mt-3">
             <div className="container">
               <Switch>
                 <Route path={"/streams/"}>
                   <LoadingIndicator/>
                   <StreamsList streams={this.state.streams}/>
                 </Route>
                 <Route path={"/food/:foodId/details"}>
                   <LoadingIndicator/>
                   <FoodDetails/>
                 </Route>
                 <Route path={"/food/"}>
                   <LoadingIndicator/>
                   <FoodList food={this.state.food}/>
                 </Route>
                 <Route path={"/component/:componentId/details"}>
                   <LoadingIndicator/>
                   <ComponentDetails/>
                 </Route>
                 <Route path={"/component/"}>
                   <LoadingIndicator/>
                   <ComponentList component={this.state.component}/>
                 </Route>
                 <Route path={"/nutrients/:nutrientId/details"}>
                   <LoadingIndicator/>
                   <NutrientDetails/>
                 </Route>
                 <Route path={"/nutrients/"}>
                   <LoadingIndicator/>
                   <NutrientList nutrients={this.state.nutrients}/>
                 </Route>
                 <Route path={"/automap/"}>
                   <AutoMap/>
                   <LoadingIndicator/>
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