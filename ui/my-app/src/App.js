import React from 'react';
import './App.css';
import { Route,Routes } from 'react-router-dom';
import Home from './components/Home';
import Login from './components/Login';
import Signup from './components/Signup';
import Restaurant from './components/Restaurant';
import RestaurantLogin from './components/RestaurantLogin';
import RestaurantAdmin from './components/RestaurantAdmin';
import Cart from './components/Cart';
import OrderItems from './components/OrderItems';
import Checkout from './components/Checkout';
import Payment from './components/Payment';
import ViewMyOrders from './components/ViewMyOrders';

// import Addtocart from './components/Addtocart';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function App() {
  return (
    <>

    <ToastContainer />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/Signup" element={<Signup />} />
          <Route path="/Login" element={<Login />} />
          <Route path="/RestaurantLogin" element={<RestaurantLogin />} />
          <Route path="/OrderItems" element={<OrderItems />} />
          <Route path='/OrderItems' render={(props) => <OrderItems name={props.location.search.split('=')[1]} />} />
          <Route path="/Restaurant" element={<Restaurant />} />
          <Route path="/RestaurantAdmin" element={<RestaurantAdmin />} />
          <Route path='/RestaurantAdmin' render={(props) => <RestaurantAdmin name={props.location.search.split('=')[1]} />} />
          <Route path="/Cart" element={<Cart />} />
          <Route path="/Checkout" element={<Checkout />} />
          <Route path="/Payment" element={<Payment />} />
          <Route path="/ViewMyOrders" element={<ViewMyOrders />} />
          {/* <Route path="/MyOrders" element={<ViewMyOrders />} /> */}

        </Routes>

    
    </>
      
      
  );
}

export default App;
