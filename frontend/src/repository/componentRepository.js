import axios from '../custom-axios/axios'
import qs from "qs";

const ComponentService = {
    fetchComponents: () => {
        return axios.get("/api/component")
    },
    fetchById: (componentId) => {
        return axios.get(`/api/component/${componentId}`);
    },
    updateComponentCompoundId: (id, compoundId) => {
        return axios.patch(`/api/component/${id}`, qs.stringify({'compoundId': compoundId}), {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    }
};

export default ComponentService;