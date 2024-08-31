import React, { useState } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";

const AddItemForm = (props) => {
    const [formData, setFormData] = useState({
      itemName: '',
      itemType: 'NON_VEG',
      itemCategory: 'NON_BEVERAGE',
      price: 1,
    });
const navigate = useNavigate();
  const styles = {
    dialog: {
      backgroundColor: '#fff',
      borderRadius: '4px',
      boxShadow: '0px 2px 6px rgba(0, 0, 0, 0.1)',
      display: props.showForm ? 'block' : 'none',
      padding: '20px',
      position: 'fixed',
      top: '50%',
      left: '50%',
      transform: 'translate(-50%, -50%)',
      width: '60%',
      maxWidth: '500px',
      zIndex: '999',
      textAlign: 'center',
    },
    label: {
      display: 'block',
      marginBottom: '10px',
      fontWeight: 'bold',
      textAlign: 'left',
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
    button: {
      backgroundColor: '#008CBA',
      color: '#fff',
      border: 'none',
      borderRadius: '4px',
      padding: '12px 20px',
      cursor: 'pointer',
      fontWeight: 'bold',
    },
        cancelButton: {
          backgroundColor: '#ddd',
          color: '#333',
          border: 'none',
          borderRadius: '4px',
          padding: '12px 20px',
          cursor: 'pointer',
          fontWeight: 'bold',
        }
  };


    //handle changes
    const handleInputChange=(e,prop)=>{

      //setting values dynamically
        setFormData({...formData,[prop]:e.target.value})
    }

const handleCancel = () => {
  navigate(`/RestaurantAdmin?name=${props.stateName}`);
};

    const handleAddItem = (e) => {
      e.preventDefault();
        if (formData.itemName.trim() === '') {
          alert('Please enter a name for the item.');
          return;
        }
        const url = 'http://localhost:8080/food/add/' + props.stateName;
      fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      })
        .then((response) => response.json())
        .then((data) => {
        if(data.success)
            toast.success(data.message);
           else
           toast.error(data.message);
          console.log('Item added:', data);
          props.onClose();
        })
        .catch((error) => {
          console.error('Error adding item:', error);
        });
    };

  const types = ['NON_VEG', 'VEG'];
  const categories = ['NON_BEVERAGE', 'BEVERAGE'];
  return (
    <div style={styles.dialog}>
      <h2 style={{ marginBottom: '20px' }}>Add Item</h2>
      <form style={{ textAlign: 'center' }}>
        <label style={styles.label}>
          Name:
          <input
            style={styles.input}
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
            style={styles.input}
            type="number"
            name="price"
            value={formData.price}
            onChange={(e) => handleInputChange(e, 'price')}
          />
        </label>


        <button style={styles.button} type="submit" onClick={handleAddItem}>Add</button>
        <button style={styles.cancelButton} onClick={handleCancel}>Cancel</button>
      </form>
    </div>
  );
};

export default AddItemForm;