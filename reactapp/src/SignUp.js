import {useNavigate} from "react-router-dom";
import {useState} from "react";

const SignUp=()=>{
    let [d,setD]=useState({name:"",email:"",password:""});
    let nav=useNavigate()
    


    const createAccount=()=>{

        fetch("http://localhost:8080/auth/register",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(d)
        })
        
        .then((res)=>res.json())
        .then((res)=>{
            if(res.data){
                alert("Account created");
                nav("/login")
            }
            else{
                alert("Failed to create account ! Email already exists")
            }
        })
        .catch((err)=>{
            alert("Error while signup");
            console.log("Error while signup");});

    }

    const handleD=(e)=>{
       setD({...d,[e.target.name]:e.target.value})
    }

    return(<div className="signup">

    <input type="text" name="name" placeholder="Enter username" onChange={handleD}/>
    
    <input type="text" name="email" placeholder="Enter Email Address" onChange={handleD}/>
    
    <input type="text" name="password" placeholder="Enter Password" onChange={handleD}/>

    <button onClick={createAccount}>SignUp</button>


    
    </div>)
}
export default SignUp;
