import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog'; 
import { Observable } from 'rxjs';
import { AddUserComponent } from '../add-user/add-user.component';
import { User } from 'src/app/Entities/user';
import { UserService } from 'src/app/Services/user.service';
import { EditUserComponent } from '../edit-user/edit-user.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: User[];

  constructor(private matDialog: MatDialog, private userService: UserService) { }

  ngOnInit(): void {
    this.refreshList(); 
  }
  refreshList(): void {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
  }

  addUser(): void {
    const addDialog = this.matDialog.open(AddUserComponent, {});
  }

  deleteUser(id: number){
    this.userService.delete(id);
    this.refreshList();
  }

  updateUser(id: number, user: User) {
    console.log(user.id);
    const updateDialog = this.matDialog.open(EditUserComponent);
    this.userService.update(user.id, user);
  }

}
