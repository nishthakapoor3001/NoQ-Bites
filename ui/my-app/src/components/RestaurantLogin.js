import React, { useState } from "react";
import Navbar from './Navbar';
import Footer from './Footer';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Label, Input, FormGroup, Button, Form, Card, Container, CardHeader, CardBody, Row, Col } from 'reactstrap';
import {  RestaurantUserLogin } from "../services/user_service";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';



const RestaurantLogin = () => {

  const [data, setData]=useState({
    
    name:'',
    password:'',
    type:'RESTAURANT'


  })

//  function handleClickRestaurant(){
//    navigate("/Restaurant")
// }

const navigate = useNavigate();

const [error, setError] = useState({
    errors: {},
    isError: false,
  });


  //handle changes
  const handleChange=(e,prop)=>{

    //setting values dynamically
      setData({...data,[prop]:e.target.value})
  }

  const resetData=()=>{
    setData({
      
      name:'',
      password:'',
      
    })
  }

  const submitForm=(e)=>{
    e.preventDefault()
    console.log(data);
    if (error.isError) {
          toast.error("Form data is invalid");
          return;
        }

    RestaurantUserLogin(data).then((jwtTokenData)=>{
      console.log("Restaurantlogin")
      console.log("jwtTokenData")
      console.log("success")
      if(jwtTokenData.success){
        toast.success(jwtTokenData.message);
      }else
        toast.error(jwtTokenData.message);

      if(jwtTokenData.success) {
//        navigate("/RestaurantAdmin",{state:{name:data.name}});
        navigate(`/RestaurantAdmin?name=${data.name}`);
     }
    }).catch((error)=>{
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
      <Navbar />
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
                  <Label for="name">
                    Restaurant Name
                  </Label>
                  <Input
                    
                    id="name"
                    name="name"
                    placeholder="Your restaurant name"
                    required
                    onChange={(e)=>handleChange(e,'name')}
                    value={data.restaurantName}
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
                    onChange={(e)=>{handleChange(e,'password')}}
                    value={data.password}
                  />
                </FormGroup>
                
                <Button color='dark' className='ms-2'>
                  LogIn
                </Button>
                <Button color='secondary' className='ms-2' onClick={resetData}>
                  Reset
                </Button>
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
    <Footer />
    </div>
  );
};

export default RestaurantLogin;



    