

import React, { createContext, useState, useContext, useEffect, ReactNode } from 'react';
import axios from 'axios'; 
import { API_BASE_URL } from '../services/api.config'; 


export const apiClient = axios.create({
    baseURL: API_BASE_URL, 
});


interface Address {
    street: string;
    city: string;
    region: string;
    zipCode?: string;
}

export interface User {
    id: string;
    name: string;
    email: string;
    rut: string;
    age: number;
    role: 'admin' | 'customer' | 'seller';
    token: string;
    hasDuocDiscount: boolean;
    points: number;
    referralCode: string;
    address: Address; 
}

interface AuthContextType {
    user: User | null;
    isLoggedIn: boolean;
    login: (loginIdentifier: string, password: string) => Promise<boolean>;
    logout: () => void;
    register: (name: string, email: string, password: string) => Promise<boolean>;
    updateProfile: (userData: Partial<User>) => Promise<boolean>;
    
    setUserFromRegistration: (userData: User) => void;
}


const AuthContext = createContext<AuthContextType | undefined>(undefined);


interface AuthProviderProps {
    children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
    
    const [user, setUser] = useState<User | null>(() => {
        const storedUser = localStorage.getItem('user');
        return storedUser ? JSON.parse(storedUser) : null;
    });

    const isLoggedIn = !!user;

    
    useEffect(() => {
        if (user) {
            
            apiClient.defaults.headers.common['Authorization'] = `Bearer ${user.token}`;
            localStorage.setItem('user', JSON.stringify(user));
        } else {
            
            delete apiClient.defaults.headers.common['Authorization'];
            localStorage.removeItem('user');
        }
    }, [user]);

    
    const login = async (loginIdentifier: string, password: string): Promise<boolean> => {
        try {
            
            const res = await apiClient.post('/users/login', { email: loginIdentifier, password }); 
            const userData: User = res.data;

            setUser(userData);
            apiClient.defaults.headers.common['Authorization'] = `Bearer ${userData.token}`; 
            return true;
        } catch (error) {
            setUser(null);
            return false;
        }
    };

    
    const setUserFromRegistration = (userData: User) => {
        setUser(userData);
        apiClient.defaults.headers.common['Authorization'] = `Bearer ${userData.token}`; 
    };

    
    const logout = async () => {
        try {
            if (user?.token) {
                await apiClient.post('/users/logout');
            }
        } catch (error) {
            console.error("Logout failed on server", error);
        } finally {
            
            setUser(null);
            localStorage.removeItem('user');
            delete apiClient.defaults.headers.common['Authorization'];
        }
    };

    
    useEffect(() => {
        const handleStorageChange = (event: StorageEvent) => {
            if (event.key === 'user' && event.newValue === null) {
                setUser(null);
                delete apiClient.defaults.headers.common['Authorization'];
            }
        };

        window.addEventListener('storage', handleStorageChange);
        return () => {
            window.removeEventListener('storage', handleStorageChange);
        };
    }, []);

    
    useEffect(() => {
        const interceptor = apiClient.interceptors.response.use(
            (response) => response,
            (error) => {
                if (error.response && error.response.status === 401) {
                    logout();
                }
                return Promise.reject(error);
            }
        );

        return () => {
            apiClient.interceptors.response.eject(interceptor);
        };
    }, []);

    const register = async (name: string, email: string, password: string): Promise<boolean> => {
        try {
            const res = await apiClient.post('/users/register', { name, email, password }); 
            const userData: User = res.data;
            setUser(userData);
            apiClient.defaults.headers.common['Authorization'] = `Bearer ${userData.token}`; 
            return true;
        } catch (error) {
            return false;
        }
    };

    const updateProfile = async (userData: Partial<User>): Promise<boolean> => {
        try {
            
            
            
            
            const res = await apiClient.put('/users/profile', userData); 
            const updatedUserData: User = res.data;
            setUser(updatedUserData);
            apiClient.defaults.headers.common['Authorization'] = `Bearer ${updatedUserData.token}`; 
            return true;
        } catch (error) {
            return false;
        }
    };

    const value = {
        user,
        isLoggedIn,
        login,
        logout,
        register,
        updateProfile,
        setUserFromRegistration,
    };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};


export const useAuth = () => {
    const context = useContext(AuthContext);
    if (context === undefined) {
        throw new Error('useAuth debe ser usado dentro de un AuthProvider');
    }
    return context;
};