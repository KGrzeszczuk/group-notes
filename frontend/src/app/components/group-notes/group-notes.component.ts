import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, Input, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Group } from 'src/app/services/groups.service';

@Component({
  selector: 'app-group-notes',
  templateUrl: './group-notes.component.html',
  styleUrls: ['./group-notes.component.css']
})
export class GroupNotesComponent {
  @Input() group!: Group;

  private breakpointObserver = inject(BreakpointObserver);

  columnNumber$: Observable<number> = this.breakpointObserver.observe([Breakpoints.Medium, Breakpoints.Small, Breakpoints.XSmall])
    .pipe(
      map(value => {
        if (value.breakpoints[Breakpoints.Small] || value.breakpoints[Breakpoints.XSmall])
          return 1;
        if (value.breakpoints[Breakpoints.Medium])
          return 2
        return 3;
      }),
      shareReplay()
    );
}
