import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { AppSessionService } from '../services/app-session.service';

export const onlyLoggedInUsersGuardGuard: CanActivateFn = (route, state) => {
  const app = inject(AppSessionService);
  console.log(app);
  return true;
};
