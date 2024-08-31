import React from 'react';
import NavbarLogin from './NavbarLogin';
import Footer from './Footer';
import {useLocation} from 'react-router-dom';
import { useEffect, useState } from "react";
import axios from 'axios';
import { toast } from 'react-toastify';


function RestaurantAdmin() {
const location = useLocation();
const queryParams = new URLSearchParams(location.search);
location.state = {};
location.state.name = queryParams.get('name');
const styles = {
          h4: {
          display: "inline-block",
              fontSize: "24px", /* set the font size */
              fontWeight: "bold", /* set the font weight */
              color: "#333", /* set the font color */
              padding: "10px 20px", /* add padding to create more space around the text */
              backgroundColor: "#f2f2f2", /* set the background color */
              borderRadius: "5px", /* add rounded corners */
              textTransform: "uppercase", /* set the text to uppercase */
              boxShadow: "0 2px 2px rgba(0,0,0,0.3)", /* add a box shadow */
              border: "5px solid #c71585",
            textAlign: "center",
            
          },
          headerContainer: {
            textAlign: 'center',
          },
          menuContainer: {
            backgroundColor: "	#fff0f5	",
            borderRadius: "5px",
            boxShadow: "0 2px 2px rgba(0,0,0,0.3)",
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
            boxShadow: "0 2px 2px rgba(0,0,0,0.3)",
            margin: "0 20px",
            padding: "20px",
            width: "40%",
            textAlign: "center",
          },

          table: {
            borderCollapse: "collapse",
            border: "1px solid black",
            margin: "auto",
            borderRadius: '10px',
            overflow: 'hidden',
            boxShadow: '0px 0px 10px rgba(0,0,0,0.3)',
          },

          td: {
            padding: "10px",
            border: "1px solid black",
          },

          tr:{
          backgroundColor: '#FF6347',
          borderBottom: '1px solid #ddd',
          color: 'white',
          textTransform: 'uppercase'
          },


          th: {
          backgroundColor: "#c71585	",
          color: "white",
          fontWeight: "bold",
          padding: "10px",
          border: "1px solid black",
          },

            button: {
              backgroundColor: '#4CAF50',
              border: 'none',
              borderRadius: '4px',
              color: '#fff',
              cursor: 'pointer',
              padding: '10px',
              textDecoration: 'none',
              textTransform: 'uppercase',
              fontSize: '14px',
            },
              deleteButton: {
                backgroundColor: '#f44336',
                color: 'white',
                border: 'none',
                borderRadius: '4px',
                padding: '8px 16px',
                fontSize: '16px',
                cursor: 'pointer',
                transition: 'background-color 0.3s ease',
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

const [deletedata, setDeleteData] = useState(null);

 const [formData, setFormData] = useState({
      itemName: '',
      itemType: 'NON_VEG',
      itemCategory: 'NON_BEVERAGE',
      price: 1,
    });


        const handleInputChange=(e,prop)=>{
            setFormData({...formData,[prop]:e.target.value})
        }


    useEffect(() => {
      axios.get(`http://localhost:8080/food/${location.state.name}`)
        .then(response => setData(response.data))
        .catch(error => console.log(error));
    }, []);

    function handleDelete(foodItem) {
      axios.delete(`http://localhost:8080/food/${location.state.name}/${foodItem}`)
        .then(response => {
        setDeleteData(response.data);
        toast.success('Food item deleted successfully');
              setTimeout(() => {
                window.location.reload();
              }, 1000);
        })
        .catch(error => console.log(error));
    }



const handleAddItem = (e) => {
      e.preventDefault();
        if (formData.itemName.trim() === '') {
          alert('Please enter a name for the item.');
          return;
        }
        const url = 'http://localhost:8080/food/add/' + location.state.name;
      fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      })
        .then((response) => response.json())
        .then((data) => {
        if(data.success){
            toast.success(data.message);
                                  setTimeout(() => {
                                    window.location.reload();
                                  }, 1000);
                                  }
           else
           toast.error(data.message);
        })
        .catch((error) => {
          console.error('Error adding item:', error);
        });
    };

  const types = ['NON_VEG', 'VEG'];
  const categories = ['NON_BEVERAGE', 'BEVERAGE'];
  const variable = 1;
    return (
      <div>
        <NavbarLogin variable={variable}/>
            <br></br>
            <h3 style={{ color: 'black', textAlign: 'center' }}><b>
              Restaurant Admin Page</b>
            </h3>
            <br></br>
<div style={styles.headerContainer}>
                <h4 style={styles.h4}>Hi There: {location.state.name}</h4>
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
                        <th style={styles.th}>Delete Item</th>
                      </tr>
                    </thead>
                    <tbody>
                      {data.food.map(item => (
                        <tr key={item.foodItem}>
                          <td style={styles.td}>{item.foodItem}</td>
                          <td style={styles.td}>{item.foodType}</td>
                          <td style={styles.td}>{item.foodCategory}</td>
                          <td style={styles.td}>{item.price}</td>
                          <td style={styles.td}>
                              <button
                                onClick={() => handleDelete(item.foodItem)}
                                style={styles.deleteButton}
                                onMouseEnter={(e) => e.target.style.backgroundColor = styles.deleteButtonHover.backgroundColor}
                                onMouseLeave={(e) => e.target.style.backgroundColor = styles.deleteButton.backgroundColor}
                              >
                                Delete
                              </button>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                )}

                <form style={{ textAlign: 'center',  marginTop: '20px'}}>
                       <div style={{ display: "flex", justifyContent: "center" }}>
                        <label style={styles.label}>
                          Name:
                          <input
                            style={{ ...styles.input, width: "130px"}}
                            type="text"
                            name="itemName"
                            required
                            value={formData.itemName}
                            onChange={(e) => handleInputChange(e, 'itemName')}
                          />
                        </label>
                        <label style={styles.label}>
                          Type:
                          <select
                            style={styles.select}
                            name="itemType"
                            value={formData.itemType}
                            onChange={(e) => handleInputChange(e, 'itemType')}
                          >
                            {types.map((type) => (
                              <option key={type} style={styles.option} value={type}>
                                {type}
                              </option>
                            ))}
                          </select>
                        </label>

                        <label style={styles.label}>
                          Category:
                          <select
                            style={styles.select}
                            name="itemCategory"
                            value={formData.itemCategory}
                            onChange={(e) => handleInputChange(e, 'itemCategory')}
                          >
                            {categories.map((category) => (
                              <option key={category} style={styles.option} value={category}>
                                {category}
                              </option>
                            ))}
                          </select>
                        </label>

                        <label style={styles.label}>
                          Price:
                          <input
                            style={{ ...styles.input, width: "60px"}}
                            type="number"
                            name="price"
                            value={formData.price}
                            onChange={(e) => handleInputChange(e, 'price')}
                          />
                        </label>
                 </div>
                 </form>
                 <button style={styles.button} type="submit" onClick={handleAddItem}>Add Item</button>
              </div>
            </div>
<Footer />
</div>
);
}

export default RestaurantAdmin;
