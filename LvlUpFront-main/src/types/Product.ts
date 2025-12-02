


export interface Review {
    id: string;
    name: string;
    rating: number; 
    comment: string;
    createdAt: string;
}


export interface Product {
    id: string;
    name: string;
    description: string;
    price: number; 
    imageUrl: string;
    rating: number;
    numReviews: number;
    isTopSelling: boolean;
    countInStock: number;

    
    specifications: string; 
    category: string;
    reviews: Review[]; 
    active: boolean; 

    
    discountType?: string;
    discountValue?: number;
}


export interface ProductPayload {
    name?: string;
    description?: string;
    price?: number;
    imageUrl?: string;
    specifications?: string;
    category?: string;
    countInStock?: number;
    isTopSelling?: boolean;
    rating?: number;
    numReviews?: number;
    active?: boolean; 
}


export interface StatusMessage {
    msg: string;
    type: 'success' | 'danger';
}