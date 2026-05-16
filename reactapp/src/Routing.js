import {Routes,Route} from "react-router-dom";
import AddBook from "./AddBook";
import Home from "./Home";
import UpdateBook from "./UpdateBook";
import Login from "./Login";
import SignUp from "./SignUp";
import DeleteBook from "./DeleteBook";
import DisplayBooks from "./DisplayBooks";

const Routing=()=>{

  return(<>
   <Routes>
    <Route path="/"  element={<Login/>}/>
    <Route path="/addBook" element={<AddBook/>} />
    <Route path="/updateBook" element={<UpdateBook/>} />
    <Route path="/home" element={<Home/>}/>
    <Route path="/signup" element={<SignUp/>}/>
    <Route path="/delete" element={<DeleteBook/>}/>
    <Route path="/displayAllBooks" element={<DisplayBooks/>}/>
  
   </Routes>

  </>)
}

export default Routing;