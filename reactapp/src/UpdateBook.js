import {useState} from "react";
const UpdateBook=()=>{

    let [d,setD]=useState({isbn:0,title:"",author:"",publicationYear:0})

    const handleD=(e)=>{
           
        setD({...d,[e.target.name]:e.target.value})

    }

    const updateBook=()=>{


        fetch("http://localhost:8080/book/updateBookByIsbn",
            {method:"PUT",
             headers:{"Content-Type":"application/json"},
             body:JSON.stringify(d),
            })
            .then((res)=>res.text())
            .then((res)=>{
                alert("Book Updated")
                setD({isbn:0,title:"",author:"",publicationYear:0})
            })
            .catch((err)=>console.log(err))
    }




    return (<>

   <h2>UPDATE BOOK</h2>
    <label>ISBN :</label>
    <input type="number" name="isbn" value={d.isbn} onChange={handleD}/><br/>
    <label>BOOK TITLE:</label>
    <input type="text" name="title" value={d.title} onChange={handleD}/><br/>
    <label>AUTHOR :</label>
    <input type="text" name="author" value={d.author} onChange={handleD}/><br/>
    <label>PUBLICATION YEAR :</label>
    <input type="number" name="publicationYear" value={d.publicationYear} onChange={handleD}/><br/>
    <button onClick={updateBook}>UPDATE</button>

    
    </>)



}

export default UpdateBook;