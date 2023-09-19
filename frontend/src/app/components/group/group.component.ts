import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Group, GroupsService } from 'src/app/services/groups.service';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent {
  groupService!: GroupsService | null;
  group!: Group;

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.groupService = new GroupsService(this.http);
    const id:number = parseInt(this.route.snapshot.paramMap.get('id')!);
    this.groupService.getGroup(id).subscribe(data => {
      this.group = data;
    })
  }

}
