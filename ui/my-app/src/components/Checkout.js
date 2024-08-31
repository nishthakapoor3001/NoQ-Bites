import React from 'react';
import {  GetCartItems } from "../services/user_service";
import {  Redirect, Link } from 'react-router-dom';
import NavbarLogin from './NavbarLogin';
import Footer from './Footer';
import Payment from './Payment';

export default class Checkout extends React.Component {
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

            var cost = cartItemsList.totalCost;

            this.setState({ products: cartItems, total: cost }, () => {
                 console.log(this.state.total);
            });
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
                                             Checkout</b>
                                            </h3>
				<hr/>
				{
					products.map((product, index) =>
						<div key={index}>
							<p>
								{product.product.foodItem}
								<small> (quantity: {product.quantity})</small>
								<span className="float-right text-primary">${product.quantity * product.product.price}</span>
							</p><hr/>
						</div>
					)
				}
				<hr/>
				{ products.length ? <div><h4><small>Total Amount:</small><span className="float-right text-primary">${total}</span></h4><hr/></div>: ''}
				{ !products.length ? <h3 className="text-warning">No item on the cart</h3>: ''}
				{ products.length ? (<Link to="/Payment"> <button className="btn btn-success float-right" onClick={() => alert('Proceed to Pay')}>Pay</button></Link>): ('')}
				<Link to="/"><button className="btn btn-danger float-right" style={{ marginRight: "10px" }}>Cancel</button></Link>
				<br/><br/><br/><br/><br/><br/><br/>
			</div>
			<Footer />
            </div>
		);
	}
}
