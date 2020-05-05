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

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      food: [],
      component: [],
      nutrients: []
    }
  }

  componentDidMount() {
    this.loadFood();
    this.loadComponent();
    this.loadNutrients();
  };

  loadFood = () => {
    foodService.fetchFoods().then(response => {
        this.setState({"food": response.data})
    })
  };

  loadComponent = () => {
    componentService.fetchComponents().then(response => {
        this.setState({"component": response.data})
    })
  };

  loadNutrients = () => {
    nutrientsService.fetchNutrients().then(response => {
      this.setState({"nutrients": response.data})
    })
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
                 <Route path={"/food/:foodId/details"}>
                   <FoodDetails/>
                 </Route>
                 <Route path={"/food/"}>
                   <FoodList food={this.state.food}/>
                 </Route>
                 <Route path={"/component/:componentId/details"}>
                   <ComponentDetails/>
                 </Route>
                 <Route path={"/component/"}>
                   <ComponentList component={this.state.component}/>
                 </Route>
                 <Route path={"/nutrients/:nutrientId/details"}>
                   <NutrientDetails/>
                 </Route>
                 <Route path={"/nutrients/"}>
                   <NutrientList nutrients={this.state.nutrients}/>
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