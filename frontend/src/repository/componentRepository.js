import axios from '../custom-axios/axios'

const ComponentService = {
    fetchComponents: () => {
        return axios.get("/api/component")
    },
    fetchById: (componentId) => {
        return axios.get(`/api/component/${componentId}`);
    }
};

export default ComponentService;