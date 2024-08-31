//import React from 'react';
//// import { AddtoOrders } from '../services/user_service';
//import axios from 'axios';
//import { toast } from 'react-toastify';
//import NavbarLogin from './NavbarLogin';
//import Footer from './Footer';
////import GooglePayButton from '@google-pay/button-react';
//import { useNavigate } from 'react-router-dom';
//import {  DeleteUserCart } from "../services/user_service";
//
//
//function Payment() {
//
//    const token = localStorage.getItem('token');
//    const navigate = useNavigate();
//
//    const handlePayment = () => {
//        axios.post('http://localhost:8080/order/add?token=' + token)
//            .then(response => {
//                console.log(response);
//                toast.success('Payment Successful');
//                DeleteUserCart().then((response)=>{
//                            if(response.success){
//                                console.log("Cart is deleted for user")
//                                toast.success(response.message);
//                                this.setState({products: []});
//                            }else
//                                toast.error(response.message);
//                            }).catch((error)=>{
//                                      // console.log(error)
//                                 console.log(error)
//                });
//                navigate('/ViewMyOrders');
//
//            })
//            .catch(error => {
//                console.log(error);
//                toast.error('Error: ' + error);
//            });
//    };
//
//    return (
//        <div>
//            < NavbarLogin />
//
//            <div className="container">
//                <div className='row g-3' style={{ margin: '2.5% auto' }}>
//                    <div className='col-md-8'>
//                        <div className='card' style={{ height: '100%', margin: '0 0 0 5%' }}>
//                            <h4 className='card-header text-center'>
//                                Payment <i className='fas fa-credit-card'></i>
//                            </h4>
//                            <div className='card-body'>
//                                <form>
//                                    <div className='mb-3'>
//                                        <label
//                                            htmlFor='cardName'
//                                            className='form-label'
//                                            style={{ fontSize: '1.25em' }}>
//                                            Name on the Card
//                                        </label>
//                                        <input
//                                            type='text'
//                                            className='form-control'
//                                            id='cardName'
//                                            name='cardName'
//                                            placeholder='write your name here as displayed on the credit/debit card'
//                                            required
//                                        />
//                                    </div>
//                                    <div className='mb-3'>
//                                        <label
//                                            htmlFor='cardNumber'
//                                            className='form-label'
//                                            style={{ fontSize: '1.25em' }}>
//                                            Card Number
//                                        </label>
//                                        <input
//                                            type='text'
//                                            className='form-control'
//                                            id='cardNumber'
//                                            name='cardNumber'
//                                            placeholder='---- ---- ---- ----'
//                                            pattern='[0-9]+'
//                                            title='Only numbers can be added here'
//                                            maxLength='16'
//                                            minLength='16'
//                                            required
//                                            onKeyPress={(event) => {
//                                                if (!/[0-9]/.test(event.key)) {
//                                                    event.preventDefault();
//                                                }
//                                            }}
//                                        />
//                                    </div>
//
//                                    <div className='row g-3'>
//                                        <div className='col-sm-6'>
//                                            <label
//                                                htmlFor='validThru'
//                                                className='form-label'
//                                                style={{ fontSize: '1.25em' }}>
//                                                Valid thru
//                                            </label>
//                                            <input
//                                                type='month'
//                                                className='form-control'
//                                                id='validThru'
//                                                name='validThru'
//                                                pattern='[0-9]{2}/[0-9]{4}'
//                                                placeholder='MM / YYYY'
//                                                required
//                                            />
//                                        </div>
//
//                                        <div className='col-sm-6'>
//                                            <label
//                                                htmlFor='cvv'
//                                                className='form-label'
//                                                style={{ fontSize: '1.25em' }}>
//                                                CVV
//                                            </label>
//                                            <input
//                                                type='text'
//                                                className='form-control'
//                                                id='cvv'
//                                                name='cvv'
//                                                pattern='^\d{3}$'
//                                                placeholder='---'
//                                                maxLength='3'
//                                                minLength='3'
//                                                required
//                                                onKeyPress={(event) => {
//                                                    if (!/[0-9]/.test(event.key)) {
//                                                        event.preventDefault();
//                                                    }
//                                                }}
//                                            />
//                                        </div>
//                                    </div>
//                                    <button
//                                        type="button"
//                                        className="btn btn-primary"
//                                        onClick={handlePayment}
//                                        style={{
//                                            width: '25%',
//                                            margin: '2.5% 37.5%',
//                                            // padding: '1.5%',
//                                        }}
//                                    >
//                                        Make Payment
//                                    </button>
//                                </form>
//                            </div>
//
//                            <div className='row g-3' style={{ width: '100%', marginTop: '0%' }}>
//                            <hr
//                                style={{ width: '45%', marginLeft: '4%', marginRight: '1%' }}
//                            />
//                            OR
//                            <hr style={{ width: '45%', marginLeft: '1%' }} />
//                        </div>
//
////                        {/* <hr style={{ width: '80%', marginLeft: '10%' }} /> */}
////                        <p
////                            style={{
////                                margin: '1% auto',
////                                fontWeight: 'bolder',
////                                fontSize: '1.25em',
////                            }}>
////                            Pay Using Google Pay
////                        </p>
////                        <div
////                            style={{
////                                margin: '1.0% auto',
////                            }}>
////                            <GooglePayButton
////                                environment="TEST"
////                                paymentRequest={{
////                                    apiVersion: 2,
////                                    apiVersionMinor: 0,
////                                    allowedPaymentMethods: [
////                                        {
////                                            type: 'CARD',
////                                            parameters: {
////                                                allowedAuthMethods: ['PAN_ONLY', 'CRYPTOGRAM_3DS'],
////                                                allowedCardNetworks: ['MASTERCARD', 'VISA'],
////                                            },
////                                            tokenizationSpecification: {
////                                                type: 'PAYMENT_GATEWAY',
////                                                parameters: {
////                                                    gateway: 'example',
////                                                    gatewayMerchantId: 'exampleGatewayMerchantId',
////                                                },
////                                            },
////                                        },
////                                    ],
////                                    merchantInfo: {
////                                        merchantId: '12345678901234567890',
////                                        merchantName: 'Demo Merchant',
////                                    },
////                                    transactionInfo: {
////                                        totalPriceStatus: 'FINAL',
////                                        totalPriceLabel: 'Total',
////                                        totalPrice: '100.00',
////                                        currencyCode: 'USD',
////                                        countryCode: 'US',
////                                    },
////                                }}
////                                onLoadPaymentData={handlePayment}
////                            />
////                            </div>
//
////                        </div>
////                    </div>
////                </div>
//            </div>
//            <Footer />
//        </div>
//    );
////};
//
//export default Payment;
