






export interface Level {
  id: number;
  name: string;
  minPoints: number;
  benefits: string[];
}


export interface Event {
  id: number;
  name: string;
  date: string; 
  location: string;
  mapEmbed: string; 
}


export interface UserPoints {
    currentPoints: number;
    levelName: string; 
    nextLevelPoints: number;
}


export interface Reward {
    id: number;
    type: 'Producto' | 'Descuento';
    name: string;
    pointsCost: number;
    description: string;
}


export interface NewsItem {
  id: number;
  title: string;
  summary: string;
  link: string;
}


export interface VideoItem { 
    id: number; 
    title: string; 
    embedUrl: string; 
}







export const mockLevels: Level[] = [
  { id: 1, name: 'Bronce', minPoints: 0, benefits: [''] },
  { id: 2, name: 'Plata', minPoints: 5000, benefits: ['Descuento del 5%'] },
  { id: 3, name: 'Oro', minPoints: 20000, benefits: ['Descuento del 10% en pedidos.', 'Soporte prioritario.'] },
  { id: 4, name: 'Diamante', minPoints: 50000, benefits: ['Descuento del 15% en pedidos.', 'Regalo de cumpleaños.', 'Acceso a eventos VIP.'] },
];

export const mockEvents: Event[] = [
  { id: 1, name: 'Torneo de eSports Level-Up', date: '10 de Noviembre de 2025', location: 'Santiago, Chile', mapEmbed: 'http://googleusercontent.com/maps.google.com/3' },
  { id: 2, name: 'Feria de Tecnología Gaming', date: '5-7 de Diciembre de 2025', location: 'Estadio O’Higgins, Concepción', mapEmbed: 'http://googleusercontent.com/maps.google.com/4' },
];


export const mockUserPoints: UserPoints = {
    currentPoints: 1250, 
    levelName: 'Plata (Nivel 2)',
    nextLevelPoints: 1500,
};

export const mockRewards: Reward[] = [
    { id: 101, type: 'Producto', name: 'Taza Gamer Edición Limitada', pointsCost: 2800, description: 'Canjea tus puntos por una taza exclusiva de Level-Up.' },
    { id: 102, type: 'Descuento', name: 'Cupón de $5.000 CLP', pointsCost: 6000, description: 'Descuento aplicable a tu próxima compra.' },
    { id: 103, type: 'Producto', name: 'Mousepad RGB Extendido', pointsCost: 18000, description: 'Mousepad amplio con iluminación RGB.' },
    
    { id: 104, type: 'Descuento', name: 'Envío Express Gratuito', pointsCost: 3500, description: 'Cubre el costo de tu envío express (Valor: $5.000 CLP).' },
    { id: 105, type: 'Producto', name: 'Polera Gamer Level-Up', pointsCost: 15000, description: 'Polera con diseño del logo de la tienda.' },
    { id: 106, type: 'Descuento', name: 'Cupón de 15% OFF', pointsCost: 35000, description: 'Descuento del 15% para una compra grande.' },
];


export const mockGamingNews: NewsItem[] = [
  { id: 1, title: 'Anuncio de GTA VI: Primer Tráiler', summary: 'Rockstar Games reveló el primer vistazo oficial a la secuela más esperada.', link: '#' },
  { id: 2, title: 'PS5 Pro: Rumores de Lanzamiento en 2024', summary: 'Las filtraciones apuntan a una consola más potente para fin de año.', link: '#' },
  { id: 3, title: 'El Esports crece un 20%', summary: 'Reporte anual sobre el impacto económico del juego competitivo.', link: '#' },
];


export const mockGamingVideos: VideoItem[] = [
    
    { id: 1, title: 'Cómo limpiar tu PS5 a fondo', embedUrl: 'https://www.youtube.com/embed/gW2D_h-t_gQ' }, 
    { id: 2, title: 'Montaje de PC: Guía paso a paso', embedUrl: 'https://www.youtube.com/embed/j_8jC8D1z7c' },
    { id: 3, title: 'Review: Los mejores auriculares gaming 2025', embedUrl: 'https://www.youtube.com/embed/5H9Hq8Gq_qQ' },
];