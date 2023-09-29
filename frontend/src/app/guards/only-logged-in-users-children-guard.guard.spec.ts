import { TestBed } from '@angular/core/testing';
import { CanActivateChildFn } from '@angular/router';

import { onlyLoggedInUsersChildrenGuardGuard } from './only-logged-in-users-children-guard.guard';

describe('onlyLoggedInUsersChildrenGuardGuard', () => {
  const executeGuard: CanActivateChildFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => onlyLoggedInUsersChildrenGuardGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
