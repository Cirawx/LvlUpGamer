

export type OrderStatus = 'Pendiente' | 'Procesando' | 'Enviado' | 'Entregado' | 'Cancelado';

export interface OrderItem {
    id?: number;
    productId?: string;
    name: string;
    price: number;
    qty: number;
    quantity?: number; 
    image?: string;
}

export interface ShippingAddress {
    street: string;
    city: string;
    region: string;
}

export interface Order {
    id: string;
    userId: string;
    userRut?: string; 
    items: OrderItem[];
    shippingAddress: ShippingAddress;
    paymentMethod: string;          
    totalPrice: number;
    shippingPrice: number;          
    isPaid: boolean;                
    status: OrderStatus;
    createdAt: string;
    paidAt?: string;                
    deliveredAt?: string;           
}

export interface OrderUpdatePayload {
    status: OrderStatus;
}
