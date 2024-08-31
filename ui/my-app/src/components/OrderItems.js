import React from 'react';
import Navbar from './Navbar';
import NavbarLogin from './NavbarLogin';
import Footer from './Footer';
import {useLocation} from 'react-router-dom';
import { useEffect, useState } from "react";
import axios from 'axios';
import { toast } from 'react-toastify';
import { FaCentercode } from 'react-icons/fa';
import { useNavigate } from "react-router-dom";


function OrderItems() {
const location = useLocation();
const queryParams = new URLSearchParams(location.search);
location.state = {};

location.state.name = queryParams.get('name');
const navigate = useNavigate();
const token = localStorage.getItem('token');

function handleClickCart() {
  for(let i=0;i< dataToSend.items.length;i++) {
     axios.post('http://localhost:8080/cart/add?token='+token, {id:dataToSend.items[i].foodItemId,quantity:dataToSend.items[i].quantity})
           .then(response => {
             console.log(response);
             toast.success('Item added to cart');
             // const navigate = useNavigate();
             // navigate('/Cart');
           })
           .catch(error => {
             console.log(error);
             toast.error('Error adding item '+ dataToSend.items[i].foodItemId+ 'to cart');
           });
  }

  // navigate("/Cart")
}
// console.log(location.state.name)
const styles = {
  h4: {
    display: "inline-block",
    fontSize: "24px",
    fontWeight: "bold",
    color: "#333",
    padding: "10px 20px",
    backgroundColor: "#f2f2f2",
    borderRadius: "5px",
    textTransform: "uppercase",
    boxShadow: "0 2px 2px rgba(0, 0, 0, 0.3)",
    border: "5px solid #c71585",
    textAlign: "center",
  },
  headerContainer: {
    textAlign: 'center',
  },
  menuContainer: {
    backgroundColor: "#fff0f5",
    borderRadius: "5px",
    boxShadow: "0 2px 2px rgba(0, 0, 0, 0.3)",
    margin: "20px",
    padding: "20px",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
  },
  h5: {
    fontSize: "28px",
    fontWeight: "bold",
    color: "#333",
    marginBottom: "20px",
  },
  ul: {
    listStyle: "none",
    margin: "0",
    padding: "0",
  },
  li: {
    fontSize: "20px",
    color: "#666",
    marginBottom: "10px",
  },
  menuItems: {
    backgroundColor: "#fff",
    borderRadius: "5px",
    boxShadow: "0 2px 2px rgba(0, 0, 0, 0.3)",
    margin: "0 20px",
    padding: "20px",
    width: "40%",
    textAlign: "center",
  },
  table: {
    borderCollapse: "collapse",
    border: "1px solid black",
    margin: "auto",
    borderRadius: "10px",
    overflow: "hidden",
    boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.3)",
  },
  td: {
    padding: "10px",
    border: "1px solid black",
  },
  tr: {
    backgroundColor: "#DD0031",
    borderBottom: "1px solid #ddd",
    color: "#fff",
    textTransform: "uppercase",
  },
  th: {
    backgroundColor: "#c71585",
    color: "#fff",
    fontWeight: "bold",
    padding: "10px",
    border: "1px solid black",
  },
  button: {
    backgroundColor: '#DD0031',
    border: 'none',
    borderRadius: '4px',
    color: '#ff4',
    alignItems: 'center',
    cursor: 'pointer',
    padding: '10px',
    textDecoration: 'none',
    textTransform: 'uppercase',
    fontSize: '14px',
  },
  deleteButton: {
    backgroundColor: "#c71585",
    color: "#fff",
    border: "none",
    borderRadius: "4px",
    padding: "8px 16px",
    fontSize: "16px"
              
              },
              deleteButtonHover: {
                backgroundColor: '#d32f2f',
              },
              label: {
                    display: 'block',
                    marginBottom: '10px',
                    fontWeight: 'bold',
                    textAlign: 'left',
                    marginRight: "22px",
                  },
                      select: {
                        display: 'block',
                        padding: '8px',
                        width: '100%',
                        border: 'none',
                        borderRadius: '4px',
                        boxShadow: '0px 2px 6px rgba(0, 0, 0, 0.1)',
                        marginBottom: '20px',
                        boxSizing: 'border-box',
                      },
                      option: {
                        padding: '8px',
                      },
                  input: {
                    display: 'block',
                    padding: '8px',
                    width: '100%',
                    border: 'none',
                    borderRadius: '4px',
                    boxShadow: '0px 2px 6px rgba(0, 0, 0, 0.1)',
                    marginBottom: '20px',
                    boxSizing: 'border-box',
                  },
  };


 
    
    const [data, setData] = useState(null);
    const [quantity, setQuantity] = useState(0);
    
    const incrementQuantity = (item) => {
      item.quantity = item.quantity+1;
      const newData = data;
      newData.food.map((localItem)=>{
        if(localItem.foodItem==item.foodItem){
          localItem.quantity=item.quantity;
        }
      })
      setData({...newData});
      // setQuantity(quantity + 1);  
    };
    
    const decrementQuantity = (item) => {
      // if (quantity > 0) {
        item.quantity = item.quantity-1;
        const newData = data;
        newData.food.map((localItem)=>{
          if(localItem.foodItem==item.foodItem){
            localItem.quantity=item.quantity;
          }
        })

      setData({...newData});
      // }
    };
    const cartItems = data && data.food ? data.food.filter(item => item.quantity > 0) : [];
    // const cartItems = data.food.filter(item => item.quantity > 0);
  
    // Create an array of item IDs and quantities
    const cartData = cartItems.map(item => {
      return {
        
        foodItemId: item.foodId,
        quantity: item.quantity
      }
    });
    const dataToSend = {
      customerId: 1, // Replace with the ID of the current customer
      items: cartData,
    }

    useEffect(() => {
      axios.get(`http://localhost:8080/food/${location.state.name}`)
        .then((response) => 
          {
            response.data.food.map((item)=>{
            item.quantity=0;
            console.log(item);
          })
          setData(response.data)})
        .catch(error => console.log(error));
    }, []);

    
    



  const types = ['NON_VEG', 'VEG'];
  const categories = ['NON_BEVERAGE', 'BEVERAGE'];
    return (
      <div>
        <NavbarLogin />
            <br></br>
            <h3 style={{ color: 'black', textAlign: 'center' }}><b>
              </b>
            </h3>
            <br></br>
<div style={styles.headerContainer}>
                <h4 style={styles.h4}> {location.state.name}</h4>
</div>

            <div style={styles.menuContainer}>
              <div style={styles.menuItems}>
                <h5 style={styles.h5}>Menu Items</h5>
                {data === null ? (
                  <p>Loading...</p>
                ) : (
                  <table style={styles.table}>
                    <thead>
                      <tr style={styles.tr}>
                        <th style={styles.th}>Name</th>
                        <th style={styles.th}>Type</th>
                        <th style={styles.th}>Category</th>
                        <th style={styles.th}>Price</th>
                        <th style={styles.th}>Quantity</th>
                      </tr>
                    </thead>
                    <tbody>
                      {data.food.map(item => (
                        <tr key={item.foodItem}>
                          <td style={styles.td}>{item.foodItem}</td>
                          <td style={styles.td}>{item.foodType}</td>
                          <td style={styles.td}>{item.foodCategory}</td>
                          <td style={styles.td}>{item.price}</td>
                          
                          {/* <b>Quantity: </b> */}
                          <td style={styles.td}> <button onClick={() => decrementQuantity(item)}>-</button>
                  {item.quantity}
                  <button onClick={() => incrementQuantity(item)}>+</button></td>
                  
                  
                        </tr>
                        
                      ))}
                    </tbody>
                   {/* <button style={styles.button} onClick={handleClickCart}>Add to Cart</button> */}
                  </table>
                  
                )}

                
                          
              </div>
              
            </div>
            <button style={{ display: 'block',
    margin: '0 auto',
    padding: '10px 20px',
    background: '#4CAF50',
    color: '#fff',
    border: 'none',
    borderRadius: '5px',
    boxShadow: '0px 3px 6px rgba(0, 0, 0, 0.16)',
    cursor: 'pointer',
    transition: 'all 0.3s ease-in-out'}} onClick={handleClickCart}>Add to Cart</button>
            
<Footer />
</div>
);
}

export default OrderItems;
