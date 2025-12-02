

import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';


const LOGO_URL = '/images/logo.png'; 
const HERO_PHRASE = 'Tu Próximo Nivel, Nuestro Compromiso.';

const HeroSection: React.FC = () => {
    return (
        
        <Container fluid className="text-center py-5 hero-section" style={{ 
            backgroundColor: 'var(--color-negro)', 
            minHeight: '60vh', 
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            borderBottom: '1px solid var(--color-azul-electrico)' 
        }}>
            <Row className="w-100">
                <Col>
                    {}
                    <img 
                        src={LOGO_URL} 
                        alt="Level-Up Gaming Logo" 
                        style={{ maxWidth: '250px', height: 'auto' }} 
                        className="mb-4"
                    />
                    
                    {}
                    <h1 className="display-3" style={{ color: 'var(--color-verde-neon)', fontFamily: 'var(--font-encabezado)' }}>
                        {HERO_PHRASE}
                    </h1>
                    
                    <p className="lead text-white mt-3">
                        Encuentra el hardware y los juegos que te llevarán a la victoria.
                    </p>
                </Col>
            </Row>
        </Container>
    );
};

export default HeroSection;