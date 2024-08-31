import React, { useState } from "react";
import NavbarLogin from './NavbarLogin';
import Footer from './Footer';
import 'bootstrap/dist/css/bootstrap.min.css';
import {  GetCartItems } from "../services/user_service";
import {  DeleteCartItem } from "../services/user_service";
import {  DeleteUserCart } from "../services/user_service";
import CartItem from './CartItem';
import Checkout from './Checkout';
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';
import { Link } from 'react-router-dom';


export default class Cart extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			products: [],
			total: 0
		}
	}

	componentWillMount() {

		GetCartItems().then((cartItemsList) => {

		    if(!cartItemsList) return;

		    if(!cartItemsList.cartItems) return;
            var cartItems = cartItemsList.cartItems;

            var cost = cartItemsList.totalCost

            this.setState({ products: cartItems, total: cost }, () => {
              console.log(this.state.total);
            });

	    });

	}

	removeFromCart = (product) => {
	    DeleteCartItem(product.id).then((response)=>{

              if(response.success){
                console.log("Item is deleted from cart")
                toast.success(response.message);
                GetCartItems().then((cartItemsList) => {

                	if(!cartItemsList) return;

                	if(!cartItemsList.cartItems) return;
                    var cartItems = cartItemsList.cartItems;

                    var cost = cartItemsList.totalCost

                    this.setState({ products: cartItems, total: cost }, () => {
                         console.log(this.state.total);
                    });

                });
              }else
                toast.error(response.message);
            }).catch((error)=>{
              // console.log(error)
              console.log(error)
            });
	}

	clearCart = () => {
		DeleteUserCart().then((response)=>{
            if(response.success){
                console.log("Cart is deleted for user")
                toast.success(response.message);
                this.setState({products: []});
            }else
                toast.error(response.message);
            }).catch((error)=>{
                      // console.log(error)
                 console.log(error)
            });
	}

	render() {
		const { products, total } =  this.state;
		return (
		<div>
		    <NavbarLogin />
                                        <br></br>
			<div className=" container">

				<h3 style={{ color: 'black', textAlign: 'center' }}><b>
                             Cart</b>
                            </h3>
				<hr/>
				{
					products.map((product) => <CartItem product={product} remove={this.removeFromCart} key={product.id}/>)
				}
				<hr/>
				{ products.length ? <div><h4><small>Total Amount:</small><span className="float-right text-primary">${total}</span></h4><hr/></div>: ''}

				{ !products.length ? <h3 className="text-warning">No item on the cart</h3>: ''}
				<Link to="/checkout"><button className="btn btn-success float-right">Checkout</button></Link>
				<button className="btn btn-danger float-right" onClick={this.clearCart} style={{ marginRight: "10px" }}>Clear Cart</button>
				<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
			</div>
		<Footer />
		</div>
		);
	}
}
