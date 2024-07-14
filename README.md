# library-management-system-java

Developed a system which will produce results for

addBook(Book book): 

Endpoint available at : POST http://localhost:8080/manage/add 
Payload : {
    "title":"Roman Numerals 101",
    "author":"G.G.Gambhir",
    "isbn" : "262",
    "genre":"Study",
    "yearOfPublication":"2004",
    "departmentName" : "Mathematics"
}

removeBook(String ISBN) : 
Endpoint available at : DELETE http://localhost:8080/manage/deleteByIsbn/201
Payload : {pathVariable} /201 for eg.


findBookByTitle(String title): 
Endpoint available at : GET http://localhost:8080/find-book/title/Roman Numerals 101
Payload : {pathVariable} /Roman Numerals 101 for eg.

findBookByAuthor(String author): 
Endpoint available at : GET http://localhost:8080/find-book/author/G.G.Gambhir
Payload : {pathVariable} /G.G.Gambhir for eg.

listAllBooks(): 
Endpoint available at : GET http://localhost:8080/find-book/findAll
No payload required

listAvailableBooks(): 
Endpoint available at : GET http://localhost:8080/find-book/find-available-books
No Payload required

DB is available at : http://localhost:8080/h2-console
