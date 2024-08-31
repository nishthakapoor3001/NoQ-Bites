import React from 'react';
import cart from '../Images/cart.jpeg';

function NavbarLogin(props) {
  const variable = props.variable;

  return (
    <div>
      <header className='sticky-top'>
        <nav className='navbar  navbar-expand-lg navbar-dark bg-dark nav nav-tabs'>
          <div className='container-fluid'>
            <a className='navbar-brand' href='/'>
              <span>NO-Q BITES </span>
            </a>
            <button
              className='navbar-toggler'
              type='button'
              data-bs-toggle='collapse'
              data-bs-target='#navbarNavDropdown'
              aria-controls='navbarNavDropdown'
              aria-expanded='false'
              aria-label='Toggle navigation'>
              <span className='navbar-toggler-icon'></span>
            </button>
            <div
              className='collapse navbar-collapse justify-content-end  '
              id='navbarNavDropdown'>
              <ul className='navbar-nav '>
                {/* <li className='nav-item'>
                  <a className='nav-link' href='/About'>
                    About Us
                  </a>
                </li>
                <li className='nav-item'>
                  <a className='nav-link' href='/ContactPage'>
                    Contact Us
                  </a>
                </li> */}
                {variable != 1 && (
                  <li className='nav-item'>
                    <a className='nav-link' href='/Cart'>
                      <img
                        src={cart}
                        style={{
                          width: '46px',
                          height: '46px',
                          borderRadius: '50%',
                          overflow: 'hidden',
                          marginTop: '-6px',
                        }}
                        alt='...'
                      />
                    </a>
                  </li>
                )}
                <li className='nav-item'>
                  <a className='nav-link ' aria-current='page' href='/ViewMyOrders'>
                    My Orders
                  </a>
                </li>
                <li className='nav-item'>
                  <a className='nav-link ' aria-current='page' href='/'>
                    Logout
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
    </div>
  );
}

export default NavbarLogin;