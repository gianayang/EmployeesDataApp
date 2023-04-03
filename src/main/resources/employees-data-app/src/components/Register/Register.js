import React, { useState } from 'react';

const Register = (props) => {
    const[username, setUsername] = useState('');
    const[password, setPassword] = useState('');

    const onNameChange = (event) => {
        setUsername(event.target.value);
    }

    const onPasswordChange = (event) => {
        setPassword(event.target.value);
    }

    const onSubmitSignIn = (e) => {
        e.preventDefault();
        fetch('http://localhost:8080/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET, DELETE, POST, PUT, HEAD, OPTIONS',
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        }).then(response => response.json())
        .then(token => {
            this.props.loadUser({
                'username': username,
                'token': token,
            });
            props.onRouteChange('home');
        })
    }

    return(
        <article className="br3 ba b--black-10 mv4 w-100 w-50-m w-25-l mw5 shadow-5 center">
            <main className="pa4 black-80">
                <div className="measure">
                    <fieldset id="sign_up" className="ba b--transparent ph0 mh0">
                    <legend className="f2 fw6 ph0 mh0">Register</legend>
                    <div className="mt3">
                        <label className="db fw6 lh-copy f6" htmlFor="name">Name</label>
                        <input className="b pa2 input-reset ba bg-transparent hover-bg-black hover-white w-100" 
                        type="name" 
                        name="name"  
                        id="name"
                        onChange={onNameChange} />
                    </div>
                    <div className="mt3 mc3">
                        <label className="db fw6 lh-copy f6" htmlFor="password">Password</label>
                        <input className="b pa2 input-reset ba bg-transparent hover-bg-black hover-white w-100" 
                        type="password" 
                        name="password"  
                        id="password"
                        onChange={onPasswordChange} />
                    </div>
                    </fieldset>
                    <div className="">
                        <input 
                            onClick={onSubmitSignIn}
                            className="b ph3 pv2 input-reset ba b--black bg-transparent grow pointer f6 dib" 
                            type="submit" 
                            value="Register" />
                    </div>
                </div>
            </main>
        </article>
    );
};

export default Register;