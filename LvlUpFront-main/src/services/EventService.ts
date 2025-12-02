

import axios from 'axios';
import { Event } from '../types/Event';
import { API_ENDPOINTS } from './api.config';


export const EventService = {
    
    async fetchAllEvents(): Promise<Event[]> {
        try {
            const { data } = await axios.get(API_ENDPOINTS.EVENTS);
            return Array.isArray(data) ? data.reverse() : [];
        } catch (error) {
            throw new Error('Error al cargar los eventos.');
        }
    },

    
    async fetchEventById(eventId: string): Promise<Event> {
        try {
            const { data } = await axios.get(`${API_ENDPOINTS.EVENTS}/${eventId}`);
            return data;
        } catch (error) {
            throw new Error('Evento no encontrado.');
        }
    },

    
    async fetchUpcomingEvents(): Promise<Event[]> {
        try {
            const { data } = await axios.get(API_ENDPOINTS.EVENTS);
            if (!Array.isArray(data)) return [];
            
            const now = new Date();
            return data
                .filter(e => new Date(e.date) >= now)
                .sort((a, b) => new Date(a.date).getTime() - new Date(b.date).getTime());
        } catch (error) {
            throw new Error('Error al cargar eventos próximos.');
        }
    },
};

export default EventService;
