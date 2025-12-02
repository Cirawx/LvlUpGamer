

export interface Event {
    id: string;
    title: string;
    date: string; 
    time: string; 
    location: string;
    mapEmbed: string;
    notes?: string;
}

export interface EventFormData {
    title?: string;
    date?: string;
    time?: string;
    location?: string;
    mapEmbed?: string;
    notes?: string;
}

export interface StatusMessage {
    msg: string;
    type: 'success' | 'danger';
}
