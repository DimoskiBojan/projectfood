import axios from '../custom-axios/axios'
import qs from 'qs'

const FoodService = {
    fetchFoods: () => {
        return axios.get("/api/food")
    },
    fetchFoodsPaged: (page, pageSize) => {
        return axios.get("/api/food", {
            headers: {
                'page': page,
                'page-size': pageSize || 5
            }
        })
    },
    fetchById: (foodId) => {
        return axios.get(`/api/food/${foodId}`);
    },
    updateFoodSameAs: (food) => {
        const foodId = food.id;
        const formParams = qs.stringify(food);
        return axios.patch(`/api/food/${foodId}`, formParams, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    }
};

export default FoodService;