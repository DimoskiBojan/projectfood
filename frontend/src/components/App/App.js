import React, {Component} from 'react';
import './App.css';
import {BrowserRouter as Router, Redirect, Route, Switch} from 'react-router-dom'
import foodService from "../../repository/foodRepository";
import Header from '../Header/header';
import FoodList from '../Food/FoodList/foodList';
import FoodDetails from "../Food/FoodDetails/foodDetails";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      food: []
    }
  }

  componentDidMount() {
    this.loadFood();
  };

  loadFood = () => {
    foodService.fetchFoods().then(response => {
        this.setState({"food": response.data})
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
                 <Redirect to={"/"} />
               </Switch>
             </div>
           </main>
         </Router>
    );
  }
}

export default App;