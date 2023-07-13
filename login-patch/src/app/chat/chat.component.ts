import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  senderId: number = 0;
  messages: any[] = [];
  message: string = "";

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.fetchUserList();
  }

  fetchUserList() {

    const senderId = localStorage.getItem("userId") as string;
    const receiverId = localStorage.getItem("receiverId") as string;
    
    let params = new HttpParams();
    params = params.set('sender', Number.parseInt(senderId));
    params = params.set('receiver', Number.parseInt(receiverId));

    this.senderId = Number.parseInt(senderId);

    console.log(senderId + " " + receiverId)

    this.http.get<any[]>('http://localhost:8080/message/all', { params }).subscribe(
      (response) => {
        this.messages = response;
      },
      (error) => {
        console.log(error);
      }
    );

    params = new HttpParams();
    params = params.set('sender', Number.parseInt(receiverId));
    params = params.set('receiver', Number.parseInt(senderId));

    this.http.get<any[]>('http://localhost:8080/message/all', { params }).subscribe(
      (response) => {
        this.messages = this.messages.concat(response);
        this.messages = this.messages.sort((a, b) => b.id - a.id);


      },
      (error) => {
        console.log(error);
      }
    );
  }

  sendMessage() {

    console.log("text is" + this.message)

    if(this.message.trim() == "") {
      return;
    }

    const senderId = localStorage.getItem("userId") as string;
    const receiverId = localStorage.getItem("receiverId") as string;

    const sender = Number.parseInt(senderId);
    const receiver = Number.parseInt(receiverId);

    const payload = {
      sender: sender,
      receiver: receiver,
      message: this.message
    };

    this.http.post("http://localhost:8080/message/send", payload);
    
  }

}
