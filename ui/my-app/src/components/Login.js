import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-toastify/dist/ReactToastify.css';
import { Label, Input, FormGroup, Button, Form, Card, Container, CardHeader, CardBody, Row, Col } from 'reactstrap';
import { LogInUser } from "../services/user_service";
import { useNavigate } from "react-router-dom";
import Navbar from "./Navbar";
import Footer from "./Footer";
import { ToastContainer, toast } from 'react-toastify';




const Login = () => {

  const [data, setData] = useState({

    watId: '',
    password: '',
    type: 'USER'


  })



  const navigate = useNavigate();


  function handleClickSignup() {
    navigate("/Signup")
  }
  const [error, setError] = useState({
    errors: {},
    isError: false,
  });
//    function handleClickLogin(){
//     if (error.isError) {
//       toast.error("user does not exist");
//       return;
//     }
//     else
//      navigate("./Restaurant")
//   }

  //handle changes
  const handleChange = (e, prop) => {

    //setting values dynamically
    setData({ ...data, [prop]: e.target.value })
  }

  const resetData = () => {
    setData({

      watId: '',
      password: '',

    })
  }

  

  const submitForm = (e) => {
    e.preventDefault()
    console.log(data);
    if (error.isError) {
      toast.error("Form data is invalid");
      return;
    }

    LogInUser(data).then((jwtTokenData) => {
      console.log("userlogin")
      console.log("jwtTokenData")
      console.log("success")

      // if(jwtTokenData.success){
      //         toast.success(jwtTokenData.message);
      // }else
      // toast.error(jwtTokenData.message);
      
      if(jwtTokenData.success) {
        toast.success(jwtTokenData.message);
        console.log(localStorage.getItem('token'));
        localStorage.setItem('token', jwtTokenData.token);
        console.log(jwtTokenData.token);
         navigate("/Restaurant");
      }
      else if(!jwtTokenData.success){
        toast.error(jwtTokenData.message);
        setData({
          watId: '',
          password: '',
          type: 'USER'
        });
      }


    }).catch((error) => {
      // console.log(error)
      console.log("fail")
      toast.error(error.message);
      setError({
        errors: error,
        isError: true,})
    });
  };



  return (
    <div>
      <div>
        <Navbar />
      </div>
<br></br>
<br></br>
<br></br>
<br></br>
<br></br>


      <Container>
        <Row className='mt-4'>

          {/* {JSON.stringify(data)} */}

          <Col sm={{ size: 6, offset: 3 }}>
          <ToastContainer />

            <Card color='dark' outline>
              <CardHeader>
                <h3 style={{ align: "center" }}> Fill in your details</h3>
              </CardHeader>
              <CardBody>
                <Form className="text-center" onSubmit={submitForm}>
                  <FormGroup>
                    <Label for="watId">
                      Wat ID
                    </Label>
                    <Input
                      type="number"
                      id="watId"
                      name="watId"
                      placeholder="Your 8-digit WatIAM Id (ex: 21000021)"
                      required
                      minLength="8"
                      maxLength="8"
                      onChange={(e) => handleChange(e, 'watId')}
                      value={data.watId}
                    />
                  </FormGroup>

                  <FormGroup>
                    <Label for="password">
                      Password
                    </Label>
                    <Input
                      id="password"
                      name="password"
                      placeholder="Your Password"
                      type="password"
                      required
                      onChange={(e) => { handleChange(e, 'password') }}
                      // onChange={(event)=>matchPwd(event.target.value)}
                      value={data.password}
                    />
                  </FormGroup>

                  <Button color='dark' className='ms-2' >
                    LogIn
                  </Button>
                  <Button color='secondary' className='ms-2' onClick={resetData}>
                    Reset
                  </Button>
                  <div style={{ marginTop: '2.5%' }}>
                    Are you a new user?
                    <Button color='primary' className='ms-2' onClick={handleClickSignup}>
                      Signup
                    </Button>
                  </div>


                </Form>
              </CardBody>
            </Card>

          </Col>
        </Row>

      </Container>
      <br></br>
      <br></br>
<br></br>
<br></br>
      <div>
        <Footer />
      </div>
    </div>
  );
};

export default Login;



