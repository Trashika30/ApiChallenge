import {useState,useEffect} from "react";
import Books2 from "./Books2";
import Loading from "./Loading";
const DisplayBooks=()=>{
    let [search,setSearch]=useState("");
    let[books,setBooks]=useState([]);
    let [filteredBooks,setFilteredBooks]=useState([]);
    let[isbn,setIsbn]=useState(0);

    useEffect(()=>{
        fetch("http://localhost:8080/book/showAll")
        .then((res)=>res.json())
        .then((res)=>{
           
            if(res.data){
               console.log("Books found");
               setBooks(res.data);

            }
            else{
                alert("No books to display")
            }
        })
        .catch((err)=>console.log("Error while fetching"));

    },[])
     
    const displayByIsbn=(isbn)=>{
        
        fetch(`http://localhost:8080/book/showBookByIsbn/${isbn}`)
        .then((res)=>res.json())
        .then((res)=>{
            if(res.data){
                setFilteredBooks([res.data])
            }
            else{
                alert("No book found with the given isbn")
            }
        })
        .catch((err)=>console.log("Error while fetching"));
    }
 
    const filterBooks=(e)=>{

        setSearch(e.target.value);
         setFilteredBooks(books.filter((obj)=>
         obj.title.toLowerCase().includes(search.toLowerCase()) || 
         obj.author.toLowerCase().includes(search.toLowerCase())
         ))

    }
    
    
       
    return (<>
    
    <h2>List of Books in Library</h2>

    <input type="number" placeholder="isbn" onChange={(e)=>setIsbn(e.target.value)}/>

    <input type="text" placeholder="Search by title or author" value={search} onChange={filterBooks}/>
    
    {  
        search.length || isbn > 0?
        filteredBooks.map((obj)=><Books2 book={obj}/>)
        :
        books.length>0? books.map((obj)=><Books2 book={obj}/>)
        :
        <Loading/>
    }
    
    </>)

}
export default DisplayBooks;