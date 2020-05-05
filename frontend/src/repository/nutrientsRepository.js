import axios from '../custom-axios/axios'

const NutrientsService = {
    fetchNutrients: () => {
        return axios.get("/api/nutrients")
    },
    fetchById: (nutrientId) => {
        return axios.get(`/api/nutrients/${nutrientId}`);
    }
};

export default NutrientsService;