




export const CLP_FORMATTER = new Intl.NumberFormat('es-CL', {
    style: 'currency',
    currency: 'CLP',
    minimumFractionDigits: 0,
});


export const formatClp = (amount: number): string => {
    return CLP_FORMATTER.format(amount);
};


export const formatDate = (date: string | Date): string => {
    const d = typeof date === 'string' ? new Date(date) : date;
    return d.toLocaleDateString('es-CL');
};


export const formatDateTime = (date: string | Date): string => {
    const d = typeof date === 'string' ? new Date(date) : date;
    return d.toLocaleString('es-CL');
};

export default {
    CLP_FORMATTER,
    formatClp,
    formatDate,
    formatDateTime,
};
