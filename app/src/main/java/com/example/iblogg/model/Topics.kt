package com.example.iblogg.model

class Topics {
    var imageUrl:String=""
    var topic: String=""
    var discuss: String=""
    var id: String=""
    constructor(imageUrl: String,
                topic: String,
                discuss: String,
                id: String){
        this.imageUrl=imageUrl
        this.topic=topic
        this.discuss=discuss
        this.id=id
    }
    constructor()
    constructor(s: String, s1: String, s2: String)

}