import axios from 'axios';
import { Product } from '../types/Product'; 

const API_URL = '/api/products';

export const fetchProducts = async (): Promise<Product[]> => {
    const { data } = await axios.get<Product[]>(API_URL);
    return data;
};


