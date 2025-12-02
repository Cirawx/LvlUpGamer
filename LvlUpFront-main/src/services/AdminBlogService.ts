

import axios from './axiosConfig';
import { BlogPost, PostFormData } from '../types/Blog';
import { API_ENDPOINTS } from './api.config';


export const AdminBlogService = {
    
    async fetchPosts(): Promise<BlogPost[]> {
        try {
            const { data } = await axios.get(API_ENDPOINTS.BLOGS);
            return Array.isArray(data) ? data.reverse() : [];
        } catch (error) {
            throw new Error('Error al cargar los posts.');
        }
    },

    
    async createPost(payload: PostFormData): Promise<BlogPost> {
        try {
            const { data } = await axios.post(`${API_ENDPOINTS.BLOGS}/admin`, payload);
            return data;
        } catch (error: any) {
            throw error;
        }
    },

    
    async updatePost(postId: string, payload: PostFormData): Promise<BlogPost> {
        try {
            const { data } = await axios.put(`${API_ENDPOINTS.BLOGS}/${postId}/admin`, payload);
            return data;
        } catch (error: any) {
            throw error;
        }
    },

    
    async deletePost(postId: string): Promise<void> {
        try {
            await axios.delete(`${API_ENDPOINTS.BLOGS}/${postId}/admin`);
        } catch (error) {
            throw new Error('Fallo al eliminar el post.');
        }
    },
};

export default AdminBlogService;
