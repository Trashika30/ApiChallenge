
const Books2=({book})=>{
   return (<div className="card">
    
    <h3>Isbn :{book.isbn}</h3>
    <h3>Title : {book.title}</h3>
    <h3>Author: {book.author}</h3>
    <h3>Year : {book.publicationYear}</h3>
  
   </div>)

}
export default Books2;