import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';

//TODO przerzucić wszystkie materailComponetns
const MaterialComponents = [
  MatButtonModule,
  MatToolbarModule
]

@NgModule({
  imports: [MaterialComponents],
  exports: [MaterialComponents]
})
export class MaterialModule { }
