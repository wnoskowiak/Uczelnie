import React from 'react'

import { Helmet } from 'react-helmet'

import './home.css'

const Home = (props) => {
  return (
    <div className="home-container">
      <Helmet>
        <title>Humongous Flaky Otter</title>
        <meta property="og:title" content="Humongous Flaky Otter" />
      </Helmet>
      <div className="home-container1">
        <h1 className="home-text">
          <span>Uczelnie</span>
          <br></br>
          <br></br>
        </h1>
      </div>
      <div className="home-container2">
        <input
          type="text"
          placeholder="login"
          id="login"
          name="login"
          className="input"
        />
      </div>
      <div className="home-container3">
        <input type="text" placeholder="hasło" className="input" />
      </div>
      <div className="home-container4">
        <div className="home-container5">
          <button type="submit" className="button">
            <span>
              <span>Zaloguj</span>
              <br></br>
            </span>
          </button>
        </div>
        <div className="home-container6">
          <button type="button" className="button">
            Button
          </button>
        </div>
      </div>
    </div>
  )
}

export default Home
