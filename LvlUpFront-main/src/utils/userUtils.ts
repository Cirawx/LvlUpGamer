

import CHILEAN_REGIONS_DATA from '../data/chile_regions.json';


export const CHILEAN_REGIONS: string[] = CHILEAN_REGIONS_DATA.map(region => region.region);


export const getCommunesByRegionName = (regionName: string): string[] => {
    const region = CHILEAN_REGIONS_DATA.find(r => r.region === regionName);
    if (!region) {
        return [];
    }
    
    return region.provincias.flatMap(provincia => provincia.comunas);
};


export const validateRut = (rut: string): boolean => {
    if (!rut || typeof rut !== 'string') {
        return false;
    }

    
    const cleanedRut = rut.replace(/\./g, '').replace('-', '');

    
    if (!/^\d{7,8}[0-9Kk]$/.test(cleanedRut)) {
        return false;
    }

    const body = cleanedRut.slice(0, -1);
    const dv = cleanedRut.slice(-1).toUpperCase();

    
    let sum = 0;
    let multiplier = 2;

    for (let i = body.length - 1; i >= 0; i--) {
        sum += parseInt(body[i], 10) * multiplier;
        multiplier++;
        if (multiplier > 7) {
            multiplier = 2;
        }
    }

    const calculatedDv = 11 - (sum % 11);
    let expectedDv: string;

    if (calculatedDv === 11) {
        expectedDv = '0';
    } else if (calculatedDv === 10) {
        expectedDv = 'K';
    } else {
        expectedDv = calculatedDv.toString();
    }

    return expectedDv === dv;
};
