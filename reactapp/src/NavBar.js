
import {Link,useNavigate} from "react-router-dom";

const NavBar=()=>{
    let nav=useNavigate()

    return (<div>
         <h2>ADMIN PAGE</h2>
         <navbar>
         <Link to="/home"><h5>Home</h5></Link>
         <Link to="/displayAllBooks"><h5>View Library</h5></Link>
        <Link to="/addBook" ><h5>Add Book</h5></Link>
        <Link to="/updateBook"><h5>UpdateBook</h5></Link>
        <Link to="/delete"><h5>Delete Book</h5></Link>
        <Link to="/signup"><h5>Sign Up</h5></Link>
        <button onClick={()=>{localStorage.clear();
                               alert("Logging out...")
                                nav("/");
                                 }}>Logout</button>
         </navbar>
        
       
       
    </div>)



}
export default NavBar;