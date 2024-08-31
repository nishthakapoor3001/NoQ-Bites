import React, { Component }  from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import NavbarLogin from './NavbarLogin';
import Footer from './Footer';

function ViewMyOrders() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem('token');
    axios
      .get('http://localhost:8080/order/?token=' + token)
      .then((response) => {
        setOrders(response.data.orderItems);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  function formatDate(date) {
    const formattedDate = new Date(date);
    const year = formattedDate.getFullYear();
    const month = ('0' + (formattedDate.getMonth() + 1)).slice(-2);
    const day = ('0' + formattedDate.getDate()).slice(-2);
    const hours = ('0' + formattedDate.getHours()).slice(-2);
    const minutes = ('0' + formattedDate.getMinutes()).slice(-2);
    const seconds = ('0' + formattedDate.getSeconds()).slice(-2);
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
  }

  function groupOrdersByDateAndTime(orders) {
    if (!orders) return {};
    const groupedOrders = {};
    orders.forEach((order) => {
      const date = formatDate(order.orderedDate);
      if (!groupedOrders[date]) {
        groupedOrders[date] = [];
      }
      groupedOrders[date].push(order);
    });
    return groupedOrders;
  }

  function calculateTotalCost(orders) {
    let totalCost = 0;
    orders.forEach((order) => {
      totalCost += order.quantity * order.product.price;
    });
    return totalCost.toFixed(2);
  }

  const groupedOrders = groupOrdersByDateAndTime(orders);

  return (
    <div>
      <NavbarLogin />
      <br />
      <div className="card">
        <h2 className="card-header text-center">My Orders</h2>
        <br />
        <br />
        {Object.keys(groupedOrders).length === 0 ? (
          <p className="text-center">You have not placed any orders yet.</p>

        ) : (
          Object.entries(groupedOrders).map(([date, orders]) => (
            <div key={date}>
              <h3 className="mt-5">{date}</h3>
              <table className="table table-striped table-bordered table-hover">
                <thead style={{ backgroundColor: 'lightBlue' }}>
                  <tr>
                    <th>Food Items</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Cost</th>
                  </tr>
                </thead>
                <tbody>
                  {orders.map((order) => (
                    <tr key={order.orderItemId}>
                      <td>{order.product.foodItem}</td>
                      <td>{order.quantity}</td>
                      <td>{`$${order.product.price.toFixed(2)}`}</td>
                      <td>{`$${(order.quantity * order.product.price).toFixed(2)}`}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
              <h4 className="text-right">Total Cost: ${calculateTotalCost(orders)}</h4>
              <hr></hr>
            </div>
          ))
        )}
      </div>
      <Footer />
    </div>
  );
}

export default ViewMyOrders;
