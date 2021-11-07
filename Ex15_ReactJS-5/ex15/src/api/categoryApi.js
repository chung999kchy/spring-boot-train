import axiosClient from "./axiosClient";

const categoryApi = {
    getAll: (params) => {
        const url = '/admin/categories';
        return axiosClient.get(url, { params });
    },

    get: (id) => {
        const url = `/admin/categories/${id}`;
        return axiosClient.get(url);
    },

    create: (data) => {
        const url = '/admin/categories';
        return axiosClient.post(url, data);
    },

    update: (id, data) => {
        const url = `/admin/categories/${id}`;
        return axiosClient.put(url, data);
    },

    delete: (id) => {
        const url = `/admin/categories/${id}`;
        return axiosClient.delete(url);
    }

}

export default categoryApi;