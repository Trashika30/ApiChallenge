import {useState,useEffect} from "react";
import Books from "./Books";

const DeleteBook=()=>{

    let[books,setBooks]=useState([]);

    useEffect(()=>{
        getAllBooks();
    },[])

    const getAllBooks=()=>{

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
    }

    const deleteByIsbn=(isbn)=>{

        fetch(`http://localhost:8080/book/deleteBookByIsbn/${isbn}`,{
            method:"DELETE"
        })
        .then((res)=>res.json())
        .then((res)=>{
            alert(res.message)
            }
          
        )
        .catch((err)=>console.log("Error while fetching"));
    }


    return (<>
   {
    books.map((obj)=><Books book={obj} deleteByIsbn={deleteByIsbn}/>)
   }
    </>)

}
export default DeleteBook;