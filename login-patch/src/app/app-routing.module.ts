import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { HomeComponent } from './home/home.component';
import { ChatComponent } from './chat/chat.component';


const routes: Routes = [
  {
    path: "",
    component: LoginComponent
  },
  {
    path: "sign-up",
    component: SignUpComponent
  },
  {
    path: "home",
    component: HomeComponent,
    pathMatch: "full"
  },
  {
    path: "chat",
    component: ChatComponent,
    pathMatch: "full"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
