


import CHILEAN_REGIONS_DATA from '../data/chile_regions.json';


interface Provincia { provincia: string; comunas: string[] }
interface ChileanRegion { region: string; provincias: Provincia[]; numero_romano: string; }


export const ALL_REGIONS_DATA: ChileanRegion[] = CHILEAN_REGIONS_DATA as ChileanRegion[];


export const getCommunesByRegionName = (regionName: string): string[] => {
    const regionData = ALL_REGIONS_DATA.find(r => r.region === regionName);
    
    if (!regionData) return [];
    
    
    return regionData.provincias.flatMap(p => p.comunas);
};