class Author {
    namee = '';
    surname = '';

    constructor(namee, surname) {
        this.namee = namee;
        this.surname = surname;
    }
    getName(){ return this.namee; }
    getSurname(){ return this.surname; }

    toString (){
        return this.getName() + ' ' + this.getSurname();
    }
}

class Book {

    constructor(title, authors, yearPubb, publisher) {
        this._title = title;
        this._authors = authors;
        this._yearPubb = yearPubb;
        this._publisher = publisher;
    }


    get title() {
        return this._title;
    }

    set title(value) {
        this._title = value;
    }

    get authors() {
        return this._authors;
    }

    set authors(value) {
        this._authors = value;
    }

    get yearPubb() {
        return this._yearPubb;
    }

    set yearPubb(value) {
        if (value > 0){
            this._yearPubb = value;
        }
        else {
            console.log("Bro, pubblication year must be positive!!");
        }
    }

    get publisher() {
        return this._publisher;
    }

    set publisher(value) {
        this._publisher = value;
    }

    toString (){
        return "{" +
            "title: " + this.title +
            ", authors: [" + this.authors + ']' +
            ", pubblication year: " + this.yearPubb +
            ", publisher: " + this.publisher +
            "}";
    }
}

