import React, { useEffect, useState } from 'react';
import NavbarLogin from './NavbarLogin';
import axios from 'axios';

import Footer from "./Footer";

import { useNavigate } from "react-router-dom";


function RestaurantTable() {
  const [restaurants, setRestaurants] = useState([]);

  const navigate = useNavigate();

  function handleButtonClick(restaurantName) {
    // console.log(restaurantName);
    navigate(`/OrderItems?name=${restaurantName}`);

    

    console.log('Button clicked!');
  }

  useEffect(() => {
    async function fetchData() {
      try {
        const data = await getAllRestaurants();
        console.log('data:', data);
        setRestaurants(data.restaurants);
      } catch (error) {
        console.error('API error:', error);
      }
    }
  
    fetchData();
  }, []);

  return (
    <div>
      <NavbarLogin />
      <table className="restaurant-table">
      <thead>
  <tr>
    <th>Name</th>
    <th>Address</th>
    {/* <th>ID</th>  */}
  </tr>
</thead>
<tbody>
  {restaurants.map(restaurant => (
    <tr key={restaurant.name}>
      <td>{restaurant.restaurantName}</td>
      <td>{restaurant.address}</td>
      <td>
        <button style={{ display: 'block',
    margin: '0 auto',
    padding: '10px 20px',
    background: '#4CAF50',
    color: '#fff',
    border: 'none',
    borderRadius: '5px',
    boxShadow: '0px 3px 6px rgba(0, 0, 0, 0.16)',
    cursor: 'pointer',
    transition: 'all 0.3s ease-in-out'}} onClick={() => handleButtonClick(restaurant.restaurantName)}>
          ORDER HERE
        </button>
      </td>
    </tr>
  ))}
</tbody>

      </table>
      <Footer />
      <style>
        {`
          .restaurant-table {
            border-collapse: collapse;
            width: 100%;
          }
          
          .restaurant-table th, .restaurant-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
          }
          
          .restaurant-table th {
            background-color: #f2f2f2;
            color: #555;
            font-weight: bold;
          }
          
          .restaurant-table tbody tr:hover {
            background-color: #f5f5f5;
          }
        `}
      </style>
    </div>
  );
}

async function getAllRestaurants() {
  try {
    const response = await axios.get("http://localhost:8080/restaurant/getAll");
    return response.data;
  } catch (error) {
    console.error(error);
  }
}

export default RestaurantTable;
