

import axios from 'axios';
import { Reward } from '../types/Reward';
import { API_ENDPOINTS } from './api.config';


export const RewardService = {
    
    async fetchAllRewards(): Promise<Reward[]> {
        try {
            const { data } = await axios.get(API_ENDPOINTS.REWARDS);
            return Array.isArray(data) ? data.filter(r => r.isActive) : [];
        } catch (error) {
            throw new Error('Error al cargar las recompensas.');
        }
    },

    
    async fetchRewardById(rewardId: string): Promise<Reward> {
        try {
            const { data } = await axios.get(`${API_ENDPOINTS.REWARDS}/${rewardId}`);
            return data;
        } catch (error) {
            throw new Error('Recompensa no encontrada.');
        }
    },

    
    async fetchByType(type: string): Promise<Reward[]> {
        try {
            const { data } = await axios.get(API_ENDPOINTS.REWARDS);
            return Array.isArray(data)
                ? data.filter(r => r.isActive && r.type === type)
                : [];
        } catch (error) {
            throw new Error('Error al cargar recompensas por tipo.');
        }
    },

    
    async fetchBySeason(season: string): Promise<Reward[]> {
        try {
            const { data } = await axios.get(API_ENDPOINTS.REWARDS);
            return Array.isArray(data)
                ? data.filter(r => r.isActive && r.season === season)
                : [];
        } catch (error) {
            throw new Error('Error al cargar recompensas por temporada.');
        }
    },

    
    async fetchByPointsCost(ascending = true): Promise<Reward[]> {
        try {
            const { data } = await axios.get(API_ENDPOINTS.REWARDS);
            if (!Array.isArray(data)) return [];
            
            return data
                .filter(r => r.isActive)
                .sort((a, b) =>
                    ascending
                        ? a.pointsCost - b.pointsCost
                        : b.pointsCost - a.pointsCost
                );
        } catch (error) {
            throw new Error('Error al cargar recompensas ordenadas.');
        }
    },
};

export default RewardService;
