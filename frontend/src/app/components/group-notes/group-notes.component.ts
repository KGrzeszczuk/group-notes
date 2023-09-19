import { Component, Input } from '@angular/core';
import { Group } from 'src/app/services/groups.service';

@Component({
  selector: 'app-group-notes',
  templateUrl: './group-notes.component.html',
  styleUrls: ['./group-notes.component.css']
})
export class GroupNotesComponent {
  @Input() group!: Group;
}
