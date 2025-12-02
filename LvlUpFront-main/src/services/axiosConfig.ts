
import axios from "axios";





axios.interceptors.request.use((config) => {
    try {
        const raw = localStorage.getItem("user");
        if (!raw) return config;

        const parsed = JSON.parse(raw);
        if (!parsed?.token) return config;

        config.headers = config.headers || {};
        config.headers.Authorization = `Bearer ${parsed.token}`;
    } catch (err) {
        console.error("Error leyendo token:", err);
    }

    return config;
});

export default axios;
