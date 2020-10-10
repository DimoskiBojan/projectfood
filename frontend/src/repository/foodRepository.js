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
    fetchStreams: () => {
        return axios.get("/api/food/streams");
    },
    fetchById: (foodId) => {
        return axios.get(`/api/food/${foodId}`);
    },
    updateFoodSameAs: (foodId, mapping) => {
        return axios.patch(`/api/food/${foodId}/sameas`, qs.stringify({'sameAs': mapping}), {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    },
    updateFoodFooDBId: (id, foodbId) => {
        return axios.patch(`/api/food/${id}/foodb-id`, qs.stringify({'foodbId': foodbId}), {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    }
};

export default FoodService;