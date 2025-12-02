

import '@testing-library/jest-dom';
import { vi } from 'vitest';




declare global {
  
  
  var global: typeof globalThis;
}
if (typeof global === 'undefined') {
  
  (global as any) = globalThis;
}





class MockFileReader {
  result: string | ArrayBuffer | null = null;
  onloadend: ((this: FileReader, ev: ProgressEvent<FileReader>) => any) | null = null;

  readAsDataURL = vi.fn(function (this: any, _file: Blob) {
    
    setTimeout(() => {
      this.result = 'data:image/png;base64,mocked_base64_data';
      if (this.onloadend) {
        this.onloadend({ target: { result: this.result } } as any);
      }
    }, 50);
  });
}


vi.stubGlobal('FileReader', MockFileReader);





globalThis.URL.createObjectURL = vi.fn(() => 'mocked_local_url');
globalThis.URL.revokeObjectURL = vi.fn();



















