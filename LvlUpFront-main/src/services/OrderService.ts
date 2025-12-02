

import axios from './axiosConfig';
import { Order, OrderUpdatePayload } from '../types/Order';
import { API_ENDPOINTS } from './api.config';


export const OrderService = {
    
    async fetchMyOrders(): Promise<Order[]> {
        try {
            const { data } = await axios.get(`${API_ENDPOINTS.ORDERS}/myorders`);
            return Array.isArray(data) ? data.reverse() : [];
        } catch (error) {
            throw new Error('Error al cargar tus órdenes.');
        }
    },

    
    async fetchOrderById(orderId: string): Promise<Order> {
        try {
            const { data } = await axios.get(`${API_ENDPOINTS.ORDERS}/${orderId}`);
            return data;
        } catch (error) {
            throw new Error('Orden no encontrada.');
        }
    },

    
    async createOrder(orderData: any): Promise<Order> {
        try {
            const { data } = await axios.post(API_ENDPOINTS.ORDERS, orderData);
            return data;
        } catch (error: any) {
            throw error;
        }
    },

    
    async cancelOrder(orderId: string): Promise<Order> {
        try {
            const { data } = await axios.put(
                `${API_ENDPOINTS.ORDERS}/${orderId}/status`,
                { status: 'Cancelado' }
            );
            return data;
        } catch (error) {
            throw new Error('No se pudo cancelar la orden.');
        }
    },
};

export default OrderService;
