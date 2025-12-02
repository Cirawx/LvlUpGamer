

import React, { createContext, useContext, useState, useEffect, ReactNode, useRef } from 'react';
import { Product } from '../types/Product';
import { useAuth } from './AuthContext';


export interface CartItem {
    product: Product;
    quantity: number;
    isRedeemed?: boolean; 
    pointsCost?: number;   
}


interface CartContextType {
    cartItems: CartItem[];
    cartCount: number;
    totalPrice: number;
    addToCart: (product: Product, quantity?: number, isRedeemed?: boolean, pointsCost?: number) => void;
    removeFromCart: (productId: string) => void;
    increaseQuantity: (productId: string) => void;
    decreaseQuantity: (productId: string) => void;
    clearCart: () => void;
}


const CartContext = createContext<CartContextType | undefined>(undefined);


interface CartProviderProps {
    children: ReactNode;
}

export const CartProvider: React.FC<CartProviderProps> = ({ children }) => {
    const { user } = useAuth();
    const cartKey = user ? `cart_${user.id}` : 'cart_guest';


    
    const [cartItems, setCartItems] = useState<CartItem[]>([]);

    
    const prevKeyRef = useRef(cartKey);

    
    useEffect(() => {
        const loadCart = () => {
            const storedUserCart = localStorage.getItem(cartKey);
            const userCartItems: CartItem[] = storedUserCart ? JSON.parse(storedUserCart) : [];

            
            if (user && prevKeyRef.current === 'cart_guest') {
                const storedGuestCart = localStorage.getItem('cart_guest');
                if (storedGuestCart) {
                    const guestCartItems: CartItem[] = JSON.parse(storedGuestCart);

                    if (guestCartItems.length > 0) {
                        
                        
                        guestCartItems.forEach(guestItem => {
                            const existingItemIndex = userCartItems.findIndex(ui => ui.product.id === guestItem.product.id);

                            if (existingItemIndex !== -1) {
                                
                                const currentQty = userCartItems[existingItemIndex].quantity;
                                const newQty = Math.min(currentQty + guestItem.quantity, guestItem.product.countInStock);
                                userCartItems[existingItemIndex].quantity = newQty;
                            } else {
                                
                                userCartItems.push(guestItem);
                            }
                        });

                        
                        localStorage.removeItem('cart_guest');
                    }
                }
            }

            setCartItems(userCartItems);
        };

        loadCart();
    }, [cartKey, user]);

    
    useEffect(() => {
        
        if (prevKeyRef.current !== cartKey) {
            prevKeyRef.current = cartKey;
            return;
        }
        localStorage.setItem(cartKey, JSON.stringify(cartItems));
    }, [cartItems, cartKey]);


    
    const cartCount = cartItems.reduce((acc, item) => acc + item.quantity, 0);
    
    const totalPrice = cartItems.reduce((acc, item) => acc + item.product.price * item.quantity, 0);

    

    const addToCart = (product: Product, quantity = 1, isRedeemed = false, pointsCost = 0) => {
        setCartItems(prevItems => {
            const exists = prevItems.find(item => item.product.id === product.id);

            if (exists) {
                
                return prevItems.map(item =>
                    item.product.id === product.id
                        ? { ...item, quantity: Math.min(item.quantity + quantity, item.product.countInStock) }
                        : item
                );
            } else {
                
                if (product.countInStock > 0 || isRedeemed) { 
                    return [...prevItems, {
                        product,
                        quantity: Math.min(quantity, product.countInStock || 1), 
                        isRedeemed,
                        pointsCost
                    }];
                }
                return prevItems; 
            }
        });
    };

    const removeFromCart = (productId: string) => {
        setCartItems(prevItems => prevItems.filter(item => item.product.id !== productId));
    };

    const increaseQuantity = (productId: string) => {
        setCartItems(prevItems =>
            prevItems.map(item =>
                item.product.id === productId
                    ? { ...item, quantity: Math.min(item.quantity + 1, item.product.countInStock) }
                    : item
            )
        );
    };

    const decreaseQuantity = (productId: string) => {
        setCartItems(prevItems =>
            prevItems
                .map(item =>
                    item.product.id === productId ? { ...item, quantity: item.quantity - 1 } : item
                )
                .filter(item => item.quantity > 0) 
        );
    };

    const clearCart = () => {
        setCartItems([]);
    };


    const value = {
        cartItems,
        cartCount,
        totalPrice,
        addToCart,
        removeFromCart,
        increaseQuantity,
        decreaseQuantity,
        clearCart,
    };

    return <CartContext.Provider value={value}>{children}</CartContext.Provider>;
};


export const useCart = () => {
    const context = useContext(CartContext);
    if (context === undefined) {
        throw new Error('useCart debe ser usado dentro de un CartProvider');
    }
    return context;
};