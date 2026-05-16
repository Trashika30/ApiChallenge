import {useState} from "react";
import {Link} from "react-router-dom";
import {useNavigate} from "react-router-dom";

const Login=()=>{

    let[email,setEmail]=useState("")
    let[pwd,setPwd]=useState("")
   
    let nav=useNavigate()
    const signIn=()=>{

        fetch(`http://localhost:8080/auth/login/${email}/${pwd}`,{
            method:"POST",
            headers:{"Content-Type":"application/json"}
           
        })
       .then((res)=>res.json())
       .then((res)=>{
         console.log(res);
         if(res.data){
            alert("Login Success")
            localStorage.setItem("token",res.data);
            nav("/home")
         }
         else{
            alert("Login failed");
         }
       })
       .catch((err)=>{
        alert("Failed while login")
        console.log(err)})
    }




    return(<div className="login">
    
      <input type="text" placeholder="Enter email address" onChange={(e)=>setEmail(e.target.value)}/>
      <input type="text" placeholder="Enter your password" onChange={(e)=>setPwd(e.target.value)}/>
      <button onClick={signIn}>SignIn</button>
      <Link to="/signup"><p>Don't have an account ? Create account</p></Link>
    </div>)

}
export default Login;