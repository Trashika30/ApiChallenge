
const Books=({book,deleteByIsbn})=>{
   return (<div>
    
    <h3>Isbn :{book.isbn}</h3>
    <h3>Title : {book.title}</h3>
    <h3>Author: {book.author}</h3>
    <h3>Year : {book.publicationYear}</h3>
    <button onClick={()=>{deleteByIsbn(Number(book.isbn))}}>Remove</button>
   </div>)

}
export default Books;