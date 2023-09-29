
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { ErrorHandler, Injectable, NgZone } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable()
export class GlobalErrorHandlerInterceptor implements ErrorHandler {

  constructor(private _snackBar: MatSnackBar, private zone: NgZone) { }

  handleError(error: any) {
    if (!(error instanceof HttpErrorResponse)) {
      error = error.rejection; // get the error object
    }
    if (error instanceof HttpErrorResponse && error.status == HttpStatusCode.Unauthorized) {
      console.log("Nieautoryzowany dostęp");

      this.openSnackBar();

    }
    
    console.error('Error from global error handler', error);
  }

  openSnackBar() {
    this.zone.run(() => {
      this._snackBar.open('Brak autoryzaci do tego zasobu przekierowanie', 'Ok', {
        horizontalPosition: 'right',
        verticalPosition: 'top',
        duration: 60 * 1000
      });
    })
  }

}
