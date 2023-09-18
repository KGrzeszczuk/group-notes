import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExamplePageComponent } from './components/example-page/example-page.component';
import { LoginComponent } from './components/login/login.component';
import { MainPageNavComponent } from './components/main-page-nav/main-page-nav.component';

const routes: Routes = [
  {
    path: 'nav', component: MainPageNavComponent,
    children: [
      { path: 'example', component: ExamplePageComponent },
      { path: '', redirectTo: '/nav/example', pathMatch: 'full' },
    ],
  },
  { path: '', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
