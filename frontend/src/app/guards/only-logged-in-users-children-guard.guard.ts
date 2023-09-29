import { CanActivateChildFn } from '@angular/router';

export const onlyLoggedInUsersChildrenGuardGuard: CanActivateChildFn = (childRoute, state) => {
  return true;
};
