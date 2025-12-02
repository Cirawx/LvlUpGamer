




export const ORDER_STATUS_OPTIONS = ['Pendiente', 'Procesando', 'Enviado', 'Entregado', 'Cancelado'];


export const REWARD_TYPES = ['Producto', 'Descuento', 'Envio'];


export const REWARD_SEASONS = ['Standard', 'Halloween', 'Navidad', 'BlackFriday', 'Verano'];


export const PRODUCT_CATEGORIES = ['Gaming', 'Accesorios', 'Periféricos', 'Software', 'Otro'];


export const USER_ROLES = ['admin', 'seller', 'customer'];


export const MAX_PRODUCT_STOCK = 999;
export const MAX_PRODUCT_PRICE_CLP = 9999999;


export const ERROR_MESSAGES = {
    NETWORK_ERROR: 'Error de conexión. Por favor, intenta más tarde.',
    VALIDATION_ERROR: 'Por favor, verifica los datos ingresados.',
    UNAUTHORIZED: 'No tienes permisos para realizar esta acción.',
    NOT_FOUND: 'El recurso solicitado no fue encontrado.',
    SERVER_ERROR: 'Error del servidor. Por favor, intenta más tarde.',
};

export default {
    ORDER_STATUS_OPTIONS,
    REWARD_TYPES,
    REWARD_SEASONS,
    PRODUCT_CATEGORIES,
    USER_ROLES,
    MAX_PRODUCT_STOCK,
    MAX_PRODUCT_PRICE_CLP,
    ERROR_MESSAGES,
};
