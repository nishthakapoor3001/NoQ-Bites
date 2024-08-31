import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-toastify/dist/ReactToastify.css';
import Navbar from "./Navbar";
import { useNavigate } from "react-router-dom";
import { Label, Input, FormGroup, Button, Form, Card, Container, CardHeader, CardBody, Row, Col } from 'reactstrap';
import { signUp } from "../services/user_service";
import { ToastContainer, toast } from 'react-toastify';

import Footer from "./Footer";

function Signup() {
  const navigate = useNavigate();

  const [data, setData] = useState({

    name: '',
    email: '',
    password: '',
    watid: '',
  })

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  //handle changes
  const handleChange = (e, prop) => {

    //setting values dynamically
    setData({ ...data, [prop]: e.target.value })
  }

  const resetData = () => {
    setData({

      name: '',
      email: '',
      password: '',
      watid: '',
    })
  }


  const submitForm = (e) => {
    e.preventDefault();

    if (error.isError) {
      toast.error("Form data is invalid");
      return;
    }

    signUp(data).then((resp) => {
      console.log("success")
      // alert(resp.message);
      toast.success(resp.message);
      setData({
        name: '',
        email: '',
        password: '',
        watid: '',
      });
      navigate("/Login");
    })
    .catch((error) => {
      console.log("Error: ", error)
      toast.error(error.message);
      setError({
        errors: error,
        isError: true,
      })
    });
  };



  return (
    <div>
      <div>
        <Navbar />
      </div>


      <Container>
        <Row className='mt-4'>

          <Col sm={{ size: 6, offset: 3 }}>
          <ToastContainer />
            <Card color='dark' outline>
              <CardHeader>
                <h3 style={{ align: "center" }}> Fill in your details</h3>
              </CardHeader>
              <CardBody>
                <Form className="text-center" onSubmit={submitForm}>
                  <FormGroup>
                    <Label for="watid">
                      Wat ID
                    </Label>
                    <Input
                      type="number"
                      id="watid"
                      name="watid"
                      placeholder="Your 8-digit WatIAM Id (ex: 21000021)"
                      required
                      min="10000000"
                      max="99999999"
                      onInvalid={e => e.target.setCustomValidity('WATID should have 8 digits')}
                      onInput={e => e.target.setCustomValidity('')}
                      onChange={(e) => handleChange(e, 'watid')}
                      value={data.watid}
                    />
                  </FormGroup>
                  <FormGroup>
                    <Label for="name">
                      Name
                    </Label>
                    <Input
                      id="name"
                      name="name"
                      type="text"
                      placeholder="Your Name"
                      required
                      onChange={(e) => handleChange(e, "name")}
                      value={data.name}
                    />
                  </FormGroup>
                  <FormGroup>
                    <Label for="email">
                      Email
                    </Label>
                    <Input
                      id="email"
                      name="email"
                      placeholder="Your Email"
                      type="email"
                      required
                      onChange={(e) => handleChange(e, 'email')}
                      value={data.email}
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
                      minLength="8"
                      maxLength="32"
                      onChange={(e) => { handleChange(e, 'password') }}
                      value={data.password}
                    />
                  </FormGroup>

                  <Button color='dark'>
                    Register
                  </Button>
                  <Button color='secondary' className='ms-2' onClick={resetData}>
                    Reset
                  </Button>
                  <div style={{marginTop:'2.5%'}}>
                    Already a User?
                    <Button color='primary' className='ms-2' href="/Login">
                      Login
                    </Button>
                  </div>
                  
                </Form>
              </CardBody>
            </Card>

          </Col>
        </Row>

      </Container>
      <div>
        <Footer />
      </div>
    </div>

  );
};

export default Signup;
