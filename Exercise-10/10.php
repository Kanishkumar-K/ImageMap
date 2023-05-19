<?php
        session_start();
        $name = $_POST['name'];
        $email = $_POST['email'];
        $gender =$_POST['gender'];
        $mobile = $_POST['mobile'];
        
        $Err="";
        $err=0;

        if (empty($name) || empty($email)  || empty($mobile)) 
        {
            echo("<SCRIPT LANGUAGE='JavaScript'>
            window.alert(' * fields must be filled !');
            window.location.href='10.html';
            </SCRIPT>");
        } 
        else 
        {
            if (!preg_match("/^[a-zA-Z ]$/",$name)) 
            {
                $Err.= "<br> * Invalid Name .Should contain only letters and spaces";
                $err=1;
            }

            if (!filter_var($email, FILTER_VALIDATE_EMAIL)) 
            {
                $Err.= "<br> * Invalid email format";
                $err=1;
            }

            if(!preg_match("/^[0-9]{10}$/",$mobile))
            {
                $Err.="<br> * Invalid mobile";
                $err=1;
            }
        }
        if($err=1)
        {
            echo "<h2>Error in Form inputs</h2>".$Err;
            echo "<br><br><a href='10.html'>Re fill Form</a>";
        }
        else{
        
            echo "<h2>Form Submission Successful</h2>
                <h4>Your Input</h4>";
            echo "Name:".$name."<br>Email:".$email."<br>Mobile:".$mobile."<br>Gender:".$gender;

            $con = mysqli_connect("localhost:3306","root","","users");
        
            
            if($con->connect_error)
            {
                echo "$con->connect_error";
                die("Connection Failed :".$con->connect_error);
            }else
            {

                $s = "insert into user(name,email,mobile,gender) values ('$name','$email','$mobile','$gender')";
                
                $result = mysqli_query($con,$s);
                if($result)
                    echo "<br><h4>Form data stored in the database successfully !!<h4>";
                else
                    echo"<h4>Error storing data in the database</h4>";
            }
        }



?>
