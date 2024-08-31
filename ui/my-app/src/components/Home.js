import React from 'react';
import Navbar from './Navbar';
// import Slider from './Slider';
import Footer from './Footer';

import safety from '../Images/safety.png';
import fare from '../Images/lowestfare.png';
import offers from '../Images/offers.png';
import helpline from '../Images/helpline.png';
import doodle from '../Images/doodle.png';
import award1 from '../Images/award1.png';
import award2 from '../Images/award2.png';
import award3 from '../Images/award3.png';
// import uwpic from '../Images/Waterloo.jpeg'

function Home() {

  localStorage.clear();
  return (
    <div>
      <Navbar />
      {/* <Slider /> */}

      <div className='d-flex h-100 text-center '>
        <div className='cover-container d-flex w-100 h-100 p-3 mx-auto flex-column'>
          <div
            className='doodle-text'
            style={{
              height: '20em',

              backgroundImage: 'url(' + doodle + ')',
              backgroundRepeat: 'no-repeat',
              backgroundSize: '100em',
              color: '#062f4f',
            }}>
            <br />
            <h1
              className='display-1'
              style={{
                fontFamily: 'Great Vibes, cursive',
                verticalAlign: 'middle',
                display: 'inline-block',
              }}>
              No-Q Bites
            </h1>
            <p
              className='lead'
              style={{
                color: '#4f060b',
                fontSize: '1.7em',
                fontWeight: 'revert',
                marginTop: '-0.75em',
                marginBottom: '1.5em',
              }}>
              <strong>Order your cravings with just a tap - anytime, anywhere</strong>
            </p>
            <p className='lead'>
              <a data-bs-toggle='modal'
                href='/Signup'
                role='button' className='btn btn-lg  btn-dark'>
                <strong style={{ color: '#fff' }}>Join Us Now !</strong>
              </a>
            </p>
          </div>
          <br />
          <div
            className='cards'
            style={{
              backgroundColor: '#062f4f',
              padding: '1% 0',
            }}>
            <h1 style={{ color: '#FFF', textAlign: 'center' }}>
              OUR PROMISE TO DELIVER
            </h1>

            <div
              className='row row-cols-1 row-cols-xl-4 row-cols-sm-2 g-4 container-fluid '
              style={{ margin: '2% 0', padding: '2% 2%' }}>
              <div className='col'>
                <div className='card h-100'>
                  <img
                    src={safety}
                    style={{
                      width: '30%',
                      height: '50%',
                      padding: '20px 0px',
                    }}
                    className='card-img-top mx-auto d-block'
                    alt='...'
                  />
                  <div className='card-body'>
                    <h4 className='card-title'>SAFETY ENSURED</h4>
                    <p className='card-text' style={{ padding: '20px 20px' }}>

                    </p>
                  </div>
                </div>
              </div>
              <div className='col'>
                <div className='card h-100'>
                  <img
                    src={fare}
                    style={{
                      width: '35%',
                      height: '50%',
                      padding: '20px 0px',
                    }}
                    className='card-img-top mx-auto d-block'
                    alt='...'
                  />
                  <div className='card-body'>
                    <h4 className='card-title'>LOWEST PRICES</h4>
                    <p className='card-text' style={{ padding: '20px 20px' }}>

                    </p>
                  </div>
                </div>
              </div>
              <div className='col'>
                <div className='card h-100'>
                  <img
                    src={offers}
                    style={{
                      width: '35%',
                      height: '50%',
                      padding: '20px 0px',
                    }}
                    className='card-img-top mx-auto d-block'
                    alt='...'
                  />
                  <div className='card-body'>
                    <h4 className='card-title'>BEST OFFERS</h4>
                    <p className='card-text' style={{ padding: '20px 20px' }}>

                    </p>
                  </div>
                </div>
              </div>
              <div className='col'>
                <div className='card h-100'>
                  <img
                    src={helpline}
                    style={{
                      width: '35%',
                      height: '50%',
                      padding: '20px 0px',
                    }}
                    className='card-img-top mx-auto d-block'
                    alt='...'
                  />
                  <div className='card-body'>
                    <h5 className='card-title'>24x7 HELPLINE</h5>
                    <p className='card-text' style={{ padding: '20px 20px' }}>

                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className='awards'>
            <h1
              style={{
                padding: '4% 0',
              }}>
              WHY CHOOSE US
            </h1>
            <div className='row row-cols-1  row-cols-xl-3 row-cols-sm-1 align-items-center'>
              <div className='col '>
                <div className='h-100'>
                  <img
                    src={award2}
                    style={{ width: '30%', height: '30%' }}
                    // className=' mx-auto d-block'
                    alt='...'
                  />
                </div>
                <div
                  className='col-text'
                  style={{
                    width: '30%',
                    height: '30%',
                    textAlign: 'center',
                    margin: '5% 35% 10%',
                  }}>
                  <p className='fs-5'>
                    Earn Rewards on every order.
                  </p>
                </div>
              </div>
              <div className='col '>
                <div className='h-100'>
                  <img
                    src={award1}
                    style={{ width: '30%', height: '30%' }}
                    // className=' mx-auto d-block'
                    alt='...'
                  />
                </div>
                <div
                  className='col-text'
                  style={{
                    width: '30%',
                    height: '30%',
                    textAlign: 'center',
                    margin: '5% 35% 10%',
                  }}>
                  <p className='fs-5'>
                    Multiple Cuisines at your tips.
                  </p>
                </div>
              </div>
              <div className='col '>
                <div className='h-100'>
                  <img
                    src={award3}
                    style={{ width: '30%', height: '30%' }}
                    // className=' mx-auto d-block'
                    alt='...'
                  />
                </div>
                <div
                  className='col-text'
                  style={{
                    width: '30%',
                    height: '30%',
                    textAlign: 'center',
                    margin: '5% 35% 10%',
                    fontSize: '1.5em',
                  }}>
                  <p className='fs-5'>
                    Grow your business by partnering with us.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Home;
