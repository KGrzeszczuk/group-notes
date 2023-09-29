import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { onlyLoggedInUsersGuardGuard } from './only-logged-in-users-guard.guard';

describe('onlyLoggedInUsersGuardGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => onlyLoggedInUsersGuardGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
