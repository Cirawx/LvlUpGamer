

import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { Alert, Container } from 'react-bootstrap';

const AdminRoute: React.FC = () => {
    const { user, isLoggedIn } = useAuth();
    
    
    if (!isLoggedIn || !user) {
        
        return <Navigate to="/login" replace />;
    }
    
    
    if (user.role !== 'admin') {
        return (
            <Container className="py-5">
                <Alert variant="danger">
                    Acceso Denegado. No tienes permisos de administrador.
                </Alert>
            </Container>
        );
    }
    
    
    return <Outlet />;
};

export default AdminRoute;