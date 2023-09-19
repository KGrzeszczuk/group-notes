import { Component, Input } from '@angular/core';
import { Group } from 'src/app/services/groups.service';

@Component({
  selector: 'app-group-details',
  templateUrl: './group-details.component.html',
  styleUrls: ['./group-details.component.css']
})
export class GroupDetailsComponent {
  @Input() group!: Group;
}
