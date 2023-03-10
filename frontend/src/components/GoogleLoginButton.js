import React from 'react';
import { GoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
const GoogleLoginButton = () => {
    return (
        <React.Fragment>

            <GoogleOAuthProvider clientId='111272373115-5vbj6rckde2ntanmcchogcsan9aislv8.apps.googleusercontent.com'>
                <GoogleLogin buttonText="구글로 로그인하기" onSuccess={(credentialResponse)=>{
                    console.log(credentialResponse)
                }}
                onError={(err)=>{
                    console.log(err)
                }} />
                    

                
            </GoogleOAuthProvider>
        </React.Fragment>
    );
};

export default GoogleLoginButton;