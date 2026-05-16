import {useState} from "react";

const AddBook=()=>{

    let [d,setD]=useState({isbn:0,title:"",author:"",publicationYear:0})

    const handleD=(e)=>{
           
        setD({...d,[e.target.name]:e.target.value})

    }

    const addBook=()=>{

        fetch("http://localhost:8080/book/addBook",
            {method:"POST",
             headers:{"Content-Type":"application/json"},
             body:JSON.stringify(d),
            })
            .then((res)=>res.json())
            .then((res)=>{
                if(res.data){
                alert("Book added to library");
                setD({isbn:0,title:"",author:"",publicationYear:0});
                }
                else{
                    alert("Unable to add book");
                }
            })
            .catch((err)=>console.log(err))
    }




    return (<>

   <h2>ADD A NEW BOOK TO LIBRARY</h2>
    <label>ISBN :</label>
    <input type="number" name="isbn" value={d.isbn} onChange={handleD}/><br/>
    <label>BOOK TITLE:</label>
    <input type="text" name="title" value={d.title} onChange={handleD}/><br/>
    <label>AUTHOR :</label>
    <input type="text" name="author" value={d.author} onChange={handleD}/><br/>
    <label>PUBLICATION YEAR :</label>
    <input type="number" name="publicationYear" value={d.publicationYear} onChange={handleD}/><br/>
    <button onClick={addBook}>ADD BOOK</button>

    
    </>)



}

export default AddBook;