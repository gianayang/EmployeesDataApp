import './App.css';
import React, { Component } from 'react';
import Signin from './components/Signin/Signin';
import Register from './components/Register/Register';
import Navigation from './components/Navigation/Navigation';
import EmployeesTable from './components/EmployeesTable/EmployeesTable';

const initialState = {
  route: 'register',
  isSignedIn: false,
  user: {
    username: '',
    password: '',
    token: ''
  }
}

class App extends Component {
  constructor() {
    super();
    this.state = initialState;
  }

  loadUser = (data) => {
    this.setState({
      user: {
        username: data.username,
        token: data.token
      }
    })
    console.log(this.state.user, this.state.token)
  }

  onRouteChange = (route) => {
    if (route === 'signout') {
      this.setState(initialState);
    } else if (route === 'home') {
      this.setState({ isSignedIn: true, route: 'home' })
    } else {
      this.setState({ route: route })
    }
  }

  render() {
    return (
      <div className='App'>
        <Navigation onRouteChange={this.onRouteChange} isSignedIn={this.state.isSignedIn} />
          {
            this.state.route === 'home'?
            <div>
              <EmployeesTable />
            </div>
            :
            this.state.route === 'signin' ?
              <Signin onRouteChange={this.onRouteChange} loadUser={this.loadUser}></Signin>
              :
              <Register onRouteChange={this.onRouteChange} loadUser={this.loadUser}></Register>
          }
      </div>
    )
  }
}

export default App;
