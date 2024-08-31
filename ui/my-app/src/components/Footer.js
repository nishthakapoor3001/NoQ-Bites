import React from "react";
 import { FaFacebookF,FaInstagram, FaTwitter,FaLinkedinIn } from "react-icons/fa";


function Footer() {
  return (
    <div  style={{marginTop:'2.5%'}}>
      <footer className='bg-dark text-center text-white'>
        <div className='container p-4 '>
          <section className='mb-4 text-center'>
          <a
              className='btn btn-outline-light btn-floating m-1'
              href='http://www.facebook.com'
              role='button'>
              <FaFacebookF />
            </a>

            <a
              className='btn btn-outline-light btn-floating m-1'
              href='http://www.twitter.com'
              role='button'>
              <FaTwitter />
            </a>

            <a
              className='btn btn-outline-light btn-floating m-1'
              href='http://www.instagram.com'
              role='button'>
              <FaInstagram />
            </a>

            <a
              className='btn btn-outline-light btn-floating m-1'
              href='http://www.linkedin.com'
              role='button'>
              <FaLinkedinIn />
            </a>
          </section>

          <section className='mb-4'>
            <p>
              <strong
                style={{
                  fontSize: '2rem',
                }}>
                NoQ Bites{' '}
              </strong>
              Satisfy your cravings, anytime and anywhere with just a few taps!. ORDER NOW!
            </p>
          </section>
        </div>

        <div
          className='text-center p-3'
          style={{ backgroundColor: '#000000', opacity: '0.3' }}>
          © 2023 Copyright : 
          <a className='text-white' href='/#'>
             noqbites.com
          </a>
        </div>
      </footer>
    </div>
  );
}

export default Footer;
