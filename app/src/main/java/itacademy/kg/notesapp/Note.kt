package itacademy.kg.notesapp

class Note {

    var nameOfNote = ""
    var descriptions = ""
    var date = 0

    constructor(nameOfNote: String, descriptions: String, date: Int) {
        this.nameOfNote = nameOfNote
        this.descriptions = descriptions
        this.date = date
    }

}