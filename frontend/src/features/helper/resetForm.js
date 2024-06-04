import { useCallback } from 'react';

const useResetForm = (setters) => {

  return useCallback(() => {
    setters.forEach(setter => setter(''));
  }, [setters]);
}

export default useResetForm;
