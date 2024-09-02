package com.example.iblogg.model

class User {
    var firstName: String = ""
    var secondName: String = ""
    var email: String = ""
    var userName: String = ""
    var password: String = ""
    var userid: String=""

    constructor(firstName: String,secondName: String,
                email: String,userName: String,password: String,userid: String){
        this.firstName=firstName
        this.secondName=secondName
        this.email = email
        this.userName = userName
        this.password=password
        this.userid=userid
    }
    constructor()
}