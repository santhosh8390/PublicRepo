import { NgModule } from '@angular/core';
import { PagesRoutingModule } from './pages-routing.module';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { AppComponent } from '../app.component';


//  Declare all the components in this module
@NgModule({
  declarations: [AppComponent],
  providers: [],
  imports: [
    PagesRoutingModule,
    HeaderComponent,
    FooterComponent,
    HomeComponent
  ],
})
export class PagesModule {}
